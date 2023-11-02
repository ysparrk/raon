package com.arch.raon.domain.dictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.arch.raon.domain.dictionary.dto.request.SocketReqDTO;
import com.arch.raon.domain.dictionary.dto.response.SocketResponseDTO;
import com.arch.raon.domain.dictionary.service.DictionarySocketService;
import com.arch.raon.global.util.enums.RoomResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DictionarySocketController {

	private final SimpMessagingTemplate messagingTemplate;
	private final DictionarySocketService dictionarySocketService;

	/**
	 * 방 생성 요청
	 *
	 * 방을 생성하여 방 id를 리턴한다.
	 * 방 생성을 요청한 사람은 방장이 된다.
	 *
	 * @param reqDTO
	 * @return message(roomId)
	 */
	@MessageMapping("/dictionary-quiz/create-room")
	public void createRoom(SocketReqDTO reqDTO) {
		RoomResult result = dictionarySocketService.createRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		switch (result){
			case CREATE_SUCCESS:
				SocketResponseDTO message = new SocketResponseDTO(reqDTO.getNickname(), reqDTO.getRoomId());
				sendToRoom("/dictionary-quiz/create-room", reqDTO.getRoomId(), message);
				break;

			case FAIL_INVALID_USER:
				// TODO: exception에 대한 처리가 더 필요
				break;

			case CREATE_FAIL_SAME_ROOMID:
				// TODO: exception에 대한 처리가 더 필요
				break;
		}
	}


	@MessageMapping("/dictionary-quiz/join-room")
	public void joinRoom(SocketReqDTO reqDTO) {
		RoomResult result = dictionarySocketService.joinRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		switch (result){
			case JOIN_SUCCESS:
				List<String> users = dictionarySocketService.getUserNickNames(reqDTO.getRoomId());
				List<SocketResponseDTO> responseDTOS = new ArrayList<>();

				for(String user:users){
					responseDTOS.add(new SocketResponseDTO(user,null));
				}
				sendToRoom("/dictionary-quiz/join-room", reqDTO.getRoomId(), responseDTOS);
				break;

			case JOIN_FAIL_FULL:
				// TODO: exception에 대한 처리가 더 필요
				break;

			case JOIN_FAIL_PLAYING:
				// TODO: exception에 대한 처리가 더 필요
				break;

			case JOIN_FAIL_NONEXIST:
				// TODO: exception에 대한 처리가 더 필요
				break;
		}
	}

	@MessageMapping("/dictionary-quiz/leave")
	public void userLeaveRoom(String nickname, String roomId){
		RoomResult result = dictionarySocketService.leaveRoom(nickname, roomId);
	}


	// 특정 roomId에 메시지를 전송하는 메서드
	private void sendToRoom(String topic, String roomId, Object message) {
		// roomId를 포함한 토픽 주소로 메시지 전송
		messagingTemplate.convertAndSend("/topic"+topic+"/"+roomId, message);
	}



}
