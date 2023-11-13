package com.arch.raon.domain.dictionary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arch.raon.domain.dictionary.dto.request.SocketQuizReqDTO;
import com.arch.raon.domain.dictionary.dto.request.SocketReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryRoomResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketJoinResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketLeaveResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketQuizDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketSignalResDTO;
import com.arch.raon.domain.dictionary.dto.response.socket.SocketStageResultResDTO;
import com.arch.raon.domain.dictionary.service.DictionarySocketService;
import com.arch.raon.domain.dictionary.vo.Rooms;
import com.arch.raon.global.dto.ResponseDTO;
import com.arch.raon.global.util.enums.RoomResult;
import com.arch.raon.global.util.enums.SocketResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DictionarySocketController {

	private final SimpMessagingTemplate messagingTemplate;
	private final DictionarySocketService dictionarySocketService;

	/**
	 * roomId가 유효한지 체크하는 HTTP 요청에 대한 응답이다.
	 *
	 * 프론트에서
	 * 	방 생성하기 -> 여기로 요청을 보내서 roomId가 올바른지 확인. 올바르면 다른 요청들과 겹치는걸 방지하기 위해
	 * 				 미리 방을 만들어야 한다. 그 뒤 다른 페이지로 넘어가고, 거기서 소켓 연결(join)을 해야 한다.
	 *
	 * 	방 참가하기 -> 여기로 요청을 보내서 존재하는 방인지 확인, 이때는 방 생성하기랑 반대로 판단해야 한다. 마찬가지로
	 * 				 방이 존재하면 넘어가서 join해야 한다.
	 *
	 * @param reqDTO
	 * @return
	 */
	@PostMapping("/dictionary-quiz/check-room")
	public ResponseEntity<ResponseDTO> ckeckRoomIdExist(@RequestBody SocketReqDTO reqDTO) {
		System.out.println("[CHECK-ROOM] 방 확인 요청!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());
		DictionaryRoomResDTO roomResDTO = new DictionaryRoomResDTO(Rooms.hasRoomThatIdIs(reqDTO.getRoomId()));
		System.out.println("[CHECK-ROOM-RESULT] 방 결과 : " + roomResDTO);
		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDTO.builder()
				.message("국어사전 퀴즈 방 존재 여부")
				.data(roomResDTO)
				.build()
			);
	}

	/**
	 * 방에 연결한다.
	 *
	 * 만약 Rooms에 해당 roomId가 없으면 방을 생성한다.
	 *
	 * 즉
	 * roomId가 존재하면 -> 참가
	 * roomId가 존재하지 않으면 -> 생성
	 * @param reqDTO
	 */
	@MessageMapping("/dictionary-quiz/connect-room")
	public void connectToRoom(SocketReqDTO reqDTO) {
		System.out.println("[CONNECT-ROOM] 방 연결 요청!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());

		RoomResult result = dictionarySocketService.connectRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		switch (result){
			case JOIN_SUCCESS:
			case CREATE_SUCCESS:
				SocketJoinResDTO socketJoinResDTO = Rooms.getUsersOf(reqDTO.getRoomId());
				socketJoinResDTO.setMessage(SocketResponse.ENTER);
				socketJoinResDTO.setNewComer(reqDTO.getNickname());

				sendToRoom(reqDTO.getRoomId(), socketJoinResDTO);
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

	@MessageMapping("/dictionary-quiz/leave")
	public void userLeaveRoom(SocketReqDTO reqDTO){
		System.out.println("[LEAVE-ROOM] 방 나가기 요청!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());

		RoomResult result = dictionarySocketService.leaveRoom(reqDTO.getNickname(), reqDTO.getRoomId());

		switch(result) {
			case LEAVE_SUCCESS:
				String nextOwner = Rooms.getOwnerOf(reqDTO.getRoomId());
				sendToRoom(reqDTO.getRoomId(), new SocketLeaveResDTO(reqDTO.getNickname(), nextOwner, SocketResponse.LEAVE));
				break;
			case LEAVE_FAIL_NONEXIST:
				System.out.println("ERROR");
				break;
		}
	}

	@MessageMapping("/dictionary-quiz/game-start")
	public void startGame(SocketReqDTO reqDTO) throws InterruptedException {
		System.out.println("[GAME-START] 게임 시작 요청!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());
		RoomResult result = dictionarySocketService.startGame(reqDTO.getRoomId(), reqDTO.getNickname());
		System.out.println("result : " + result);

		switch(result){
			case GAME_START_SUCCESS:
				// 1. 퀴즈를 뽑아 방에 넣는다. 넣을 때 방 객체가 알아서 섞어준다.
				DictionaryQuizResDTO quizes = dictionarySocketService.getQuizes();
				dictionarySocketService.addQuizToRoom(quizes, reqDTO.getRoomId());

				sendToRoom(reqDTO.getRoomId()
					, new SocketSignalResDTO(SocketResponse.STAGE_START)
				);										// 2. 게임을 준비하라는 메세지를 보낸다.

				Thread.sleep(5000); 				// 3. 3초를 쉰다.

				SocketQuizDTO firstQuiz = dictionarySocketService.getNextQuizFrom(reqDTO.getRoomId());
				sendToRoom(reqDTO.getRoomId(), firstQuiz); // 4. 해당 방의 첫 번째 퀴즈를 리턴한다.

				break;
			case GAME_START_FAIL_NOT_A_OWNER:
				// TODO: 예외 처리 할 것
				break;


		}
	}

	/**
	 * 유저가 게임을 하면서 보낸 답을 체크한다.
	 * 프론트에서 방의 모든 유저가 답을 보내면 통계를 내보내고,
	 * 약 7초 뒤에 다음 문제를 보낸다.
	 */
	@MessageMapping("/dictionary-quiz/on-stage")
	public void onStage(SocketQuizReqDTO reqDTO) throws InterruptedException {
		System.out.println("[ON-STAGE] 정답 보냄!!!! 요청자: " + reqDTO.getNickname() +" 방 아이디: "+ reqDTO.getRoomId());
		RoomResult result = dictionarySocketService.addAnswerToRoom(reqDTO);

		System.out.println("[ON-STAGE-RES] 결과 :" + result);

		switch(result){
			case GAME_STAGE_DATA_SEND_COMPLETE:
				break; // 유저가 정답을 잘 보냈을 떄
			
			case STAGE_END: // 모든 유저가 정답을 보냈을 떄
				SocketStageResultResDTO ranking = dictionarySocketService.getStageResultOf(reqDTO.getRoomId());
				sendToRoom(reqDTO.getRoomId(), ranking);

				Thread.sleep(5000); // 이 시간동안 결과를 프론트에서 띄어줘야 하므로, 테스트를 위해 5초로 둔다.

				SocketQuizDTO nextQuiz = dictionarySocketService.getNextQuizFrom(reqDTO.getRoomId());
				sendToRoom(reqDTO.getRoomId(), nextQuiz);
				break;

			case GAME_END: // 모든 유저가 마지막 스테이지의 정답을 보냈을 떄
				SocketStageResultResDTO finalRanking = dictionarySocketService.getStageResultOf(reqDTO.getRoomId());
				finalRanking.setMessage(SocketResponse.FINAL_RESULT);

				// TODO : 결과를 db에 저장해야 한다.
				
				// 최종 결과 전송
				sendToRoom(reqDTO.getRoomId(), finalRanking);
		}
	}


	// 특정 방에 있는 "모든 인원"에게 데이터를 보낼 때 (방 입장, 방 나가기, 문제 결과 전송 등)
	private void sendToRoom(String roomId, Object data) {
		System.out.println("==== 요청 성공 : "+ roomId +" 메세지(주소값만 뜰 수 있음) : " + data);
		// roomId를 포함한 토픽 주소로 메시지 전송
		messagingTemplate.convertAndSend("/topic/dictionary-quiz/room/"+roomId, data);
	}
}
