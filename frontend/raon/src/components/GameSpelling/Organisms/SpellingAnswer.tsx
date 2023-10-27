import React from 'react';
import styled from 'styled-components';
import { useRecoilValue } from 'recoil';
import { submitState, answerState } from '../../../recoil/Atoms.tsx';
import ExitButton from '../../Common/Atoms/ExitButton.tsx';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const ScoreHeader = styled.h1`
  font-family: 'CookieRun';
  text-align: center;
  font-size: 70px;
  width: 100%;
  margin-top: 50px;
`;

const Content = styled.div`
  font-family: 'CookieRun';
  font-size: 50px;
  color: #000000;
`;

const Columns = styled.div`
  display: flex;
  justify-content: center;
  gap: 50px;
`;

const Column = styled.div`
  display: flex;
  flex-direction: column;
`;

const IncorrectAnswer = styled.span`
  text-decoration: line-through;
  margin-right: 40px;
`;

const CorrectAnswer = styled.span`
  color: red;
`;

const SpellingAnswer = () => {
  const userAnswers = useRecoilValue(submitState);
  const correctAnswers = useRecoilValue(answerState);

  const leftAnswers = userAnswers.slice(0, 5);
  const rightAnswers = userAnswers.slice(5, 10);

  const score = userAnswers.reduce((acc, answer, index) => {
    return answer === correctAnswers[index] ? acc + 1 : acc;
  }, 0);

  return (
    <Container>
      <ScoreHeader>당신의 점수: {score * 10} 점</ScoreHeader>
      <Content>
        <Columns>
          <Column>
            {leftAnswers.map((answer, index) => (
              /* eslint-disable-next-line react/no-array-index-key */
              <div key={index}>
                {index + 1}.{' '}
                {answer === correctAnswers[index] ? (
                  answer
                ) : (
                  <>
                    <IncorrectAnswer>{answer}</IncorrectAnswer>
                    <CorrectAnswer>{correctAnswers[index]}</CorrectAnswer>
                  </>
                )}
              </div>
            ))}
          </Column>
          <Column>
            {rightAnswers.map((answer, index) => (
              /* eslint-disable-next-line react/no-array-index-key */
              <div key={index + 5}>
                {index + 6}.{' '}
                {answer === correctAnswers[index + 5] ? (
                  answer
                ) : (
                  <>
                    <IncorrectAnswer>{answer}</IncorrectAnswer>
                    <CorrectAnswer>{correctAnswers[index + 5]}</CorrectAnswer>
                  </>
                )}
              </div>
            ))}
          </Column>
        </Columns>
      </Content>
      <ExitButton to="/main/" />
    </Container>
  );
};

export default SpellingAnswer;
