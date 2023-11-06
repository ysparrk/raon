package com.arch.raon.domain.dictionary.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.SocketResponseDTO;
import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.quizRoom.Room;
import com.arch.raon.global.util.enums.GameState;
import com.arch.raon.global.util.enums.RoomResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionarySocketServiceImpl implements DictionarySocketService{

	private final ConcurrentMap<String, Room> rooms = new ConcurrentHashMap<>();
	private final MemberRepository memberRepository;
	private final DictionaryDirectionQuizRepository dictionaryDirectionQuizRepository;
	private final DictionaryInitialQuizRepository dictionaryInitialQuizRepository;

	@Override
	public RoomResult createRoom(String nickname, String roomId) {
		if(isValidUser(nickname)) {
			// 이미 존재하는 방에 대해 방 생성 요청시
			if(rooms.containsKey(roomId)){
				return RoomResult.CREATE_FAIL_SAME_ROOMID;
			}
			
			// 만든 방 id로 방에 입장하고, 방 생성을 요청한 사람을 방장으로 넣는다.
			rooms.put(roomId, new Room(nickname));
			return RoomResult.CREATE_SUCCESS;
		}
		return RoomResult.FAIL_INVALID_USER;
	}

	@Override
	public RoomResult joinRoom(String nickname, String roomId) {
		if(rooms.containsKey(roomId)){
			Room thatRoom = rooms.get(roomId);

			if(thatRoom.getCurrentState().equals(GameState.PLAY)){
				return RoomResult.JOIN_FAIL_PLAYING;
			}
			if(thatRoom.isFull()){
				return RoomResult.JOIN_FAIL_FULL;
			}

			synchronized (thatRoom){
				thatRoom.enter(nickname);
			}
			return RoomResult.JOIN_SUCCESS;
		}
		else{
			return RoomResult.FAIL_NONEXIST_ROOM;
		}
	}

	@Override
	public boolean isValidRoomId(String roomId) {
		return !rooms.containsKey(roomId);
	}

	@Override
	public RoomResult leaveRoom(String nickname, String roomId) {
		if(rooms.containsKey(roomId)){
			rooms.get(roomId).leave(nickname);
			return RoomResult.LEAVE_SUCCESS;
		}
		return RoomResult.LEAVE_FAIL_NONEXIST;
	}

	@Override
	public RoomResult startGame(String roomId, String nickname) {
		if(rooms.containsKey(roomId)){
			String roomOwner = rooms.get(roomId).getOwner();
			if(roomOwner.equals(nickname)){
				rooms.get(roomId).PLAY();

				return RoomResult.GAME_START_SUCCESS;
			}
			else{
				return RoomResult.GAME_START_FAIL_NOT_A_OWNER;
			}
		}
		return RoomResult.FAIL_NONEXIST_ROOM;
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

	public List<SocketResponseDTO> getRoomInfo(String roomId) {
		Room room = rooms.get(roomId);
		return room.getUsers();
	}

	@Override
	public DictionaryQuizResDTO getQuizes() {
		List<DictionaryDirectionQuiz> directionQuizes = dictionaryDirectionQuizRepository.random3();
		List<DictionaryInitialQuiz> initialQuizes = dictionaryInitialQuizRepository.random7();
		return new DictionaryQuizResDTO(initialQuizes, directionQuizes);
	}

}
