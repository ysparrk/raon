import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import { roomManageState, multiDictState } from '../../../recoil/Atoms';
import { useWebSocket } from '../../../websocket/WebSocketContext';
import Timer from '../../Common/Atoms/Timer';

const ResultDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 1.25rem;
  width: 80vw;
  height: 60vh;
  gap: 2rem;
`;

const LeftDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 30vw;
  height: 58vh;
  color: white;
  font-family: 'ONE-Mobile-POP';
  font-size: 2.5rem;
  gap: 0.3125rem;
`;
const RightDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 48vw;
  height: 58vh;
  background-color: lightblue;
  color: white;
  font-family: 'ONE-Mobile-POP';
  font-size: 2.5rem;
`;

const LeftQuizAnswerP = styled.p`
  font-family: 'ONE-Mobile-POP';
  font-size: 5.75rem;
  color: #ffcd4a;
  margin: 1.25rem;
`;

const LeftMyAnswerP = styled.p`
  font-family: 'CookieRun';
  font-size: 4rem;
  color: white;
`;

const CorrectAnswer = styled.span`
  color: #ffcd4a;
`;

const LeftOthersDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 3.125rem;
  font-family: 'ONE-Mobile-POP';
  font-size: 2.3rem;
`;

const LeftOthersP = styled.p`
  font-family: 'ONE-Mobile-POP';
  font-size: 2.125rem;
  color: white;
`;

function MultiResult() {
  const [myAnswer, setMyAnswer] = useState('');
  const [otherAnswer, setOtherAnswer] = useState<string[]>([]);
  const Quiz = useRecoilValue(multiDictState);
  const Room = useRecoilValue(roomManageState);
  const myname = localStorage.getItem('nickname') ?? '미사용자';

  useEffect(() => {
    setOtherAnswer([]);
    const checkMyRequest = () => {
      Room.userResult.forEach(
        (user: { nickname: string; lastAnswer: string }) => {
          if (user.nickname === myname) {
            setMyAnswer(user.lastAnswer);
            setOtherAnswer((prev) => [...prev, user.lastAnswer]);
          } else {
            setOtherAnswer((prev) => [...prev, user.lastAnswer]);
          }
        },
      );
    };

    checkMyRequest();
  }, [Room.userResult]);

  return (
    <ResultDiv>
      <LeftDiv>
        <LeftQuizAnswerP>답 : {Quiz.answer}</LeftQuizAnswerP>
        {myAnswer === Quiz.answer ? (
          <LeftMyAnswerP>
            내 제출: <CorrectAnswer>{myAnswer}</CorrectAnswer>
          </LeftMyAnswerP>
        ) : (
          <LeftMyAnswerP>내 제출: {myAnswer}</LeftMyAnswerP>
        )}
        다른 친구들의 답
        <LeftOthersDiv>
          {otherAnswer.map((answer, index) => (
            <LeftOthersP key={answer}>
              {answer === Quiz.answer ? (
                <CorrectAnswer>{answer}</CorrectAnswer>
              ) : (
                answer
              )}
            </LeftOthersP>
          ))}
        </LeftOthersDiv>
      </LeftDiv>
      <RightDiv />
    </ResultDiv>
  );
}

export default MultiResult;
