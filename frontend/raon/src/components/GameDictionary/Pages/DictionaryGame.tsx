import React from 'react';
import styled from 'styled-components';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButton';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const DictionaryGame = () => {
  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>내용물</ContentDiv>
      <ExitButton to="/game/dictionary-quiz" />
    </div>
  );
};

export default DictionaryGame;
