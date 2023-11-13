import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import { dictScoreState, multiDictState } from '../../../recoil/Atoms';
import AnswerInputBox from '../Atoms/AnswerInputBox';
import SingleModeAnswer from './SingleModeAnswer';
import { useWebSocket } from '../../../websocket/WebSocketContext';
import Timer from '../../Common/Atoms/Timer';

interface QuizLetterProps {
  word: string;
  initial: string;
  meaning: string;
  nextClick: () => void;
}

const QuizDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 80vw;
  height: 60vh;
  gap: 20px;
`;
const QuizQuestion = styled.div`
  display: flex;
  font-family: 'NanumBarunGothic';
  font-size: 4rem;
  color: white;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin-top: 3.125rem;
`;
const QuizInitial = styled.div`
  display: flex;
  font-family: 'CookieRun';
  font-size: 7rem;
  color: white;
`;

const QuizEnterDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 17.5rem;
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

function MultiQuizLetter({
  word,
  initial,
  meaning,
  nextClick,
}: QuizLetterProps) {
  const [inputValue, setInputValue] = useState('');
  const [isSolved, setIsSolved] = useState(false);
  const [isCorrect, setIsCorrect] = useState(false);
  const [startTime, setStartTime] = useState(Date.now());
  const [timerState, setTimerState] = useState(20);
  const QuizStage = useRecoilValue(multiDictState);
  const Stomp = useWebSocket();
  useEffect(() => {
    setTimerState(20);
    setIsSolved(false);
    setStartTime(Date.now());
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
      handleClick(inputValue);
    }
    return () => clearInterval(id);
    // 카운트 변수가 바뀔때마다 useEffecct 실행
  }, [timerState]);
  // const setDictScore = useSetRecoilState(dictScoreState);
  const handleClick = (value: string) => {
    const timeSpend = Date.now() - startTime;

    console.log(value, timeSpend, QuizStage.stage);
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
      {/* {isSolved && (
        <SingleModeAnswer
          onClose={() => {
            setIsSolved(false);
            nextClick();
          }}
          answer={word}
          isCorrect={isCorrect}
        />
      )} */}
      <QuizQuestion>{meaning}</QuizQuestion>
      <QuizInitial>{initial}</QuizInitial>
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

export default MultiQuizLetter;
