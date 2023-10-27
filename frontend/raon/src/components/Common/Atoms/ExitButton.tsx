import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const ExitButton = ({ to }: { to: string }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(to);
  };

  const Container = styled.div`
    display: flex;
    padding-left: 1000px; // 이거 사이즈 반응형으로 하려면 다시 지정해야하는데
  `;

  const textStyle: React.CSSProperties = {
    fontFamily: "'CookieRun'",
    cursor: 'pointer',
    color: '#FFCD4A',
    fontSize: '100px',
  };

  return (
    <Container>
      <div style={textStyle} onClick={handleClick} role="button" tabIndex={0}>
        나가기
      </div>
    </Container>
  );
};

export default ExitButton;
