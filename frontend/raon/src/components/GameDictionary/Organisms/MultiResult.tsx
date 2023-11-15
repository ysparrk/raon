import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import { roomManageState, multiDictState } from '../../../recoil/Atoms';
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
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 48vw;
  height: 58vh;
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
  margin-bottom: 1rem;
`;

const CorrectAnswer = styled.span`
  color: #ffcd4a;
`;

const LeftOthersDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  /* margin-top: 2.125rem; */
  font-family: 'ONE-Mobile-POP';
  font-size: 2.3rem;
`;

const LeftOthersP = styled.p`
  font-family: 'ONE-Mobile-POP';
  font-size: 2.125rem;
  color: white;
  margin: 0.5rem;
`;

const RightTopicP = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 2.125rem;
  color: black;
`;
const RightRankDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  font-family: 'CookieRun';
  font-size: 2.125rem;
  color: black;
  gap: 3.625rem;
  margin: 1rem;
`;

function MultiResult() {
  const [myAnswer, setMyAnswer] = useState('');
  const [otherAnswer, setOtherAnswer] = useState<string[]>([]);
  const [timerState, setTimerState] = useState(20);

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
  useEffect(() => {
    if (Room.breakTime) {
      setTimerState(5);
    }
  }, [Room.breakTime]);
  useEffect(() => {
    const id = setInterval(() => {
      setTimerState((count) => count - 1);
    }, 1000);

    if (timerState === 0) {
      clearInterval(id);
    }
    return () => clearInterval(id);
  }, [timerState]);
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
      <RightDiv>
        <RightTopicP>현재 점수</RightTopicP>
        {Room.userResult.map(
          (
            user: {
              nickname?: string;
              current_point?: number;
              lastAnswer?: string;
            },
            index,
          ) => {
            if (user.lastAnswer === Quiz.answer) {
              return (
                <RightRankDiv key={user.nickname}>
                  <CorrectAnswer>{index + 1}</CorrectAnswer>
                  <CorrectAnswer>{user.nickname ?? '?'}</CorrectAnswer>
                  <CorrectAnswer>{user.current_point ?? '0'}</CorrectAnswer>
                </RightRankDiv>
              );
            }
            return (
              <RightRankDiv key={user.nickname}>
                <p>{index + 1}</p>
                <p>{user.nickname ?? '?'}</p>
                <p>{user.current_point ?? '0'}</p>
              </RightRankDiv>
            );
          },
        )}
      </RightDiv>

      <TimerDiv>
        <Timer />
        {timerState}
      </TimerDiv>
    </ResultDiv>
  );
}

export default MultiResult;
