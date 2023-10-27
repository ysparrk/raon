import React from 'react';
import styled from 'styled-components';
import UserInputPart from '../Organisms/UserInputPart';
import OriginalText from '../Organisms/OriginalText';
import TitleBox from '../../Common/Atoms/TitleBox';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
`;

function SummarizeQuizPage() {
  return (
    <div>
      <TitleBox>문해력 기르기</TitleBox>
      <ContentDiv>
        <OriginalText />
        <UserInputPart />
      </ContentDiv>
    </div>
  );
}

export default SummarizeQuizPage;
