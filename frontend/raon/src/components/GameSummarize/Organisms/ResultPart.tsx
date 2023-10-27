import React, { useState } from 'react';
import styled from 'styled-components';
import ActionButton from '../Atoms/ActionButton';
import BlurView from '../Atoms/BlurView';
import ResultModal from '../Atoms/ResultModal';

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
  const [isDetail, setIsDetail] = useState(false);
  return (
    <ResultDiv>
      {isDetail && (
        <>
          <BlurView />
          <ResultModal onClick={() => setIsDetail(false)} />
        </>
      )}
      <ResultText>대단해요!</ResultText>
      <ResultBtn>
        <ActionButton
          optionText="피드백 보기"
          onClick={() => setIsDetail(true)}
          buttoncolor="white"
        />
      </ResultBtn>
    </ResultDiv>
  );
}

export default ResultPart;
