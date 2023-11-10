// wsSetting.tsx

import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { useSetRecoilState } from 'recoil';
import { multiDictState } from '../recoil/Atoms';

const useWebSocket = () => {
  //   const [stompClient, setStompClient] = useState<Client | null>(null);
  const navigate = useNavigate();
  const setMultiState = useSetRecoilState(multiDictState);

  const initializeWebSocket = () => {
    const nickname = localStorage.getItem('nickname') ?? '미사용자';
    const roomId = sessionStorage.getItem('roomId') ?? '0000';

    const socket = new SockJS(`${process.env.REACT_APP_API_URL}api/ws`, null, {
      transports: ['websocket', 'xhr-streaming', 'xhr-polling'],
    });

    const client = new Client({
      webSocketFactory: () => socket,

      onConnect: () => {
        if (roomId === '0000') {
          alert('구독한 방 아이디가 없습니다.');
        } else {
          client.subscribe(`/topic/dictionary-quiz/room/${roomId}`, callback);
          client.publish({
            destination: '/dictionary-quiz/connect-room',
            body: JSON.stringify({ nickname, roomId }),
          });
          console.log('Connected to the WebSocket server');
        }
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    const callback: (message: any) => void = (message: any) => {
      if (message.body) {
        const body: any = JSON.parse(message.body);
        const { message: msg } = JSON.parse(message.body);
        console.log(msg);

        switch (msg) {
          case 'ENTER':
            console.log('방 사람 리스트');
            console.log(body);
            break;

          case 'STAGE_START':
            console.log('퀴즈 들어옴');
            console.log(body);
            break;

          case 'DIRECTION_QUIZ':
            console.log('동서남북 퀴즈');
            console.log(body);
            setMultiState((prev) => ({
              ...prev,
              type: 'D',
              id: body.dictionaryDirectionQuiz.id,
              westWord: body.dictionaryDirectionQuiz.westWord,
              northWord: body.dictionaryDirectionQuiz.northWord,
              eastWord: body.dictionaryDirectionQuiz.eastWord,
              southWord: body.dictionaryDirectionQuiz.southWord,
              answer: body.dictionaryDirectionQuiz.answer,
            }));
            break;

          case 'INITIAL_QUIZ':
            console.log('초성퀴즈');
            console.log(body);
            setMultiState((prev) => ({
              ...prev,
              type: 'I',
              id: body.dictionaryInitialQuiz.id,
              initial: body.dictionaryInitialQuiz.initial,
              meaning: body.dictionaryInitialQuiz.meaning,
              word: body.dictionaryInitialQuiz.word,
            }));
            break;

          default:
            console.log(body);
            break;
        }
      }
    };

    const leaveRoom = (): void => {
      console.log('방나가기 요청 보내기');
      client.publish({
        destination: `/dictionary-quiz/leave`,
        body: JSON.stringify({ nickname, roomId }),
      });
      disconnectWebSocket();
    };

    const disconnectWebSocket = (): void => {
      if (client && client.connected) {
        console.log('소켓종료');
        client.deactivate();
      }
    };

    const gameStart = (): void => {
      console.log('게임 시작 요청');
      client.publish({
        destination: `/dictionary-quiz/game-start`,
        body: JSON.stringify({ nickname, roomId }),
      });
    };


    const sendQuizResult = (userAnswer: string, timeSpend: number, stage: number): void => {
      console.log('퀴즈 정답 보내기');
      client.publish({
        destination: `/dictionary-quiz/on-stage`,
        body: JSON.stringify({ nickname, roomId, userAnswer, timeSpend, stage }),
      });
    };


    const createRoom = () => {
      client.activate();
    };

    // useEffect(() => {
    //   if (!stompClient) {
    //     setStompClient(client);
    //     client.activate();
    //   }

    //   return () => {
    //     disconnectWebSocket();
    //   };
    // }, [stompClient]);

    return {
      leaveRoom,
      gameStart,
      createRoom,
      sendQuizResult,
    };
  };

  return { initializeWebSocket };
};

export default useWebSocket;
