package com.arch.raon.domain.dictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.arch.raon.domain.dictionary.dto.request.SocketReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.SocketJoinResDTO;
import com.arch.raon.domain.dictionary.dto.response.SocketLeaveResDTO;
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
		System.out.println("[CREATE-ROOM] 방 생성 요청!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());

		RoomResult result = dictionarySocketService.createRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		switch (result){
			case CREATE_SUCCESS:
				SocketResponseDTO message = new SocketResponseDTO(reqDTO.getNickname(), reqDTO.getRoomId(), "방 생성 성공", true);
				sendToRoom(reqDTO.getRoomId(), message);
				break;

			case FAIL_INVALID_USER:
				System.err.println("[CREATE-FAIL] 방 생성 실패 : 존재하지 않는 사용자");

				// TODO: exception에 대한 처리가 더 필요
				break;

			case CREATE_FAIL_SAME_ROOMID:
				System.err.println("[CREATE-FAIL] 방 생성 실패 : 이미 존재하는 방 아이디");
				// TODO: exception에 대한 처리가 더 필요
				break;
		}
	}

	/**
	 * 사용자가 방에 입장한다.
	 *
	 * 입장에 성공하면 방에 있는 사람들에게 업데이트 된 방 인원의 정보를 전달한다.
	 *
	 * @param reqDTO
	 */
	@MessageMapping("/dictionary-quiz/join-room")
	public void joinRoom(SocketReqDTO reqDTO) {
		System.out.println("[JOIN-ROOM] 방 참가 요청!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());

		RoomResult result = dictionarySocketService.joinRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		switch (result){
			case JOIN_SUCCESS:
				List<String> users = dictionarySocketService.getRoomInfo(reqDTO.getRoomId());
				String owner = dictionarySocketService.getOwner(reqDTO.getRoomId());

				// 그 뒤 방 전체 사람들에게 입장 한 사람의 정보를 보내준다.(방에 방금 들어온 사람도 자신의 정보를 이때 받는다.)
				sendToRoom(reqDTO.getRoomId(), new SocketJoinResDTO(reqDTO.getNickname(), owner, users));
				break;

			case JOIN_FAIL_FULL:
				// TODO: exception에 대한 처리가 더 필요
				break;

			case JOIN_FAIL_PLAYING:
				// TODO: exception에 대한 처리가 더 필요
				break;

			case FAIL_NONEXIST_ROOM:
				// TODO: exception에 대한 처리가 더 필요
				break;
		}
	}

	@MessageMapping("/dictionary-quiz/leave")
	public void userLeaveRoom(SocketReqDTO reqDTO){
		System.out.println("[LEAVE-ROOM] 방 나가기 요청!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());

		// 일단 방 자료구조에서 제외
		RoomResult result = dictionarySocketService.leaveRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		// 방 내의 사람들에게 나갔다는 것을 알림.
		if(result == RoomResult.LEAVE_SUCCESS){
			String owner = dictionarySocketService.getOwner(reqDTO.getRoomId());
			sendToRoom(reqDTO.getRoomId(), new SocketLeaveResDTO(reqDTO.getNickname(),owner));
		}
	}

	@MessageMapping("/dictionary-quiz/game-start")
	public void startGame(SocketReqDTO reqDTO){
		System.out.println("==== 게임 시작 요청 : "+ reqDTO);

		RoomResult result = dictionarySocketService.startGame(reqDTO.getRoomId(), reqDTO.getNickname());

		switch(result){
			case GAME_START_SUCCESS:
				DictionaryQuizResDTO quizes = dictionarySocketService.getQuizes();
				// 퀴즈의 정답을 room에 넣어야 한다 재원아
				dictionarySocketService.addQuizToRoom(quizes, reqDTO.getRoomId());

				sendToRoom(reqDTO.getRoomId(), quizes);
				break;
			case GAME_START_FAIL_NOT_A_OWNER:
				// TODO: 예외 처리 할 것
				break;
		}
	}




	// 특정 방에 있는 "모든 인원"에게 데이터를 보낼 때 (방 입장, 방 나가기, 문제 결과 전송 등)
	private void sendToRoom(String roomId, Object message) {
		System.out.println("==== 요청 성공 : "+ roomId +" 메세지(주소값만 뜰 수 있음) : " + message);
		// roomId를 포함한 토픽 주소로 메시지 전송
		messagingTemplate.convertAndSend("/topic/dictionary-quiz/room/"+roomId, message);
	}

	// 에러 전송용으로 사용 할 것.
	private void sendResult(String nickname, Object message){
		System.out.println("==== 방 입장 성공, 방에 있는 애들 : "+ nickname +" 메세지(주소값만 뜰 수 있음) : " + message);
		messagingTemplate.convertAndSend("/topic/result/"+nickname, message);
	}



}
