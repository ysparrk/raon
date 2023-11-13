// WebSocketContext.tsx
import React, { createContext, useContext, useEffect } from 'react';
import { Client, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { useSetRecoilState } from 'recoil';
import {
  multiDictState,
  gameStartState,
  roomManageState,
} from '../recoil/Atoms';

interface WebSocketContextProps {
  leaveRoom: () => void;
  gameStart: () => void;
  createRoom: () => void;
  sendQuizResult: (
    userAnswer: string,
    timeSpend: number,
    stage: number,
  ) => void;
  checkStatus: () => void;
}

const WebSocketContext = createContext<WebSocketContextProps | undefined>(
  undefined,
);

export const WebSocketProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const setMultiState = useSetRecoilState(multiDictState);
  const setGameStart = useSetRecoilState(gameStartState);
  const setRoomStatus = useSetRecoilState(roomManageState);

  useEffect(() => {
    // Initialize WebSocket connection here if needed
    return () => {
      // Clean up WebSocket connection when the component unmounts
    };
  }, []);

  const initializeWebSocket = () => {
    const nickname = localStorage.getItem('nickname') ?? '미사용자';
    let roomId = sessionStorage.getItem('roomId') ?? '0000';
    // const socket = new SockJS(`${process.env.REACT_APP_API_URL}api/ws`, null, {
    //   transports: ['websocket', 'xhr-streaming', 'xhr-polling'],
    // });

    const client = new Client({
      webSocketFactory: () =>
        new SockJS(`${process.env.REACT_APP_API_URL}api/ws`, null, {
          transports: ['websocket', 'xhr-streaming', 'xhr-polling'],
        }),
      onWebSocketError: (error) => {
        console.log('에러임');
        console.log(error);
      },
      debug: (str) => {
        console.log(str);
      },
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
      onStompError: (error) => {
        console.log(error);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    const callback = (message: IMessage) => {
      if (message.body) {
        const body: any = JSON.parse(message.body);
        const { message: msg } = JSON.parse(message.body);
        console.log(msg);

        switch (msg) {
          case 'ENTER':
            console.log('방 사람 리스트');
            console.log(body);
            setRoomStatus((prev) => ({
              ...prev,
              users: body.users,
            }));

            break;

          case 'STAGE_START':
            console.log('방장이 게임 시작');
            console.log(body);
            setGameStart(true);
            break;

          case 'DIRECTION_QUIZ':
            console.log('동서남북 퀴즈');
            console.log(body);
            setRoomStatus((prev) => ({
              ...prev,
              breakTime: false,
            }));
            setMultiState((prev) => ({
              ...prev,
              type: 'D',
              stage: body.stage,
              id: body.dictionaryDirectionQuiz.id,
              westWord: body.dictionaryDirectionQuiz.westWord,
              northWord: body.dictionaryDirectionQuiz.northWord,
              eastWord: body.dictionaryDirectionQuiz.eastWord,
              southWord: body.dictionaryDirectionQuiz.southWord,
              answer: body.answer,
            }));
            break;

          case 'INITIAL_QUIZ':
            console.log('초성퀴즈');
            console.log(body);
            setRoomStatus((prev) => ({
              ...prev,
              breakTime: false,
            }));
            setMultiState((prev) => ({
              ...prev,
              type: 'I',
              stage: body.stage,
              id: body.dictionaryInitialQuiz.id,
              initial: body.dictionaryInitialQuiz.initial,
              meaning: body.dictionaryInitialQuiz.meaning,
              word: body.dictionaryInitialQuiz.word,
              answer: body.answer,
            }));
            break;
          case 'STAGE_RESULT':
            console.log(body);
            setRoomStatus((prev) => ({
              ...prev,
              userResult: body.users,
              breakTime: true,
            }));
            break;
          case 'LEAVE':
            console.log(body);
            setRoomStatus((prev) => ({
              ...prev,
              users: body.users,
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

    const sendQuizResult = (
      userAnswer: string,
      timeSpend: number,
      stage: number,
    ): void => {
      console.log('퀴즈 정답 보내기');
      client.publish({
        destination: `/dictionary-quiz/on-stage`,
        body: JSON.stringify({
          nickname,
          roomId,
          userAnswer,
          timeSpend,
          stage,
        }),
      });
    };

    const createRoom = async () => {
      roomId = (await sessionStorage.getItem('roomId')) ?? '0000';
      client.activate();
    };

    const checkStatus = () => {
      console.log(client);
    };

    return {
      leaveRoom,
      gameStart,
      createRoom,
      sendQuizResult,
      checkStatus,
    };
  };

  return (
    <WebSocketContext.Provider value={initializeWebSocket()}>
      {children}
    </WebSocketContext.Provider>
  );
};

export const useWebSocket = () => {
  const context = useContext(WebSocketContext);
  if (!context) {
    throw new Error('useWebSocket must be used within a WebSocketProvider');
  }
  return context;
};
