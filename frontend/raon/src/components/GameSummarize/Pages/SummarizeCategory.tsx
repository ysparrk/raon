import React from 'react';
import styled from 'styled-components';
import TitleBox from '../../Common/Atoms/TitleBox';
import ArticleCategory from '../Organisms/ArticleCategory';
import ExitButton from '../../Common/Atoms/ExitButton';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

function SummarizeCategoryPage() {
  return (
    <div>
      <TitleBox>문해력 기르기</TitleBox>
      <ArticleCategory />
    </div>
  );
}

export default SummarizeCategoryPage;
