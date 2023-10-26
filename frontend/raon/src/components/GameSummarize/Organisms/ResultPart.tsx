import React from 'react';
import styled from 'styled-components';
import ActionButton from '../Atoms/ActionButton';

const ResultDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 800px;
  height: 450px;
`;

const ResultText = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 150px;
  font-family: 'ONE-Mobile-POP';
  color: white;
  height: 65%;
`;

const ResultBtn = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 35%;
`;

function ResultPart() {
  return (
    <ResultDiv>
      <ResultText>대단해요!</ResultText>
      <ResultBtn>
        <ActionButton optionText="피드백 보기" />
      </ResultBtn>
    </ResultDiv>
  );
}

export default ResultPart;
