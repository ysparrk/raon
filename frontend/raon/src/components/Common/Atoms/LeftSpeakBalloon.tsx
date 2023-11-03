import React from 'react';
import styled, { keyframes } from 'styled-components';
import leftBalloon from '../../../assets/Images/leftBalloon.png';

interface LeftSpeakBalloonProps {
  content?: string;
}

const speakAnimation = keyframes`
  0% {
    opacity: 0;
  }
  17.5% {
    opacity: 0;
  }
  20% {
    opacity: 1;
    transform: translateY(0);
  }

  70% {
    opacity: 1;
  }
  72.5% {
    opacity: 0;
    transform: translateY(-0.1563rem)
  }
  100% {
    opacity: 0;
  }
`;

const LeftBalloonDiv = styled.div`
  display: flex;
  background-image: url(${leftBalloon});
  background-size: cover;
  font-family: 'ONE-Mobile-POP';
  font-size: 2.5rem;
  width: 18.75rem;
  height: 7.8125rem;
  align-items: center;
  justify-content: center;
  animation: ${speakAnimation} 7s ease-in forwards infinite; /* 등장 애니메이션 설정 */
`;

const TextP = styled.p`
  display: inline;
  margin-left: 5rem;
`;

const LeftSpeakBalloon = ({ content }: LeftSpeakBalloonProps) => {
  return (
    <LeftBalloonDiv>
      <TextP>{content}</TextP>
    </LeftBalloonDiv>
  );
};

LeftSpeakBalloon.defaultProps = {
  content: '',
};

export default LeftSpeakBalloon;
