import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface JoinButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
}

const StartButton = ({ onClick }: JoinButtonProps) => {
  const textStyle: React.CSSProperties = {
    fontFamily: "'CookieRun'",
    cursor: 'pointer',
    color: 'black',
    fontSize: '6.25rem',
  };

  return (
    <div style={textStyle} onClick={onClick} role="button" tabIndex={0}>
      시작하기
    </div>
  );
};

StartButton.defaultProps = {
  onClick: console.log('test'),
};

export default StartButton;
