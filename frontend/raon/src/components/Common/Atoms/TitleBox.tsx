import React from 'react';
import styled from 'styled-components';

interface TitleBoxProps {
  children: React.ReactNode;
}

const Box = styled.div`
  width: 645px; //
  height: 89px; //
  background-color: #ffcd4a;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Text = styled.div`
  font-family: 'CookieRun';
  color: #000000;
  font-size: 48px;
`;

const TitleBox = ({ children }: TitleBoxProps) => {
  return (
    <Box>
      <Text>{children}</Text>
    </Box>
  );
};

export default TitleBox;
