import React, { useState } from 'react';
import styled from 'styled-components';
import AnswerInputBox from '../Atoms/AnswerInputBox';
import SingleModeAnswer from './SingleModeAnswer';

interface QuizLetterProps {
  word: string;
  initial: string;
  meaning: string;
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
  font-size: 5rem;
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

function QuizLetter({ word, initial, meaning }: QuizLetterProps) {
  const [inputValue, setInputValue] = useState('');
  const [isSolved, setIsSolved] = useState(false);
  const [isCorrect, setIsCorrect] = useState(false);
  const handleClick = (value: string) => {
    if (value === word) {
      setIsCorrect(true);
      setIsSolved(true);
    } else {
      setIsCorrect(false);
      setIsSolved(true);
    }
  };
  return (
    <QuizDiv>
      {isSolved && (
        <SingleModeAnswer
          onClose={() => {
            setIsSolved(false);
          }}
          answer={word}
          isCorrect={isCorrect}
        />
      )}
      <QuizQuestion>{meaning}</QuizQuestion>
      <QuizInitial>{initial}</QuizInitial>
      <QuizEnterDiv>
        <AnswerInputBox
          inputText={inputValue}
          onChange={(event) => {
            setInputValue(event.target.value);
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

export default QuizLetter;
