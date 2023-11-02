package com.arch.raon.domain.dictionary.service;

public interface DictionarySocketService {
	public String createRoom(String nickname);

	public boolean joinRoom(String nickname, String roomId);

	boolean isValidRoomId(String roomId);

	void leaveRoom(String nickname, String roomId);
}
