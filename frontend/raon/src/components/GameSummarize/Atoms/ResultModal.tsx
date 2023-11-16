import React from 'react';
import styled from 'styled-components';
import { useRecoilValue } from 'recoil';
import { summarizeState } from '../../../recoil/Atoms.tsx';

interface ResultModalProps {
  spellResult?: string;
  feedbackResult?: string;
  onClick?: React.MouseEventHandler<HTMLDivElement>;
}

const ResultModalDiv = styled.div`
  position: absolute;
  z-index: 2;
  top: 5%;
  width: 62.5rem;
  height: 40.625rem;
  padding: 0.625rem;
  font-family: 'CookieRun';
  font-size: 1.5rem;
  border-radius: 0.9375rem;
  border: 0.375rem solid black;
  background-color: #ffcd4a;
  justify-content: center;
  align-items: center;
`;

const ResultModalHeader = styled.p`
  font-family: 'CookieRun';
  font-size: 2.5rem;
  font-weight: 1000;
  color: #ffebb6;
  text-shadow:
    -0.0938rem -0.0938rem 0 #000,
    0.0938rem -0.0938rem 0 #000,
    -0.0938rem 0.0938rem 0 #000,
    0.0938rem 0.0938rem 0 #000;
`;

const ResultModalContent = styled.div`
  width: 61.4375rem;
  height: 7.5rem;
  padding: 0.125rem;
  margin: 0.25rem;
  font-family: 'CookieRun';
  font-size: 1.375rem;
  border-radius: 0.9375rem;
  border: 0.1875rem solid black;
  box-shadow: 0.125rem 0.125rem 0.25rem rgb(0, 0, 0, 0.5);
  background-color: white;
`;

const ResultModalExit = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 3.125rem;
  margin-left: 53.125rem;
  margin-top: 1.25rem;
  padding: 0rem;
`;
function ResultModal({
  spellResult,
  feedbackResult,
  onClick,
}: ResultModalProps) {
  const summarize = useRecoilValue(summarizeState);
  return (
    <ResultModalDiv>
      <ResultModalHeader>내 요약문</ResultModalHeader>
      <ResultModalContent>{summarize.summarize_content}</ResultModalContent>
      <ResultModalHeader>맞춤법 검사</ResultModalHeader>
      <ResultModalContent>{spellResult}</ResultModalContent>
      <ResultModalHeader>요약 평가</ResultModalHeader>
      <ResultModalContent>{feedbackResult}</ResultModalContent>
      <ResultModalExit onClick={onClick}>나가기</ResultModalExit>
    </ResultModalDiv>
  );
}

ResultModal.defaultProps = {
  spellResult:
    '"오늘의 점심은 돼지고기 김치찌개, 오므라이스, 그리고 소시지였습니다." - 이 부분은 문법적으로 정확한 문장입니다. 맞춤법 오류가 없습니다. "아치는 귀여웠습니다." - 이 부분도 문법적으로 정확한 문장입니다. 맞춤법 오류가 없습니다.',
  feedbackResult:
    '전체 문장은 문법적으로 정확하며, 요약 역시 원본 문장의 내용을 잘 반영하고 있습니다.',
  onClick: console.log('test'),
};
export default ResultModal;
