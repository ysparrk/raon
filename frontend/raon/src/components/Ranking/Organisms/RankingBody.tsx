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
  padding: 0.625rem;
  border-right: 1px solid #ccc;
  height: 25rem;
  z-index: 1;

  &:last-child {
    border-right: none;
  }
`;

const RankScrollDiv = styled.div`
  height: 23rem;
  overflow-y: auto;
  overflow-x: hidden;
  width: 100%;
  margin: 0;
  padding: 0.5875rem;
  &::-webkit-scrollbar {
    width: 0.995rem;
  }
  &::-webkit-scrollbar-track {
    background-color: ivory;
    border-radius: 0.625rem;
    box-shadow: inset 0rem 0rem 0.3125rem white;
  }
  &::-webkit-scrollbar-thumb {
    background-color: gray;
    border-radius: 0.625rem;
  }
  &::-webkit-scrollbar-thumb:hover {
    background-color: #2f3542;
  }
`;

const RankTitle = styled.div`
  font-size: 2.5rem;
  font-family: 'CookieRun';
  text-align: center;
  width: 100%;
  padding-bottom: 0.625rem;
`;

const RankItem = styled.div<{ rank: number }>`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 0.3125rem 0;
  transition: background-color 0.3s;
  &.first,
  &.second,
  &.third {
    color: white;
  }
`;

const RankNumber = styled.div<{ rank: number }>`
  flex: 0 0 13%;
  text-align: left;
  font-family: 'CookieRun';
  font-size: 1.5rem;
  &.first {
    color: gold; /* 1등 색상 */
    text-shadow:
      -0.0938rem -0.0938rem 0 #000,
      0.0938rem -0.0938rem 0 #000,
      -0.0938rem 0.0938rem 0 #000,
      0.0938rem 0.0938rem 0 #000;
  }

  &.second {
    color: silver; /* 2등 색상 */
    text-shadow:
      -0.0938rem -0.0938rem 0 #000,
      0.0938rem -0.0938rem 0 #000,
      -0.0938rem 0.0938rem 0 #000,
      0.0938rem 0.0938rem 0 #000;
  }

  &.third {
    color: #cd7f32; /* 3등 색상 (브론즈) */
    text-shadow:
      -0.0938rem -0.0938rem 0 #000,
      0.0938rem -0.0938rem 0 #000,
      -0.0938rem 0.0938rem 0 #000,
      0.0938rem 0.0938rem 0 #000;
  }
`;

const Nickname = styled.div`
  flex: 0 0 57%;
  text-align: left;
  font-family: 'CookieRun';
  font-size: 1.375rem;
`;

const Score = styled.div`
  flex: 0 0 30%;
  text-align: right;
  font-family: 'CookieRun';
  font-size: 1.25rem;
`;

const ExitFixDiv = styled.div`
  position: fixed;
  bottom: 3%;
  right: 3%;
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
  const getRankClass = (index: number) => {
    if (index === 0) {
      return 'first';
    }
    if (index === 1) {
      return 'second';
    }
    if (index === 2) {
      return 'third';
    }
    return '';
  };
  const renderRankItems = (rankData: any[]) => {
    return rankData.map((item, index) => (
      <RankItem
        rank={index + 1}
        className={getRankClass(index)}
        key={item.ranker}
      >
        <RankNumber rank={index + 1} className={getRankClass(index)}>
          {index + 1}등
        </RankNumber>
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
          <RankScrollDiv>{renderRankItems(personalList)}</RankScrollDiv>
        </Rank>
        <Rank>
          <RankTitle>교내 개인 순위</RankTitle>
          <RankScrollDiv>{renderRankItems(classList)}</RankScrollDiv>
        </Rank>
        <Rank>
          <RankTitle>내 학교 순위</RankTitle>
          <RankScrollDiv>{renderRankItems(schoolList)}</RankScrollDiv>
        </Rank>
      </RankingBox>
      <ExitFixDiv>
        <ExitButton to="/main/" />
      </ExitFixDiv>
    </Container>
  );
};

export default RankingBody;
