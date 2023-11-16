import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { keyframes } from 'styled-components';
import rightBalloon from '../../../assets/Images/rightBalloon.png';

interface RightSpeakBalloonProps {
  content?: string;
}
const speakAnimation = keyframes`
  0% {
    opacity: 0;
  }
  27.5% {
    opacity: 0;
  }
  30% {
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
const RightBalloonDiv = styled.div`
  display: flex;
  background-image: url(${rightBalloon});
  background-size: cover;
  font-family: 'ONE-Mobile-POP';
  font-size: 2.5rem;
  width: 14.375rem;
  height: 7.8125rem;
  align-items: center;
  justify-content: center;
  animation: ${speakAnimation} 7.5s ease-in forwards infinite; /* 등장 애니메이션 설정 */
`;

const TextP = styled.p`
  display: inline;
  margin-right: 3.125rem;
  margin-bottom: 1.25rem;
`;

const RightSpeakBalloon = ({ content }: RightSpeakBalloonProps) => {
  return (
    <RightBalloonDiv>
      <TextP>{content}</TextP>
    </RightBalloonDiv>
  );
};

RightSpeakBalloon.defaultProps = {
  content: '',
};

export default RightSpeakBalloon;
