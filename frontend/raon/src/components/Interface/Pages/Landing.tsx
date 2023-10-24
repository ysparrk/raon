import React from 'react';
import styled from 'styled-components';

const TestDiv = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 200px;
`;
const TestDiv2 = styled.div`
  font-family: 'CookieRun';
  font-size: 200px;
`;
function LandingPage() {
  return (
    <div>
      <TestDiv>Hello 원모바일</TestDiv>
      <TestDiv2>Hello 쿠키런</TestDiv2>
    </div>
  );
}

export default LandingPage;
