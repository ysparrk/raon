import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import { dictScoreState, multiDictState } from '../../../recoil/Atoms';
import AnswerInputBox from '../Atoms/AnswerInputBox';
import SingleModeAnswer from './SingleModeAnswer';
import { useWebSocket } from '../../../websocket/WebSocketContext';
import Timer from '../../Common/Atoms/Timer';
import { useBGM } from '../../../sound/SoundContext';

interface QuizCrossWordProps {
  word: string;
  west_word: string;
  north_word: string;
  east_word: string;
  south_word: string;
  nextClick: () => void;
}

const QuizDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 80vw;
  height: 60vh;
  gap: 2rem;
`;
const QuizQuestion = styled.div`
  display: flex;
  font-family: 'NanumBarunGothic';
  font-size: 2.5rem;
  color: white;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin-top: 3.125rem;
`;
const QuizContentDiv = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
  gap: 0.3125rem;
  width: 20rem;
  height: 20rem;

  /* 중앙 셀 스타일 */
  & > div:nth-child(5) {
    grid-column: 2 / 3;
    grid-row: 2 / 3;
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: 'ONE-Mobile-POP';
    font-size: 3.125rem;
    border-radius: 1.5625rem;
    background-color: white;
    color: black;
    border: 0.25rem solid black;
  }

  /* 위, 아래, 왼쪽, 오른쪽 셀 스타일 */
  & > div:nth-child(4),
  & > div:nth-child(6) {
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: 'NanumBarunGothic';
    font-size: 3.5rem;
    color: white;
    border: '0.125rem solid black';
  }
  & > div:nth-child(2),
  & > div:nth-child(8) {
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: 'NanumBarunGothic';
    font-size: 3.5rem;
    color: white;
    border: '0.125rem solid black';
  }
`;
const QuizEnterDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 17.5rem;
  margin-top: 1.25rem;
`;

const QuizEnterBtn = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 7.5rem;
  height: 5.125rem;
  background-color: navy;
  border-radius: 10px;
  color: white;
  font-family: 'ONE-Mobile-POP';
  font-size: 2.5rem;
`;
const TimerDiv = styled.div`
  position: fixed;
  display: flex;
  flex-direction: row;
  top: 15%;
  right: 5%;
  font-size: 3.1375rem;
  font-family: 'ONE-Mobile-POP';
  color: black;
  justify-content: space-between;
  align-items: center;
  width: 7.5rem;
`;

const WaitDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  font-family: 'CookieRun';
  font-weight: 900;
  font-size: 5rem;
  color: black;
  width: 65.5rem;
  height: 8rem;
`;

function MultiQuizCrossWord({
  word,
  west_word,
  north_word,
  east_word,
  south_word,
  nextClick,
}: QuizCrossWordProps) {
  const [inputValue, setInputValue] = useState('');
  const [isSolved, setIsSolved] = useState(false);
  const [isCorrect, setIsCorrect] = useState(false);
  const [startTime, setStartTime] = useState(Date.now());
  const [timerState, setTimerState] = useState(20);
  const QuizStage = useRecoilValue(multiDictState);
  const Stomp = useWebSocket();
  const { startBGM, isMuted } = useBGM();

  useEffect(() => {
    setTimerState(20);
    setIsSolved(false);
    setStartTime(Date.now());
    setInputValue('');
    if (!isMuted) {
      startBGM('nextStage');
    }
  }, [QuizStage.stage]);
  useEffect(() => {
    // 설정된 시간 간격마다 setInterval 콜백이 실행된다.
    const id = setInterval(() => {
      // 타이머 숫자가 하나씩 줄어들도록
      setTimerState((count) => count - 1);
    }, 1000);

    // 0이 되면 카운트가 멈춤
    if (timerState === 0) {
      clearInterval(id);
      if (!isSolved) {
        handleClick(inputValue);
      }
    }
    return () => clearInterval(id);
    // 카운트 변수가 바뀔때마다 useEffecct 실행
  }, [timerState]);
  const setDictScore = useSetRecoilState(dictScoreState);
  const handleClick = (value: string) => {
    const timeSpend = Date.now() - startTime;

    if (!isMuted) {
      startBGM('sendData');
    }
    Stomp.sendQuizResult(value, timeSpend, QuizStage.stage);
    // if (value === word) {
    //   // setIsCorrect(true);
    //   // setIsSolved(true);
    //   // setInputValue('');
    //   // setDictScore((prevValue) => prevValue + 10);
    // } else {
    //   // setIsCorrect(false);
    //   // setIsSolved(true);
    //   // setInputValue('');
    // }
  };

  return (
    <QuizDiv>
      <QuizQuestion>가운데에 한 글자를 넣어 각 단어를 완성하시오</QuizQuestion>
      <QuizContentDiv>
        <div />
        <div>{north_word}</div>
        <div />
        <div>{west_word}</div>
        <div>?</div>
        <div>{east_word}</div>
        <div />
        <div>{south_word}</div>
        <div />
      </QuizContentDiv>
      <TimerDiv>
        <Timer />
        {timerState}
      </TimerDiv>
      {isSolved ? (
        <WaitDiv>제출 완료! 결과를 기다려주세요</WaitDiv>
      ) : (
        <QuizEnterDiv>
          <AnswerInputBox
            inputText={inputValue}
            onChange={(event) => {
              setInputValue(event.target.value);
            }}
            onEnter={() => {
              if (!isSolved) {
                handleClick(inputValue);
                setIsSolved(true);
              }
            }}
          />
          <QuizEnterBtn
            onClick={() => {
              handleClick(inputValue);
              setIsSolved(true);
            }}
          >
            제출
          </QuizEnterBtn>
        </QuizEnterDiv>
      )}
    </QuizDiv>
  );
}

export default MultiQuizCrossWord;
