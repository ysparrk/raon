package com.arch.raon.domain.dictionary.service;

import java.util.List;

import com.arch.raon.domain.dictionary.dto.response.SocketResponseDTO;
import com.arch.raon.global.util.enums.RoomResult;

public interface DictionarySocketService {
	public RoomResult createRoom(String nickname, String roomId);

	public RoomResult joinRoom(String nickname, String roomId);

	public boolean isValidRoomId(String roomId);

	public RoomResult leaveRoom(String nickname, String roomId);

	public List<SocketResponseDTO> getRoomInfo(String roomId);
}
