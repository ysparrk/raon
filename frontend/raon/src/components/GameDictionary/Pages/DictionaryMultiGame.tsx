import React, { useState } from 'react';
import styled from 'styled-components';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';
import TitleBox from '../../Common/Atoms/TitleBox';
import { multiDictState } from '../../../recoil/Atoms';
import QuizCrossWord from '../Organisms/MultiQuizCrossWord';
import QuizLetter from '../Organisms/MultiQuizLetter';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const DictionaryWaitingRoom = () => {
  const Quiz = useRecoilValue(multiDictState);

  if (Quiz.type === 'I') {
    return (
      <div>
        <TitleBox>국어사전 놀이</TitleBox>
        <QuizLetter
          word={Quiz.word}
          initial={Quiz.initial}
          meaning={Quiz.meaning}
          nextClick={() => {
            console.log('체크용');
          }}
        />
      </div>
    );
  }
  if (Quiz.type === 'D') {
    return (
      <div>
        <TitleBox>국어사전 놀이</TitleBox>
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
      </div>
    );
  }

  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>게임시작중</ContentDiv>
    </div>
  );
};

export default DictionaryWaitingRoom;
