import React from 'react';
import styled from 'styled-components';
import SpellingAnswer from '../Organisms/SpellingAnswer';
import TitleBox from '../../Common/Atoms/TitleBox';

const SpellingResult = () => {
  return (
    <div>
      <TitleBox>맞춤법 놀이</TitleBox>
      <SpellingAnswer />
    </div>
  );
};

export default SpellingResult;
