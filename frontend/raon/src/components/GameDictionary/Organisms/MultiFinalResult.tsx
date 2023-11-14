import React, { useEffect } from 'react';
import styled from 'styled-components';
import { useRecoilValue } from 'recoil';
import { roomManageState } from '../../../recoil/Atoms';
import Firework from '../../Common/Organisms/Firework.tsx';

const ResultTotalDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 50vw;
  height: 60vh;
  color: white;
  font-family: 'ONE-Mobile-POP';
  font-size: 5rem;
`;
const ResultTopicP = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 4rem;
  color: white;
`;
const ResultPersonalDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  font-family: 'CookieRun';
  font-weight: 800;
  font-size: 3rem;
  color: white;
  gap: 4.825rem;
  margin: 1rem;
`;

function MultiModeResult() {
  const Room = useRecoilValue(roomManageState);
  useEffect(() => {
    // 악
  }, []);

  return (
    <ResultTotalDiv>
      <Firework />
      <ResultTopicP>최종 결과</ResultTopicP>
      {Room.userResult.map(
        (user: { nickname?: string; current_point?: number }, index) => (
          <ResultPersonalDiv key={user.nickname}>
            <p>{index + 1}</p>
            <p>{user.nickname ?? '?'}</p>
            <p>{user.current_point ?? '0'}</p>
          </ResultPersonalDiv>
        ),
      )}
    </ResultTotalDiv>
  );
}

export default MultiModeResult;
