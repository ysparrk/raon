import React, { useState } from 'react';
import styled from 'styled-components';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { useNavigate } from 'react-router-dom';


const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

// 방을 나가는 사용자 닉네임, roomId 보내기
const leaveRoom = (client: Client, nickname: string, roomId: string): void => {
  client.publish({
    destination: `/dictionary-quiz/leave`,
    body: JSON.stringify({nickname, roomId}),
  });
};

// 웹소켓 연결 종료
const disconnectWebSocket = (client: Client): void => {
  if (client && client.connected) {
    client.deactivate();
  }
};


const DictionaryWaitingRoom = () => {
  const [client, setClient] = useState<Client | null>(null); // 웹소켓 클라이언트 상태
  const [nickname, setNickname] = useState(''); // 사용자 닉네임 상태
  const [roomId, setRoomId] = useState(''); // 방 ID 상태
  const navigate = useNavigate();

  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>대기실</ContentDiv>
      {/* <ExitButton to="/game/dictionary-quiz" /> */}
      <ExitButton onClick={() => {
        console.log(client)
        if (client) {
          console.log("나가기 버튼 누름")
          leaveRoom(client, nickname, roomId);
          disconnectWebSocket(client);
        }
        navigate("/game/dictionary-join");
      }} />
    </div>
  );
};

export default DictionaryWaitingRoom;
