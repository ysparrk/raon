import React from 'react';
import styled from 'styled-components';
import SpellingAnswer from '../Organisms/SpellingAnswer';
import TitleBox from '../../Common/Atoms/TitleBox';

const TestDiv = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 12.5rem;
`;

const TestDiv2 = styled.div`
  font-family: 'CookieRun';
  font-size: 12.5rem;
`;

const SpellingResult = () => {
  return (
    <div>
      <TitleBox>맞춤법 놀이</TitleBox>
      <SpellingAnswer />
    </div>
  );
};

export default SpellingResult;
