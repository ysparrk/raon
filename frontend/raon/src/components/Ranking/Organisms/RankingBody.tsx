import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import ExitButton from '../../Common/Atoms/ExitButton';
import {
  getPersonalSpelling,
  getClassSpelling,
  getSchoolSpelling,
  getPersonalDictionary,
  getClassDictionary,
  getSchoolDictionary,
} from '../../../api/RankingApi.tsx';

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

  const [personalList, setPersonalList] = useState([]);
  const [classList, setClassList] = useState([]);
  const [schoolList, setSchoolList] = useState([]);

  useEffect(() => {
    const fetchSpellingData = async () => {
      try {
        const responsePersonal = await getPersonalSpelling();
        if (
          responsePersonal &&
          responsePersonal.data &&
          Array.isArray(responsePersonal.data.data.rankList)
        ) {
          setPersonalList(responsePersonal.data.data.rankList);
        }

        const responseClass = await getClassSpelling();
        if (
          responseClass &&
          responseClass.data &&
          Array.isArray(responseClass.data.data.rankList)
        ) {
          setClassList(responseClass.data.data.rankList);
        }

        const responseSchool = await getSchoolSpelling();
        if (
          responseSchool &&
          responseSchool.data &&
          Array.isArray(responseSchool.data.data.rankList)
        ) {
          setSchoolList(responseSchool.data.data.rankList);
        }
      } catch (error) {
        console.error('맞춤법 놀이 API 요청 중 오류 발생:', error);
      }
    };

    const fetchDictionaryData = async () => {
      try {
        const responsePersonal = await getPersonalDictionary();
        if (
          responsePersonal &&
          responsePersonal.data &&
          Array.isArray(responsePersonal.data.data.rankList)
        ) {
          setPersonalList(responsePersonal.data.data.rankList);
        }

        const responseClass = await getClassDictionary();
        if (
          responseClass &&
          responseClass.data &&
          Array.isArray(responseClass.data.data.rankList)
        ) {
          setClassList(responseClass.data.data.rankList);
        }

        const responseSchool = await getSchoolDictionary();
        if (
          responseSchool &&
          responseSchool.data &&
          Array.isArray(responseSchool.data.data.rankList)
        ) {
          setSchoolList(responseSchool.data.data.rankList);
        }
      } catch (error) {
        console.error('국어사전 놀이 API 요청 중 오류 발생:', error);
      }
    };

    if (activeGame === '맞춤법 놀이') {
      fetchSpellingData();
    } else if (activeGame === '국어사전 놀이') {
      fetchDictionaryData();
    }
  }, [activeGame]);

  const renderRankItems = (rankData) => {
    return rankData.map((item, index) => (
      <RankItem key={index}>
        <RankNumber>{index + 1}등</RankNumber>
        <Nickname>{item.ranker}</Nickname>
        <Score>{item.score}점</Score>
      </RankItem>
    ));
  };

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
          {renderRankItems(personalList)}
        </Rank>
        <Rank>
          <RankTitle>교내 개인 순위</RankTitle>
          {renderRankItems(classList)}
        </Rank>
        <Rank>
          <RankTitle>내 학교 순위</RankTitle>
          {renderRankItems(schoolList)}
        </Rank>
      </RankingBox>
      <ExitButton to="/main/" />
    </Container>
  );
};

export default RankingBody;
