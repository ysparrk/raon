import React from 'react';
import styled from 'styled-components';

const StyledButton = styled.button`
  background-color: white;
  font-family: CookieRun;
  border: 4px solid black;
  padding: 16px 60px;
  border-radius: 10px;
  cursor: pointer;
  transition: 0.3s;
  font-size: 24px;
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
