package com.arch.raon.domain.dictionary.service;

import com.arch.raon.global.util.enums.RoomResult;

public interface DictionarySocketService {
	public RoomResult createRoom(String nickname, String roomId);

	public RoomResult joinRoom(String nickname, String roomId);

	public boolean isValidRoomId(String roomId);

	public RoomResult leaveRoom(String nickname, String roomId);
}
