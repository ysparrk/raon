import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';
import TitleBox from '../../Common/Atoms/TitleBox';
import { multiDictState, roomManageState } from '../../../recoil/Atoms';
import QuizCrossWord from '../Organisms/MultiQuizCrossWord';
import QuizLetter from '../Organisms/MultiQuizLetter';
import MultiResult from '../Organisms/MultiResult';
import { useBGM } from '../../../sound/SoundContext';
const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ReadyDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: 'CookieRun';
  font-weight: 900;
  color: white;
  height: 70vh;
  font-size: 5.125rem;
`;
const CountDiv = styled.div`
  position: fixed;
  display: flex;
  top: 5%;
  right: 5%;
  font-size: 3.125rem;
  font-family: 'ONE-Mobile-POP';
  color: white;
`;

const DictionaryWaitingRoom = () => {
  const Quiz = useRecoilValue(multiDictState);
  const Room = useRecoilValue(roomManageState);
  const navigate = useNavigate();
  const { startBGM, isMuted } = useBGM();
  useEffect(() => {
    if (!isMuted) {
      startBGM('multiGame');
    }
  }, []);

  useEffect(() => {
    if (Room.isFinish) {
      navigate('/game/dictionary-multi-result');
    }
  }, [Room.isFinish]);
  if (Room.breakTime) {
    return (
      <div>
        <TitleBox>국어사전 놀이</TitleBox>
        <CountDiv>{Quiz.stage} / 10</CountDiv>
        <ContentDiv>
          <MultiResult />
        </ContentDiv>
      </div>
    );
  }

  if (Quiz.type === 'I') {
    return (
      <div>
        <TitleBox>국어사전 놀이</TitleBox>
        <CountDiv>{Quiz.stage} / 10</CountDiv>
        <ContentDiv>
          <QuizLetter
            word={Quiz.word}
            initial={Quiz.initial}
            meaning={Quiz.meaning}
            nextClick={() => {
              console.log('체크용');
            }}
          />
        </ContentDiv>
      </div>
    );
  }
  if (Quiz.type === 'D') {
    return (
      <div>
        <TitleBox>국어사전 놀이</TitleBox>
        <CountDiv>{Quiz.stage} / 10</CountDiv>
        <ContentDiv>
          <QuizCrossWord
            word={Quiz.answer}
            north_word={Quiz.northWord}
            east_word={Quiz.eastWord}
            west_word={Quiz.westWord}
            south_word={Quiz.southWord}
            nextClick={() => {
              console.log('체크용');
            }}
          />
        </ContentDiv>
      </div>
    );
  }

  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>
        <ReadyDiv>곧 게임이 시작됩니다!</ReadyDiv>
      </ContentDiv>
    </div>
  );
};

export default DictionaryWaitingRoom;
