import React from 'react';
import styled from 'styled-components';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButton';
import WaitInterface from '../Organisms/WaitInterface';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const DictionaryQuiz = () => {
  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>
        <WaitInterface />
      </ContentDiv>
    </div>
  );
};

export default DictionaryQuiz;
