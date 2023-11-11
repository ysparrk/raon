import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { v4 as uuidv4 } from 'uuid';
import constructWithOptions from 'styled-components/dist/constructors/constructWithOptions';
import { useSetRecoilState } from 'recoil';
import JoinButton from '../Atoms/JoinButton';
import StartButton from '../../Common/Atoms/StartButton';
import RoomExitButton from '../../Common/Atoms/ExitButtonInRoom';
import { multiDictState } from '../../../recoil/Atoms';
import { useWebSocket } from '../../../websocket/WebSocketContext';

const InterfaceDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 80vw;
  height: 60vh;
`;

const RoomCurrentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  padding-top: 100px;
  align-items: center;
  gap: 20px;
  width: 600px;
  margin: 50px;
`;

const RoomHeadText = styled.div`
  font-size: 80px;
  font-family: 'CookieRun';
  color: white;
`;

const RoomCodeText = styled.div`
  font-size: 40px;
  font-family: 'CookieRun';
  color: white;
`;

const RoomParticipantsText = styled.div`
  font-size: 35px;
  font-family: 'ONE-Mobile-POP';
  color: #ffcd4a;
`;

const ButtonDiv = styled.div`
  position: absolute;
  display: flex;
  gap: 30px;
  justify-content: center;
  align-items: center;
  bottom: 6.5%;
  right: 10%;
`;

function WaitInterface() {
  const [participants, setParticipants] = useState([]);
  const navigate = useNavigate();
  const setMultiState = useSetRecoilState(multiDictState);
  const Stomp = useWebSocket();

  // const nickname = localStorage.getItem('nickname') ?? '미사용자';
  const roomId = sessionStorage.getItem('roomId') ?? '0000'; // 세션에서 roomId 가져오기, 기본값 0000

  // // 웹 소켓 클라이언트 설정
  // const socket = new SockJS(`${process.env.REACT_APP_API_URL}api/ws`, null, {
  //   transports: ['websocket', 'xhr-streaming', 'xhr-polling'],
  // });
  // const stompClient = new Client({
  //   webSocketFactory: () => socket,

  //   onConnect: () => {
  //     // 구독 시작
  //     // 서버로 메시지 보내기
  //     if (roomId === '0000') {
  //       alert('구독한 방 아이디가 없습니다.');
  //     } else {
  //       stompClient.subscribe(
  //         `/topic/dictionary-quiz/room/${roomId}`,
  //         callback,
  //       );
  //       stompClient.publish({
  //         destination: '/dictionary-quiz/connect-room',
  //         body: JSON.stringify({ nickname, roomId }),
  //       });
  //       console.log('Connected to the WebSocket server');
  //     }
  //   },
  //   reconnectDelay: 5000, // 자동 재 연결
  //   heartbeatIncoming: 4000,
  //   heartbeatOutgoing: 4000,
  // });

  // // 콜백함수 => roomId 받기
  // const callback: (message: any) => void = (message: any) => {
  //   if (message.body) {
  //     const body: any = JSON.parse(message.body);
  //     const { message: msg } = JSON.parse(message.body);
  //     console.log(msg);

  //     switch (msg) {
  //       case 'ENTER':
  //         console.log('방 사람 리스트');
  //         // const gameParticipants = users.map((user: any) => user);
  //         // setParticipants(gameParticipants);
  //         console.log(body);
  //         break;

  //       case 'STAGE_START':
  //         console.log('퀴즈 들어옴');
  //         console.log(body);
  //         break;

  //       case 'DIRECTION_QUIZ':
  //         console.log('동서남북 퀴즈');
  //         console.log(body);
  //         setMultiState((prev) => ({
  //           ...prev,
  //           type: 'D',
  //           id: body.dictionaryDirectionQuiz.id,
  //           westWord: body.dictionaryDirectionQuiz.westWord,
  //           northWord: body.dictionaryDirectionQuiz.northWord,
  //           eastWord: body.dictionaryDirectionQuiz.eastWord,
  //           southWord: body.dictionaryDirectionQuiz.southWord,
  //           answer: body.dictionaryDirectionQuiz.answer,
  //         }));
  //         break;

  //       case 'INITIAL_QUIZ':
  //         console.log('초성퀴즈');
  //         console.log(body);
  //         setMultiState((prev) => ({
  //           ...prev,
  //           type: 'I',
  //           id: body.dictionaryInitialQuiz.id,
  //           initial: body.dictionaryInitialQuiz.initial,
  //           meaning: body.dictionaryInitialQuiz.meaning,
  //           word: body.dictionaryInitialQuiz.word,
  //         }));
  //         break;

  //       default:
  //         console.log(body);
  //         break;
  //     }
  //   }
  // };

  // // 방을 나가는 사용자 닉네임, roomId 보내기
  // const leaveRoom = (
  //   client: Client,
  //   nicknameInput: string,
  //   roomIdInput: string,
  // ): void => {
  //   console.log('방나가기 요청 보내기');
  //   client.publish({
  //     destination: `/dictionary-quiz/leave`,
  //     body: JSON.stringify({ nicknameInput, roomIdInput }),
  //   });
  // };

  // // 웹소켓 연결 종료
  // const disconnectWebSocket = (client: Client): void => {
  //   if (client && client.connected) {
  //     console.log('소켓종료');
  //     client.deactivate();
  //   }
  // };

  // 방장이 게임 시작 요청 보내기
  // const gameStart = (
  //   client: Client,
  //   nicknameInput: string,
  //   roomIdInput: string,
  // ): void => {
  //   console.log('게임 시작 요청');
  //   client.publish({
  //     destination: `/dictionary-quiz/game-start`,
  //     body: JSON.stringify({ nicknameInput, roomIdInput }),
  //   });
  // };

  // // 컴포넌트 마운트 시 웹 소켓 연결 시작
  // useEffect(() => {
  //   stompClient.activate();
  // }, []);

  useEffect(() => {
    Stomp.createRoom();
  }, []);

  return (
    <>
      <InterfaceDiv>
        <RoomCurrentDiv>
          <RoomHeadText>방 코드</RoomHeadText>
          <RoomCodeText>{roomId}</RoomCodeText>
          {/* {participants.reverse().map((participant, index) => (
          <RoomParticipantsText key={index}>{participant}</RoomParticipantsText>
        ))} */}
        </RoomCurrentDiv>
        <JoinButton
          optionText="초대하기"
          buttoncolor="gold"
          onClick={() => {
            console.log('test');
          }}
        />
      </InterfaceDiv>
      <ButtonDiv>
        <StartButton
          content="시작하기"
          onClick={() => {
            // if (stompClient) {
            console.log('게임 시작 버튼');
            Stomp.gameStart();
            // gameStart(nickname, roomId);
            // }
            navigate('/game/dictionary-multi-game');
          }}
        />
        <RoomExitButton
          onClick={() => {
            // if (stompClient) {
            Stomp.leaveRoom();
            // leaveRoom(nickname, roomId);
            sessionStorage.removeItem('roomId');
            // }
            navigate('/main');
          }}
        />
      </ButtonDiv>
    </>
  );
}

export default WaitInterface;
