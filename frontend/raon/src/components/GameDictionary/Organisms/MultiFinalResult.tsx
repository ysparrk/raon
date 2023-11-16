import React, { useEffect } from 'react';
import styled from 'styled-components';
import { useRecoilValue } from 'recoil';
import { roomManageState } from '../../../recoil/Atoms';
import Podium from '../Atoms/Podium.tsx';
import Firework from '../../Common/Organisms/Firework.tsx';

const ResultTotalDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 65vw;
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
  padding-left: 6.75rem;
  width: 90%;
  align-items: center;
  font-family: 'CookieRun';
  font-weight: 800;
  font-size: 2rem;
  color: white;
  gap: 4.825rem;
  margin: 1rem;
`;
const ResultInfoDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
`;
const LeftDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-end;
  width: 40%;
  margin: 0;
  height: 100%;
`;
const RightDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60%;
  margin: 0;
  height: 100%;
`;

function MultiModeResult() {
  const Room = useRecoilValue(roomManageState);
  useEffect(() => {
    // 악
  }, []);
  const [firstUser, secondUser, thirdUser] = Room.userResult;
  return (
    <ResultTotalDiv>
      <Firework />
      <ResultTopicP>최종 결과</ResultTopicP>
      <ResultInfoDiv>
        <LeftDiv>
          <Podium
            firstUser={firstUser}
            secondUser={secondUser}
            thirdUser={thirdUser}
          />
        </LeftDiv>
        <RightDiv>
          {Room.userResult.map(
            (user: { nickname?: string; current_point?: number }, index) => (
              <ResultPersonalDiv key={user.nickname}>
                <p>{index + 1}등</p>
                <p>{user.nickname ?? '?'}</p>
                <p>{user.current_point ?? '0'}점</p>
              </ResultPersonalDiv>
            ),
          )}
        </RightDiv>
      </ResultInfoDiv>
    </ResultTotalDiv>
  );
}

export default MultiModeResult;
