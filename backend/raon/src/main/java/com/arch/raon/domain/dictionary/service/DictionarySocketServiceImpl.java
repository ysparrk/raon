package com.arch.raon.domain.dictionary.service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.arch.raon.domain.dictionary.controller.DictionaryController;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.quizRoom.Room;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionarySocketServiceImpl implements DictionarySocketService{

	private final ConcurrentMap<String, Room> rooms = new ConcurrentHashMap<>();
	private final MemberRepository memberRepository;

	@Override
	public String createRoom(String nickname) {
		if(isValidUser(nickname)) {

			// 방 id를 만든다.
			String roomId = createRoomId();

			// 만든 방 id로 방에 입장하고, 방 생성을 요청한 사람을 방장으로 넣는다.
			rooms.put(roomId, new Room(nickname));

			return roomId;
		}

		return null;
	}

	@Override
	public boolean joinRoom(String nickname, String roomId) {
		return false;
	}

	@Override
	public boolean isValidRoomId(String roomId) {
		return !rooms.containsKey(roomId);
	}

	@Override
	public void leaveRoom(String nickname, String roomId) {
		if(rooms.containsKey(roomId)){
			rooms.get(roomId).leaveUser(nickname);
		}
	}

	private String createRoomId() {
		String roomId = null;

		do {
			roomId = UUID.randomUUID().toString();
		} while (!rooms.containsKey(roomId));

		return roomId;
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
