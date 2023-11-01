import React from 'react';
import styled from 'styled-components';
import LeftSpeakBalloon from '../Atoms/LeftSpeakBalloon';
import RightSpeakBalloon from '../Atoms/RightSpeakBalloon';

const DictionaryInit = () => {
  return (
    <div>
      <p>여긴어디</p>
      <LeftSpeakBalloon content="삐약" />
      <RightSpeakBalloon content="삐약" />
    </div>
  );
};
export default DictionaryInit;
