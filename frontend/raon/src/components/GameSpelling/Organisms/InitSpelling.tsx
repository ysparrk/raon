import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { keyframes } from 'styled-components';
import StartButton from '../../Common/Atoms/StartButton';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';

const appearAnimation = keyframes`
  0% {
    opacity: 0;
    transform: translateY(5px);
  }
  70% {
    opacity: 0;
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
`;

const InterfaceDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  width: 72.5vw;
  height: 80vh;
  margin: 0.9375rem;
  padding: 1.25rem;
  gap: 1.5rem;
  border: 0.5rem solid black;
  background-color: ivory;
  border-radius: 1.875rem;
  opacity: 0;
  animation: ${appearAnimation} 0.5s ease-in forwards;
`;
const GameHeaderDiv = styled.div`
  display: flex;
  font-family: 'ONE-Mobile-POP';
  font-size: 9.25rem;
  color: black;
  opacity: 0;
  animation: ${appearAnimation} 1s ease-in forwards;
`;
const GameExampleQuestionDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-family: 'NanumBarunGothic';
  font-size: 40px;
  opacity: 0;
  animation: ${appearAnimation} 1.25s ease-in forwards;
`;
const GameExampleContentDiv = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
  gap: 0.3125rem;
  width: 11.5rem;
  height: 11.5rem;
  opacity: 0;
  animation: ${appearAnimation} 1.5s ease-in forwards;
`;

const ButtonDiv = styled.div`
  position: absolute;
  display: flex;
  gap: 3.125rem;
  height: 7.5rem;
  justify-content: center;
  align-items: center;
  bottom: 5.5%;
  right: 2%;
  opacity: 0;
  animation: ${appearAnimation} 2.5s ease-in forwards;
`;
function InitInterface() {
  const navigate = useNavigate();
  return (
    <>
      <InterfaceDiv>
        <GameHeaderDiv>맞춤법 퀴즈</GameHeaderDiv>
        <GameExampleQuestionDiv>
          <p>다음 중 맞는 것을 고르세요</p>
        </GameExampleQuestionDiv>
        <GameExampleContentDiv>d</GameExampleContentDiv>
      </InterfaceDiv>
      <ButtonDiv>
        <StartButton
          fontColor="sienna"
          content="시작하기"
          onClick={() => {
            navigate('/game/dictionary-quiz');
          }}
        />
        <ExitButton
          onClick={() => {
            navigate('/main');
          }}
        />
      </ButtonDiv>
    </>
  );
}

export default InitInterface;
