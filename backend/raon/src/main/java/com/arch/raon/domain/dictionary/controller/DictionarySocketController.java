package com.arch.raon.domain.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.arch.raon.domain.dictionary.dto.SocketVO;
import com.arch.raon.domain.dictionary.service.DictionarySocketService;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DictionarySocketController {

	private final SimpMessagingTemplate messagingTemplate;
	private final MemberRepository memberRepository;
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
	public void createRoom(String nickname) {
		String roomId = dictionarySocketService.createRoom(nickname);
		SocketVO message = new SocketVO(nickname, roomId);

		if(roomId != null){
			sendToRoom("/dictionary-quiz/create-room", message);
		}
		else{
			message.setMessage("error");
			sendToRoom("/dictionary-quiz/error", message);
		}
	}

	/**
	 *
	 *
	 * @param nickname
	 */
	@MessageMapping("/dictionary-quiz/joinroom")
	public void joinRoom(String nickname, String roomId) {

		//boolean joinOk = dictionarySocketService.createRoom(nickname);


		SocketVO message = new SocketVO(nickname, roomId);



		sendToRoom("/dictionary-quiz/join-room", message);
	}



	// 특정 roomId에 메시지를 전송하는 메서드
	private void sendToRoom(String topic, SocketVO message) {
		// roomId를 포함한 토픽 주소로 메시지 전송
		messagingTemplate.convertAndSend("/topic"+topic, message);
	}



}
