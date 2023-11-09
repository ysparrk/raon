import React, { useState, useEffect } from 'react';
import styled, { createGlobalStyle, keyframes } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import gildong from '../../../assets/Images/gildong.png';

const rollAndRotateFromLeft = keyframes`
  0% {
    transform: translateX(-100%) rotate(0deg);
  }
  25% {
    transform: translateX(-75%) rotate(90deg);
  }
  50% {
    transform: translateX(-50%) rotate(180deg);
  }
  75% {
    transform: translateX(-25%) rotate(270deg);
  }
  100% {
    transform: translateX(0) rotate(360deg);
  }
`;

const TestDiv = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 200px;
`;

const TestDiv2 = styled.div`
  font-family: 'CookieRun';
  font-size: 200px;
`;

const ImageContainer = styled.div`
  overflow-x: hidden;
  position: relative;
  height: 100vh; // 뷰포트 높이를 기준으로 설정하거나 다른 적절한 높이 값을 사용하세요
  width: 100vw; // 뷰포트 너비를 기준으로 설정합니다
`;

const BoxContainer = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  width: fit-content; // 자동으로 내용에 맞게 너비 조정
`;

const StyledBox = styled.div`
  background-color: #fd6f23;
  padding: 20px 80px;
  border-radius: 80px;
  font-family: 'CookieRun';
  font-size: 48px;
  text-align: center;
  margin-bottom: 20px; // 아래쪽 마진 추가
`;

const StyledStart = styled.div`
  font-family: 'CookieRun';
  color: #ffcb4c;
  font-size: 60px;
  margin-top: 20px;
  cursor: pointer;
`;

const StyledSquareBox = styled.div`
  background-color: #ffcd4a; // 노란색 배경
  color: #ffebb6;
  width: 200px; // 정사각형 크기 설정
  height: 200px;
  display: inline-block; // 가로로 배열
  margin: 20px 80px; // 주변 마진
  line-height: 200px; // 텍스트를 박스 중앙에 위치
  font-weight: 1000;
  text-align: center; // 텍스트 중앙 정렬
  font-family: 'CookieRun';
  font-size: 100px; // 폰트 크기
  border-radius: 30px; // 모서리 둥글게
  animation: ${rollAndRotateFromLeft} 2s ease-out;
`;

const StyledCaption = styled.div`
  text-align: center; // 텍스트 중앙 정렬
  font-size: 24px; // 폰트 크기
  font-family: 'ONE-Mobile-POP';
  margin-top: 20px; // 위쪽 마진
`;

const GildongImage = styled.img<{ shouldMove: boolean }>`
  width: 300px;
  height: 450px;
  transition: transform 1s ease-in-out;

  position: absolute;
  bottom: 0px;
  right: -400px;

  transform: ${({ shouldMove }) =>
    shouldMove ? 'translateX(-380px)' : 'none'};
`;

function LandingPage() {
  const [shouldMove, setShouldMove] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      setShouldMove(true);
    }, 1500);

    return () => {
      clearTimeout(timer);
    };
  }, []);
  const StyledCaptionText = "'즐거운'이라는 순 우리말";
  return (
    <div>
      <ImageContainer>
        <GildongImage src={gildong} alt="gildong" shouldMove={shouldMove} />
        <BoxContainer>
          <StyledBox>아이들의 즐거운 국어공부</StyledBox>
          <div>
            <StyledSquareBox>라</StyledSquareBox>
            <StyledSquareBox>온</StyledSquareBox>
          </div>
          <StyledCaption>{StyledCaptionText}</StyledCaption>
          <StyledStart
            onClick={() => {
              window.location.href = `${process.env.REACT_APP_OAUTH_URL}`;
            }}
          >
            시작하기
          </StyledStart>
        </BoxContainer>
      </ImageContainer>
    </div>
  );
}

export default LandingPage;
