import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import JoinButton from '../Atoms/JoinButton';
import StartButton from '../Atoms/StartButton';
import RoomExitButton from '../Atoms/ExitButtonInRoom';

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
  justify-content: center;
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

  return (
    <>
      <InterfaceDiv>
        <RoomCurrentDiv>
          <RoomHeadText>방 코드</RoomHeadText>
          <RoomCodeText>AAAA-BBBB-CCCC-DDDD</RoomCodeText>
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
        <StartButton />
        <RoomExitButton />
      </ButtonDiv>
    </>
  );
}

export default WaitInterface;
