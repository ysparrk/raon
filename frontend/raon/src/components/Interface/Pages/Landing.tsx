import React from 'react';
import styled from 'styled-components';
import InputBox from '../../Common/Atoms/InputBox.tsx';
import ExitButton from '../../Common/Atoms/ExitButton.tsx';
import TitleBox from '../../Common/Atoms/TitleBox.tsx';

const TestDiv = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 200px;
`;
const TestDiv2 = styled.div`
  font-family: 'CookieRun';
  font-size: 200px;
`;
function LandingPage() {
  const [inputValue, setInputValue] = React.useState('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value); // 상태 업데이트를 추가
    console.log(e.target.value); // 여기에 실제 로직을 작성해주세요.
  };

  return (
    <div>
      <TestDiv>Hello 원모바일</TestDiv>
      <TestDiv2>Hello 쿠키런</TestDiv2>
      <InputBox inputText={inputValue} onChange={handleInputChange} />
      <ExitButton to="/" />
      <TitleBox>국어사전 놀이</TitleBox>
    </div>
  );
}

export default LandingPage;
