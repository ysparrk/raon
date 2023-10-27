import React from 'react';
import styled from 'styled-components';

interface TitleBoxProps {
  children: React.ReactNode;
}

const Container = styled.div`
  display: flex;
  padding-top: 1.25rem;
  padding-left: 6.25rem;
`;

const Box = styled.div`
  width: 40.3125rem;
  height: 5.5625rem;
  background-color: #ffcd4a;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 0.1875rem solid #000000;
`;

const Text = styled.div`
  font-family: 'CookieRun';
  color: #000000;
  font-size: 3rem;
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
