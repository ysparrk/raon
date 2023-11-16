import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { v4 as uuidv4 } from 'uuid';
import { useSetRecoilState } from 'recoil';
import { postRoomId } from '../../../api/GameRoomCreateApi';
import JoinButton from '../Atoms/JoinButton';
import JoinInputBox from '../Atoms/JoinInputBox';
import { roomManageState } from '../../../recoil/Atoms';
import { useWebSocket } from '../../../websocket/WebSocketContext';

const InterfaceDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 80vw;
  height: 60vh;
`;

const nickname = localStorage.getItem('nickname') ?? '미사용자';

// const connectToWebSocket = (
//   nicknameInput: string,
//   roomId: string,
// ): Promise<Client> => {
//   return new Promise((resolve, reject) => {
//     const socket = new SockJS(`${process.env.REACT_APP_API_URL}api/ws`, null, {
//       transports: ['websocket', 'xhr-streaming', 'xhr-polling'],
//     });
//     const client = new Client({
//       webSocketFactory: () => socket,
//       onConnect: () => {
//         joinRoom(client, nicknameInput, roomId);
//         resolve(client);
//       },
//       onStompError: (error) => {
//         reject(error);
//       },
//       reconnectDelay: 5000, // 자동 재 연결
//       heartbeatIncoming: 4000,
//       heartbeatOutgoing: 4000,
//     });

//     client.activate();
//   });
// };

// 입장하는 사용자 닉네임, roomId 보내기
// const joinRoom = (
//   client: Client,
//   nicknameInput: string,
//   roomId: string,
// ): void => {
//   client.subscribe(`/topic/dictionary-quiz/room/${roomId}`, callback);
//   client.publish({
//     destination: `/dictionary-quiz/join-room`,
//     body: JSON.stringify({ nicknameInput, roomId }),
//   });
//   sessionStorage.setItem('roomId', roomId); // 세션에 roomId 저장
// };

// // 콜백함수 => roomId 받기
// const callback: (message: any) => void = (message: any) => {
//   if (message.body) {
//     const body: any = JSON.parse(message.body);
//     console.log(body);
//   }
// };

function JoinInterface() {
  const [isJoin, setIsJoin] = useState(false);
  const [inputBoxValue, setInputBoxValue] = useState('');
  const navigate = useNavigate();
  const setManagerState = useSetRecoilState(roomManageState);
  const Stomp = useWebSocket();
  const roomId = uuidv4().slice(0,8);

  const handleCreateClick = async (
    nicknameInput: string,
    roomIdInput: string,
  ) => {
    try {
      const response = await postRoomId(nicknameInput, roomIdInput);
      if (response) {
        console.log('handle 들어옴');
        console.log(response.data.data.roomIdExist);
        const { roomIdExist } = response.data.data;

        if (roomIdExist) {
          alert('방 아이디 중복');
        } else {
          setManagerState((prev) => ({
            ...prev,
            manager: true,
          }));

          sessionStorage.setItem('roomId', roomIdInput);
          navigate('/game/dictionary-quiz');
        }
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleJoinClick = async (
    nicknameInput: string,
    roomIdInput: string,
  ) => {
    try {
      const response = await postRoomId(nicknameInput, roomIdInput);
      if (response) {
        console.log('handle 들어옴');
        console.log(response.data.data.roomIdExist);
        const { roomIdExist } = response.data.data;
        console.log(response);

        if (!roomIdExist) {
          alert('존재하지 않는 방');
        } else {
          console.log('세션에 roomId 저장');
          // 방 아이디 만들어지면 이동 및 세션에 roomId 저장
          sessionStorage.setItem('roomId', roomIdInput);
          navigate('/game/dictionary-quiz');
        }
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleInputChange = (value: string) => {
    setInputBoxValue(value);
  };
  if (!isJoin) {
    return (
      <InterfaceDiv>
        <JoinButton
          optionText="방만들기"
          buttoncolor="gainsboro"
          onClick={() => handleCreateClick(nickname, roomId)}
        />
        <JoinButton
          optionText="참여하기"
          buttoncolor="cornflowerblue"
          onClick={() => setIsJoin(true)}
        />
      </InterfaceDiv>
    );
  }
  return (
    <InterfaceDiv>
      <JoinInputBox onInputChange={handleInputChange} />
      <JoinButton
        optionText="참여하기"
        buttoncolor="lightcoral"
        onClick={() => handleJoinClick(nickname, inputBoxValue)}
      />
    </InterfaceDiv>
  );
}

export default JoinInterface;
