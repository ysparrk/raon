import React from 'react';
import styled from 'styled-components';

interface TitleBoxProps {
  children: React.ReactNode;
}

const Container = styled.div`
  display: flex;
  padding-top: 20px;
  padding-left: 100px;
`;

const Box = styled.div`
  width: 645px;
  height: 89px;
  background-color: #ffcd4a;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 3px solid #000000;
`;

const Text = styled.div`
  font-family: 'CookieRun';
  color: #000000;
  font-size: 48px;
`;

const TitleBox = ({ children }: TitleBoxProps) => {
  return (
    <Container>
      <Box>
        <Text>{children}</Text>
      </Box>
    </Container>
  );
};

export default TitleBox;
