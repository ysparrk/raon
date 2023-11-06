import React from 'react';
import styled, { keyframes } from 'styled-components';
import helpCharacter from '../../../assets/Images/helpCharacter.png';
import cloudBalloon from '../../../assets/Images/cloudBalloon.png';

interface HelpCharacterProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
}

const CharaterfloatAnimation = keyframes`
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-0.625rem);
  }
`;
const HelpCharacterDiv = styled.div`
  position: absolute;
  width: 18.75rem;
  height: 17.9375rem;
  background-image: url(${helpCharacter});
  background-size: cover;
  cursor: pointer;
  display: flex;
  bottom: -2%;
  left: 0%;
  animation: ${CharaterfloatAnimation} 2s infinite alternate;
`;

const BalloonfloatAnimation = keyframes`
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(0.125rem);
  }
`;
const CloudBalloonDiv = styled.div`
  display: flex;
  position: absolute;
  left: 3%;
  bottom: 33%;
  align-items: center;
  justify-content: center;
  font-family: 'ONE-Mobile-POP';
  font-size: 3.125rem;
  width: 16rem;
  height: 16rem;
  background-image: url(${cloudBalloon});
  background-size: cover;
  animation: ${BalloonfloatAnimation} 0.8s infinite alternate;
`;

const TextP = styled.p`
  display: inline;
  margin-bottom: 2.5rem;
`;

function HelpCharacter({ onClick }: HelpCharacterProps) {
  return (
    <div>
      <CloudBalloonDiv>
        <TextP>도움말</TextP>
      </CloudBalloonDiv>
      <HelpCharacterDiv onClick={onClick} />
    </div>
  );
}

HelpCharacter.defaultProps = {
  onClick: () => console.log('test'),
};

export default HelpCharacter;
