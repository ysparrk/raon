import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { keyframes } from 'styled-components';
import StartButton from '../../Common/Atoms/StartButton';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';

const appearAnimation = keyframes`
  0% {
    opacity: 0;
    transform: translateY(0.3125rem);
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
  font-size: 2.5rem;
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

  /* 중앙 셀 스타일 */
  & > div:nth-child(5) {
    grid-column: 2 / 3;
    grid-row: 2 / 3;
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: 'ONE-Mobile-POP';
    font-size: 2.8125rem;
    border-radius: 1.5625rem;
    background-color: black;
    color: white;
    /* border: 4px solid black; */
  }

  /* 위, 아래, 왼쪽, 오른쪽 셀 스타일 */
  & > div:nth-child(2),
  & > div:nth-child(4),
  & > div:nth-child(6),
  & > div:nth-child(8) {
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: 'NanumBarunGothic';
    font-size: 3.5rem;
    border: '0.125rem solid black';
  }
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
        <GameHeaderDiv>국어사전 놀이</GameHeaderDiv>
        <GameExampleQuestionDiv>
          <p>가운데에 한 글자를 넣어</p>
          <p>각 단어를 완성하시오</p>
        </GameExampleQuestionDiv>
        <GameExampleContentDiv>
          <div />
          <div>지</div>
          <div />
          <div>신</div>
          <div>?</div>
          <div>제</div>
          <div />
          <div>집</div>
          <div />
        </GameExampleContentDiv>
      </InterfaceDiv>
      <ButtonDiv>
        <StartButton
          fontColor="dodgerblue"
          content="함께하기"
          onClick={() => {
            navigate('/game/dictionary-multi-init');
          }}
        />
        <StartButton
          fontColor="slategrey"
          content="혼자하기"
          onClick={() => {
            navigate('/game/dictionary-single-game');
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
