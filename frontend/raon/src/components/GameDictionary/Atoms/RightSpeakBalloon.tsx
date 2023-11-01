import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import rightBalloon from '../../../assets/Images/rightBalloon.png';

interface RightSpeakBalloonProps {
  content?: string;
}

const RightBalloonDiv = styled.div`
  display: flex;
  background-image: url(${rightBalloon});
  background-size: cover;
  font-family: 'ONE-Mobile-POP';
  font-size: 40px;
  width: 230px;
  height: 125px;
  align-items: center;
  justify-content: center;
`;

const TextP = styled.p`
  display: inline;
  margin-right: 50px;
  margin-bottom: 20px;
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
