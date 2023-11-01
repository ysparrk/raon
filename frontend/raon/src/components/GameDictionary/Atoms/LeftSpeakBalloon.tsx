import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import leftBalloon from '../../../assets/Images/leftBalloon.png';

interface LeftSpeakBalloonProps {
  content?: string;
}

const LeftBalloonDiv = styled.div`
  display: flex;
  background-image: url(${leftBalloon});
  background-size: cover;
  font-family: 'ONE-Mobile-POP';
  font-size: 40px;
  width: 300px;
  height: 125px;
  align-items: center;
  justify-content: center;
`;

const TextP = styled.p`
  display: inline;
  margin-left: 80px;
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
