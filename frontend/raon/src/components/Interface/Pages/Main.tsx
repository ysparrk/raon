import React from 'react';
import styled from 'styled-components';
import MainMenu from '../Organisms/MainMenu';

const TestDiv = styled.div`
  font-family: 'ONE-Mobile-POP';
  font-size: 200px;
`;
const TestDiv2 = styled.div`
  font-family: 'CookieRun';
  font-size: 200px;
`;
const Main = () => {
  return (
    <div>
      <MainMenu />
    </div>
  );
};

export default Main;
