import React from 'react';
import styled from 'styled-components';
import { useRecoilValue } from 'recoil';
import { submitState, answerState } from '../../../recoil/Atoms.tsx';

const TestDiv = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 200px;
`;

const TestDiv2 = styled.div`
  font-family: 'CookieRun';
  font-size: 200px;
`;

const SpellingAnswer = () => {
  const userAnswers = useRecoilValue(submitState);
  const correctAnswers = useRecoilValue(answerState);
  console.log(userAnswers, correctAnswers);

  const score = userAnswers.reduce((acc, answer, index) => {
    return answer === correctAnswers[index] ? acc + 1 : acc;
  }, 0);

  return (
    <div>
      <h1>당신의 점수: {score} / 10</h1>
    </div>
  );
};

export default SpellingAnswer;
