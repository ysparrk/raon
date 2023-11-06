import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import JoinButton from '../Atoms/JoinButton';
import JoinInputBox from '../Atoms/JoinInputBox';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const InterfaceDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 80vw;
  height: 60vh;
`;

const connectToWebSocket = (nickname: string, roomId: string): Promise<Client> => {
  return new Promise((resolve, reject) => {
    const socket = new SockJS(`${process.env.REACT_APP_API_URL}api/ws`, null, {transports: ["websocket", "xhr-streaming", "xhr-polling"]});
    const client = new Client({
      webSocketFactory: () => socket,
      onConnect: () => {
        joinRoom(client, nickname, roomId);
        resolve(client);
      },
      onStompError: (error) => {
        reject(error);
      },
      reconnectDelay: 5000, //자동 재 연결
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    client.activate();
  });
};

// 입장하는 사용자 닉네임, roomId 보내기
const joinRoom = (client: Client, nickname: string, roomId: string): void => {
  // TODO: subname 소셜로그인 구현 후 입장하는 사용자의 닉네임으로 변경
  const subname = "김태현";
  client.subscribe(`/topic/dictionary-quiz/room/${roomId}`, callback);
  client.subscribe(`/topic/result/${subname}`, callbackJoinList);
  client.publish({
    destination: `/dictionary-quiz/join-room`,
    body: JSON.stringify({nickname, roomId}),
  });
};


// 콜백함수 => roomId 받기
const callback: (message: any) => void = (message: any) => {
  if (message.body) {
    const body: any = JSON.parse(message.body);
    console.log(body);
  }
};

const callbackJoinList: (message: any) => void = (message: any) => {
  console.log("참여 리스트 받기")
  if (message.body) {
    const body: any = JSON.parse(message.body);
    console.log(body);
  }
};


function JoinInterface() {
  const [isJoin, setIsJoin] = useState(false);
  const [inputBoxValue, setInputBoxValue] = useState('');
  const navigate = useNavigate();

  const handleInputChange = (value: string) => {
    setInputBoxValue(value);
  };
  if (!isJoin) {
    return (
      <InterfaceDiv>
        <JoinButton
          optionText="방만들기"
          buttoncolor="gainsboro"
          onClick={() => navigate('/game/dictionary-quiz')}
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
        onClick={async (async) => {
          console.log(inputBoxValue);
          const client = await connectToWebSocket("김태현", inputBoxValue);
          navigate('/game/dictionary-quiz')
        }}
      />
    </InterfaceDiv>
  );
}

export default JoinInterface;
