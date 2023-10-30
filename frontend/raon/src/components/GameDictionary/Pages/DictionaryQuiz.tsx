import React from 'react';
import styled from 'styled-components';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButton';
import JoinInterface from '../Organisms/JoinInterface';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const DictionaryJoin = () => {
  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>
        <JoinInterface />
      </ContentDiv>
      <ExitButton to="/main" />
    </div>
  );
};

export default DictionaryJoin;
