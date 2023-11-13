import React from 'react';
import styled from 'styled-components';
import timer from '../../../assets/Images/timer.png';

const TimerDiv = styled.div`
  display: flex;
  background-image: url(${timer});
  background-size: cover;
  font-family: 'ONE-Mobile-POP';
  width: 3.125rem;
  height: 3.25rem;
  align-items: center;
  justify-content: center;
`;

const Timer = () => {
  return <TimerDiv />;
};

export default Timer;
