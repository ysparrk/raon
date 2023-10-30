import React from 'react';
import styled from 'styled-components';
import ResultPart from '../Organisms/ResultPart';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButton';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
function SummarizeResultPage() {
  return (
    <div>
      <TitleBox>문해력 기르기</TitleBox>
      <ContentDiv>
        <ResultPart />
        <ExitButton to="/main/" />
      </ContentDiv>
    </div>
  );
}

export default SummarizeResultPage;
