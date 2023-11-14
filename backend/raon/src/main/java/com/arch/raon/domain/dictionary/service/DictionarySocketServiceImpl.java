package com.arch.raon.domain.dictionary.service;

import java.util.List;
import java.util.Optional;

import com.arch.raon.domain.dictionary.vo.User;
import com.arch.raon.global.service.RedisService;
import org.springframework.stereotype.Service;

import com.arch.raon.domain.dictionary.dto.request.SocketQuizReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketQuizDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketStageResultResDTO;
import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
import com.arch.raon.domain.dictionary.vo.Room;
import com.arch.raon.domain.dictionary.vo.Rooms;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.util.enums.RoomResult;
import com.arch.raon.global.util.enums.SocketResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionarySocketServiceImpl implements DictionarySocketService{

	private final MemberRepository memberRepository;
	private final DictionaryDirectionQuizRepository dictionaryDirectionQuizRepository;
	private final DictionaryInitialQuizRepository dictionaryInitialQuizRepository;
	private final RedisService redisService;


	@Override
	public RoomResult connectRoom(String nickname, String roomId) {
		if(isValidUser(nickname)) {
			// 이미 존재하는 방이면 참가
			if(Rooms.hasRoomThatIdIs(roomId)){
				if(Rooms.isThatRoomFull(roomId)){
					return RoomResult.JOIN_FAIL_FULL;
				}
				Rooms.userEnterToRoom(nickname, roomId);
				return RoomResult.JOIN_SUCCESS;
			}
			// 존재하지 않는 방이면 방 생성
			Rooms.makeRoom(roomId, nickname);
			return RoomResult.CREATE_SUCCESS;
		}
		return RoomResult.FAIL_INVALID_USER;
	}

	@Override
	public RoomResult leaveRoom(String nickname, String roomId) {
		if(!Rooms.hasRoomThatIdIs(roomId))
			return RoomResult.LEAVE_FAIL_NONEXIST;
		if(!Rooms.isUserInRoom(nickname, roomId))
			return RoomResult.FAIL_NOT_IN_ROOM;

		Rooms.leaveRoom(roomId, nickname);
		return RoomResult.LEAVE_SUCCESS;
	}

	@Override
	public RoomResult startGame(String roomId, String nickname) {
		if(!Rooms.hasRoomThatIdIs(roomId)) // 방이 존재하지 않으면
			return RoomResult.FAIL_NONEXIST_ROOM;
		if(!Rooms.isUserInRoom(nickname, roomId)) // 요청을 보낸 유저가 방에 존재하지 않을 때
			return RoomResult.FAIL_NOT_IN_ROOM;
		if(!Rooms.isUserOwner(roomId, nickname)) // 방장이 아닐 때
			return RoomResult.GAME_START_FAIL_NOT_A_OWNER;
		if(Rooms.isRoomPlaying(roomId)) // 이미 게임 중일 때
			return RoomResult.GAME_START_FAIL_ALREADY_PLAYING;

		Rooms.gameStart(roomId, nickname);
		return RoomResult.GAME_START_SUCCESS;
	}

	private boolean isValidUser(String nickname){
		Member member = memberRepository.findByNickname(nickname).orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_MEMBER) {
			@Override
			public ErrorCode getErrorCode() {
				return super.getErrorCode();
			}
		});

		return member != null;
	}

	@Override
	public DictionaryQuizResDTO getQuizes() {
		List<DictionaryDirectionQuiz> directionQuizes = dictionaryDirectionQuizRepository.random3();
		List<DictionaryInitialQuiz> initialQuizes = dictionaryInitialQuizRepository.random7();
		return new DictionaryQuizResDTO(initialQuizes, directionQuizes, SocketResponse.GAME_READY);
	}

	@Override
	public void addQuizToRoom(DictionaryQuizResDTO quizes, String roomId) {
		Rooms.addQuizesToRoom(roomId, quizes);
	}

	@Override
	public SocketQuizDTO getNextQuizFrom(String roomId) {
		return Rooms.getNextQuizFrom(roomId);
	}

	@Override
	public RoomResult addAnswerToRoom(SocketQuizReqDTO reqDTO) {
		if(!Rooms.hasRoomThatIdIs(reqDTO.getRoomId()))
			return RoomResult.FAIL_NONEXIST_ROOM;
		if(!Rooms.isUserInRoom(reqDTO.getNickname(), reqDTO.getRoomId()))
			return RoomResult.FAIL_NOT_IN_ROOM;

		Rooms.addUserAnswer(
			  reqDTO.getRoomId()
			, reqDTO.getNickname()
			, reqDTO.getUserAnswer()
			, reqDTO.getStage()
			, reqDTO.getTimeSpend()
		);

		if(Rooms.isAllSubmit(reqDTO.getRoomId())){
			if(Rooms.isLastStage(reqDTO.getRoomId())){
				return RoomResult.GAME_END;
			}
			Rooms.updateNextQuiz(reqDTO.getRoomId());
			return RoomResult.STAGE_END;
		}
		return RoomResult.GAME_STAGE_DATA_SEND_COMPLETE;
	}


	@Override
	public SocketStageResultResDTO getStageResultOf(String roomId) {
		return Rooms.getStageResult(roomId);
	}

	@Override
	public void saveScore(String roomId) {
		List<User> users = Rooms.getStageResult(roomId).getUsers();

		for(User user : users){
			Member member = memberRepository.findByNickname(user.getNickname()).orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_MEMBER){
				@Override
				public ErrorCode getErrorCode() {
					return super.getErrorCode();
				}
			});

			Long id = member.getId();
			redisService.setCountryDictionaryPoint(id, user.getCurrent_point());
		}
	}

	@Override
	public List<String> getUserNickNames(String roomId) {
		return Rooms.getUserNickNameOf(roomId);
	}

}
