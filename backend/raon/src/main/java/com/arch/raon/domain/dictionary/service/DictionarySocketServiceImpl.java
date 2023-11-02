package com.arch.raon.domain.dictionary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.quizRoom.Room;
import com.arch.raon.global.quizRoom.User;
import com.arch.raon.global.util.enums.GameState;
import com.arch.raon.global.util.enums.RoomResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionarySocketServiceImpl implements DictionarySocketService{

	private final ConcurrentMap<String, Room> rooms = new ConcurrentHashMap<>();
	private final MemberRepository memberRepository;

	@Override
	public RoomResult createRoom(String nickname, String roomId) {
		if(isValidUser(nickname)) {
			// 만든 방 id로 방에 입장하고, 방 생성을 요청한 사람을 방장으로 넣는다.
			rooms.put(roomId, new Room(nickname));
			return RoomResult.CREATE_SUCCESS;
		}
		return RoomResult.FAIL_INVALID_USER;
	}

	/**
	 * 유저가 방에 입장한다.
	 *
	 * @param nickname
	 * @param roomId
	 * @return
	 */
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
				thatRoom.addUser(nickname);
			}
			return RoomResult.JOIN_SUCCESS;
		}
		else{
			return RoomResult.JOIN_FAIL_NONEXIST;
		}
	}

	@Override
	public boolean isValidRoomId(String roomId) {
		return !rooms.containsKey(roomId);
	}

	@Override
	public RoomResult leaveRoom(String nickname, String roomId) {
		if(rooms.containsKey(roomId)){
			rooms.get(roomId).leaveUser(nickname);
			return RoomResult.LEAVE_SUCCESS;
		}
		return RoomResult.LEAVE_FAIL_NONEXIST;
	}

	@Override
	public List<String> getUserNickNames(String roomId) {
		Room room = rooms.get(roomId);
		return room.getUsers();
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
}
