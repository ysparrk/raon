// WebSocketContext.tsx
import React, { createContext, useContext, useState } from 'react';
import { Client, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { useSetRecoilState, useRecoilState } from 'recoil';
import { toast, ToastContainer } from 'react-toastify';
import {
  multiDictState,
  gameStartState,
  roomManageState,
} from '../recoil/Atoms';
import 'react-toastify/dist/ReactToastify.css';

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
  let finish = false;
  const initializeWebSocket = () => {
    let nickname = localStorage.getItem('nickname') ?? '미사용자';
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
      // debug: (str) => {
      //   console.log(str);
      // },
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
            finish = false;
            setRoomStatus((prev) => ({
              ...prev,
              users: body.users,
            }));
            toast(`${body.newComer} 님이 참여했습니다!`, {
              toastId: body.newComer,
              pauseOnHover: false,
              autoClose: 3000,
            });

            break;

          case 'STAGE_START':
            setGameStart(true);
            break;

          case 'DIRECTION_QUIZ':
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
            setRoomStatus((prev) => ({
              ...prev,
              userResult: body.users,
              breakTime: true,
            }));
            break;
          case 'LEAVE':
            setRoomStatus((prev) => ({
              ...prev,
              users: body.lefts,
            }));
            if (body.nextOwner === nickname && !finish) {
              setRoomStatus((prev) => ({
                ...prev,
                manager: true,
              }));
              toast.success(`방장이 됐습니다!`, {
                toastId: body.nextOwner,
                pauseOnHover: true,
                autoClose: 4000,
              });
            }
            break;
          case 'FINAL_RESULT':
            setRoomStatus((prev) => ({
              ...prev,
              userResult: body.users,
              isFinish: true,
            }));
            setGameStart(false);
            finish = true;
            break;
          default:
            console.log(body);
            break;
        }
      }
    };

    const leaveRoom = (): void => {
      client.publish({
        destination: `/dictionary-quiz/leave`,
        body: JSON.stringify({ nickname, roomId }),
      });
      disconnectWebSocket();
    };

    const disconnectWebSocket = (): void => {
      if (client && client.connected) {
        client.deactivate();
      }
    };

    const gameStart = (): void => {
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
      nickname = (await localStorage.getItem('nickname')) ?? '미사용자';
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
