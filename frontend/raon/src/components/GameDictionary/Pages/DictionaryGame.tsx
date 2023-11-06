import React from 'react';
import styled from 'styled-components';
import QuizLetter from '../Organisms/QuizLetter';
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
      <ContentDiv>
        <QuizLetter
          word="하하"
          initial="ㅎㅎ"
          meaning="한쪽으로 치우쳐  도량이 좁고 
          너그럽지 못함"
        />
      </ContentDiv>
    </div>
  );
};

export default DictionaryGame;
