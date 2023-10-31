import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface JoinButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
}

const RoomExitButton = ({ onClick }: JoinButtonProps) => {
  const textStyle: React.CSSProperties = {
    fontFamily: "'CookieRun'",
    cursor: 'pointer',
    color: '#FFCD4A',
    fontSize: '6.25rem',
  };

  return (
    <div style={textStyle} onClick={onClick} role="button" tabIndex={0}>
      나가기
    </div>
  );
};

RoomExitButton.defaultProps = {
  onClick: console.log('test'),
};

export default RoomExitButton;
