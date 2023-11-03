package com.arch.raon.domain.dictionary.controller;

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
				SocketResponseDTO message = new SocketResponseDTO(reqDTO.getNickname(), reqDTO.getRoomId(), "방 생성 성공", true);
				sendToRoom(reqDTO.getRoomId(), message);
				break;

			case FAIL_INVALID_USER:
				// TODO: exception에 대한 처리가 더 필요
				break;

			case CREATE_FAIL_SAME_ROOMID:
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
		System.out.println("join요청: 요청자:"+reqDTO.getNickname());

		RoomResult result = dictionarySocketService.joinRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		switch (result){
			case JOIN_SUCCESS:
				// 방 입장 성공시 먼저 입장 한 사람에게 방에 있던 사람들의 정보를 보내준다.
				List<SocketResponseDTO> userAndOwnerInfo = dictionarySocketService.getRoomInfo(reqDTO.getRoomId());
				sendResult(reqDTO.getNickname(), userAndOwnerInfo);

				// 그 뒤 방 전체 사람들에게 입장 한 사람의 정보를 보내준다.(방에 방금 들어온 사람도 자신의 정보를 이때 받는다.)
				sendToRoom(reqDTO.getRoomId(), new SocketResponseDTO(reqDTO.getNickname(), reqDTO.getRoomId(), "나, 등장", false));
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
	public void userLeaveRoom(SocketReqDTO reqDTO){
		RoomResult result = dictionarySocketService.leaveRoom(reqDTO.getNickname(), reqDTO.getRoomId());
	}


	// 특정 방에 있는 "모든 인원"에게 데이터를 보낼 때 (방 입장, 방 나가기, 문제 결과 전송 등)
	private void sendToRoom(String roomId, Object message) {
		// roomId를 포함한 토픽 주소로 메시지 전송
		messagingTemplate.convertAndSend("/topic/dictionary-quiz/room/"+roomId, message);
	}

	// 요청을 보낸 "개인"에게 요청의 결과(성공, 에러 등)를 전송
	private void sendResult(String nickname, Object message){
		messagingTemplate.convertAndSend("/topic/result/"+nickname, message);
	}



}
