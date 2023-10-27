import React from 'react';
import styled from 'styled-components';

const StyledButton = styled.button`
  background-color: white;
  font-family: CookieRun;
  border: 0.25rem solid black;
  padding: 1rem 3.75rem;
  border-radius: 0.625rem;
  cursor: pointer;
  transition: 0.3s;
  font-size: 1.5rem;
`;

const CenteredContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 10vh;
`;

const ProfileButton = () => {
  return (
    <CenteredContainer>
      <StyledButton type="button">내 정보</StyledButton>
    </CenteredContainer>
  );
};

export default ProfileButton;
