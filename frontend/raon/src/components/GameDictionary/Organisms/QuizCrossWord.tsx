import React, { useState } from 'react';
import styled from 'styled-components';
import { useSetRecoilState } from 'recoil';
import { dictScoreState } from '../../../recoil/Atoms';
import AnswerInputBox from '../Atoms/AnswerInputBox';
import SingleModeAnswer from './SingleModeAnswer';

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

function QuizCrossWord({
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

  const setDictScore = useSetRecoilState(dictScoreState);
  const handleClick = (value: string) => {
    if (value === word) {
      setIsCorrect(true);
      setIsSolved(true);
      setInputValue('');
      setDictScore((prevValue) => prevValue + 10);
    } else {
      setIsCorrect(false);
      setIsSolved(true);
      setInputValue('');
    }
  };
  return (
    <QuizDiv>
      {isSolved && (
        <SingleModeAnswer
          onClose={() => {
            setIsSolved(false);
            nextClick();
          }}
          answer={word}
          isCorrect={isCorrect}
        />
      )}
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
      <QuizEnterDiv>
        <AnswerInputBox
          inputText={inputValue}
          onChange={(event) => {
            setInputValue(event.target.value);
          }}
          onEnter={() => {
            if (!isSolved) {
              handleClick(inputValue);
            }
          }}
        />
        <QuizEnterBtn
          onClick={() => {
            handleClick(inputValue);
          }}
        >
          제출
        </QuizEnterBtn>
      </QuizEnterDiv>
    </QuizDiv>
  );
}

export default QuizCrossWord;
