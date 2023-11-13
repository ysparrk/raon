import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { v4 as uuidv4 } from 'uuid';
import constructWithOptions from 'styled-components/dist/constructors/constructWithOptions';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import JoinButton from '../Atoms/JoinButton';
import StartButton from '../../Common/Atoms/StartButton';
import RoomExitButton from '../../Common/Atoms/ExitButtonInRoom';
import {
  multiDictState,
  gameStartState,
  roomManageState,
} from '../../../recoil/Atoms';
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
const RoomMyText = styled.div`
  font-size: 35px;
  font-family: 'ONE-Mobile-POP';
  color: #c4dc23;
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

declare global {
  interface Window {
    Kakao: any;
  }
}

function WaitInterface() {
  const { Kakao } = window;
  const navigate = useNavigate();
  const setMultiState = useSetRecoilState(multiDictState);
  const setManagerState = useSetRecoilState(roomManageState);
  const roomStatus = useRecoilValue(roomManageState);
  const Stomp = useWebSocket();

  const nickname = localStorage.getItem('nickname') ?? '미사용자';
  let roomId = sessionStorage.getItem('roomId') ?? '0000';
  const gameStart = useRecoilValue(gameStartState);

  useEffect(() => {
    setTimeout(() => {
      Stomp.createRoom();
    }, 100);
  }, []);

  useEffect(() => {
    if (gameStart) {
      navigate('/game/dictionary-multi-game');
    }
  }, [gameStart]);

  useEffect(() => {
    Kakao.cleanup();
    Kakao.init(process.env.REACT_APP_JAVASCRIPT_KEY);
    console.log(Kakao.isInitialized());
  });

  const handleshare = async () => {
    roomId = (await sessionStorage.getItem('roomId')) ?? '0000';
    Kakao.Share.sendDefault({
      objectType: 'text',
      text: roomId,
      link: {
        webUrl: 'https://arch-raon.com',
      },
      buttons: [
        {
          title: '게임 참여하러 가기!!',
          link: {
            webUrl: 'https://arch-raon.com',
            mobileWebUrl: 'https://arch-raon.com',
          },
        },
      ],
    });
  };

  return (
    <>
      <InterfaceDiv>
        <RoomCurrentDiv>
          <RoomHeadText>방 코드</RoomHeadText>
          <RoomCodeText>{roomId}</RoomCodeText>
          {roomStatus.users &&
            roomStatus.users.map((participant, index) => (
              <div key={participant}>
                {nickname === participant ? (
                  <RoomMyText>{participant}</RoomMyText>
                ) : (
                  <RoomParticipantsText>{participant}</RoomParticipantsText>
                )}
              </div>
              // <RoomParticipantsText key={participant}>
              //   {participant}
              // </RoomParticipantsText>
            ))}
        </RoomCurrentDiv>
        <JoinButton
          optionText="초대하기"
          buttoncolor="gold"
          onClick={handleshare}
        />
      </InterfaceDiv>
      <ButtonDiv>
        <StartButton
          content="시작하기"
          onClick={() => {
            console.log('게임 시작 버튼');
            if (roomStatus.manager) {
              Stomp.gameStart();
              // navigate('/game/dictionary-multi-game');
            } else {
              alert('방장만이 게임을 시작할 수 있어요');
            }
          }}
        />
        <RoomExitButton
          onClick={() => {
            Stomp.leaveRoom();
            setManagerState((prev) => ({
              ...prev,
              manager: false,
            }));
            sessionStorage.removeItem('roomId');
            navigate('/main');
          }}
        />
      </ButtonDiv>
    </>
  );
}

export default WaitInterface;
