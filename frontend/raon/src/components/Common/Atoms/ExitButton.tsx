import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import eraser from '../../../assets/Images/eraser.png';

const ExitButton = ({ to }: { to: string }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(to);
  };

  const Container = styled.div`
    display: flex;
    padding-left: 62.5rem; // 이거 사이즈 반응형으로 하려면 다시 지정해야하는데
  `;

  const textStyle: React.CSSProperties = {
    fontFamily: "'ONE-Mobile-POP'",
    cursor: 'pointer',
    color: 'ivory',
    fontSize: '6rem',
    WebkitTextStroke: '0.05rem navy',
    backgroundImage: `url(${eraser})`,
    backgroundSize: 'cover',
    width: '17.5rem',
    height: '8.25rem',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
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
