import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import JoinButton from '../Atoms/JoinButton';
import StartButton from '../Atoms/StartButton';
import RoomExitButton from '../Atoms/ExitButtonInRoom';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { v4 as uuidv4} from 'uuid';

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

  console.log("방만들기 들어옴")

  // 웹 소켓 클라이언트 설정
  const socket = new SockJS(`${process.env.REACT_APP_API_URL}api/ws`);
  const stompClient = new Client({
    webSocketFactory: () => socket,
    onConnect: () => {
      console.log("connect???")
      const nickname = '박영서';
      const roomId = uuidv4();
      console.log(roomId)
      stompClient.publish({ destination: '/dictionary-quiz/create-room', body: JSON.stringify({nickname, roomId}) });
     // console.log('Connected to the WebSocket server');
      stompClient.subscribe('/topic/dictionary-quiz/create-room/' + roomId, (message) => {
        console.log(message)
      });

    },
  });


  // 컴포넌트 마운트 시 웹 소켓 연결 시작
  useEffect(() => {
    stompClient.activate();
  }, []);


  return (
    <>
      <InterfaceDiv>
        <RoomCurrentDiv>
          <RoomHeadText>방 코드</RoomHeadText>
          <RoomCodeText>AAAA-BBBB-CCCC-DDDD</RoomCodeText>
          <RoomParticipantsText>참가자</RoomParticipantsText>
          <RoomParticipantsText>참가자</RoomParticipantsText>
          <RoomParticipantsText>참가자</RoomParticipantsText>
          <RoomParticipantsText>참가자</RoomParticipantsText>
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
        <StartButton onClick={() => navigate('/game/dictionary-game')} />
        <RoomExitButton />
      </ButtonDiv>
    </>
  );
}

export default WaitInterface;
