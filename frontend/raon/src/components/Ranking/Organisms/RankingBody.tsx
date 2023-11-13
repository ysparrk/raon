import React, { useState } from 'react';
import styled from 'styled-components';
import ExitButton from '../../Common/Atoms/ExitButton';

interface GameLinkProps {
  active: boolean;
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const SwitchContainer = styled.div`
  display: flex;
  justify-content: flex-start;
  align-self: flex-start;
  padding-left: 6.25rem;
`;

const GameLink = styled.span<GameLinkProps>`
  font-size: 36px;
  font-family: 'CookieRun';
  cursor: pointer;
  margin: 20px 20px;
  color: ${(props) => (props.active ? 'red' : 'black')};
`;

const RankingBox = styled.div`
  display: flex;
  width: 90%;
  justify-content: center;
  margin-top: 20px;
`;

const Rank = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: start;
  padding: 10px;
  border-right: 1px solid #ccc;

  &:last-child {
    border-right: none;
  }
`;

const RankTitle = styled.div`
  font-size: 44px;
  font-family: 'CookieRun';
  text-align: center;
  width: 100%;
  padding-bottom: 10px;
`;

const RankItem = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 5px 0;
`;

const RankNumber = styled.div`
  flex: 0 0 20%;
  text-align: left;
  font-family: 'CookieRun';
  font-size: 24px;
`;

const Nickname = styled.div`
  flex: 0 0 60%;
  text-align: left;
  font-family: 'CookieRun';
  font-size: 24px;
`;

const Score = styled.div`
  flex: 0 0 20%;
  text-align: right;
  font-family: 'CookieRun';
  font-size: 24px;
`;

const RankingBody = () => {
  const [activeGame, setActiveGame] = useState('맞춤법 놀이');

  const [rankList] = useState([
    { rank: 1, nickname: '레몬', score: 90 },
    { rank: 2, nickname: '상재우', score: 88 },
    { rank: 3, nickname: '인덕션', score: 85 },
    { rank: 9, nickname: '내 위', score: 70 },
    { rank: 10, nickname: '아치아빠재우', score: 66 },
    { rank: 11, nickname: '내 아래', score: 65 },
  ]);

  const renderRankItems = () =>
    rankList.map((item) => (
      <RankItem key={item.rank}>
        <RankNumber>{item.rank}등</RankNumber>
        <Nickname>{item.nickname}</Nickname>
        <Score>{item.score}점</Score>
      </RankItem>
    ));

  return (
    <Container>
      <SwitchContainer>
        <GameLink
          active={activeGame === '맞춤법 놀이'}
          onClick={() => setActiveGame('맞춤법 놀이')}
        >
          맞춤법 놀이
        </GameLink>
        <GameLink
          active={activeGame === '국어사전 놀이'}
          onClick={() => setActiveGame('국어사전 놀이')}
        >
          국어사전 놀이
        </GameLink>
      </SwitchContainer>
      <RankingBox>
        <Rank>
          <RankTitle>전국 개인 순위</RankTitle>
          {renderRankItems()}
        </Rank>
        <Rank>
          <RankTitle>교내 개인 순위</RankTitle>
          {renderRankItems()}
        </Rank>
        <Rank>
          <RankTitle>내 학교 순위</RankTitle>
          {renderRankItems()}
        </Rank>
      </RankingBox>
      <ExitButton to="/main/" />
    </Container>
  );
};

export default RankingBody;
