package com.arch.raon.domain.dictionary.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.arch.raon.domain.dictionary.dto.SocketVO;
import com.arch.raon.domain.dictionary.service.DictionarySocketService;

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
	 * @param nickname
	 * @return message(roomId)
	 */
	@MessageMapping("/dictionary-quiz/create-room")
	public void createRoom(String nickname, String roomId) {
		SocketVO message = new SocketVO(nickname, roomId);

		if(dictionarySocketService.isValidRoomId(roomId)){
			sendToRoom("/dictionary-quiz/create-room", roomId, message);
		}
		else{
			// TODO: exception에 대한 처리가 더 필요
			message.setMessage("error");
			sendToRoom("/dictionary-quiz/error", roomId, message);
		}
	}


	@MessageMapping("/dictionary-quiz/join-room")
	public void joinRoom(String nickname, String roomId) {

		//boolean joinOk = dictionarySocketService.createRoom(nickname);
		SocketVO message = new SocketVO(nickname, roomId);
		sendToRoom("/dictionary-quiz/join-room", roomId, message);
	}

	@MessageMapping("/dictionary-quiz/leave")
	public void userLeaveRoom(String nickname, String roomId){
		dictionarySocketService.leaveRoom(nickname, roomId);
	}


	// 특정 roomId에 메시지를 전송하는 메서드
	private void sendToRoom(String topic, String roomId, SocketVO message) {
		// roomId를 포함한 토픽 주소로 메시지 전송
		messagingTemplate.convertAndSend("/topic"+topic+"/"+roomId, message);
	}



}
