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

const DictionaryWaitingRoom = () => {
  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>대기실</ContentDiv>
      <ExitButton to="/game/dictionary-quiz" />
    </div>
  );
};

export default DictionaryWaitingRoom;
