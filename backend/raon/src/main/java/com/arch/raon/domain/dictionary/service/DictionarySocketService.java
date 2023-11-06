package com.arch.raon.domain.dictionary.service;

import java.util.List;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.SocketResponseDTO;
import com.arch.raon.global.util.enums.RoomResult;

public interface DictionarySocketService {

	/**
	 * 유저가 방을 만든다.
	 *
	 * @param nickname
	 * @param roomId
	 * @return
	 */
	public RoomResult createRoom(String nickname, String roomId);


	/**
	 * 유저가 방에 입장한다.
	 *
	 * @param nickname
	 * @param roomId
	 * @return
	 */
	RoomResult joinRoom(String nickname, String roomId);

	boolean isValidRoomId(String roomId);

	RoomResult leaveRoom(String nickname, String roomId);

	RoomResult startGame(String roomId, String nickname);

	List<SocketResponseDTO> getRoomInfo(String roomId);

	DictionaryQuizResDTO getQuizes();
}
