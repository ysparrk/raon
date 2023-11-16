import React, { useEffect } from 'react';
import styled from 'styled-components';
import { useRecoilValue } from 'recoil';
import { dictScoreState } from '../../../recoil/Atoms';
import { postSingleResult } from '../../../api/GameDictionaryApi';

const ResultDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 50rem;
  height: 28.125rem;
`;

const ResultText = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 9.375rem;
  font-family: 'ONE-Mobile-POP';
  color: white;
  height: 65%;
`;

const ScoreText = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 5rem;
  font-family: 'CookieRun';
  font-weight: 900;
  color: white;
  height: 35%;
`;

function SingleModeResult() {
  const userScore = useRecoilValue(dictScoreState);
  // useEffect(() => {
  //   const postScore = async (score: number) => {
  //     try {
  //       const response = await postSingleResult(score);
  //       console.log(response);
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   };

  //   // postScore 함수 호출
  //   postScore(userScore);
  // }, [userScore]); // 두 번째 매개변수로 userScore를 의존성으로 설정

  let content = '';
  if (userScore >= 80) {
    content = '훌륭해요!';
  } else if (userScore >= 40) {
    content = '잘했어요!';
  } else if (userScore >= 10) {
    content = '좋아요!';
  } else {
    content = '?';
  }
  return (
    <ResultDiv>
      <ResultText>{userScore} 점</ResultText>
      <ScoreText>{content}</ScoreText>
    </ResultDiv>
  );
}

export default SingleModeResult;
