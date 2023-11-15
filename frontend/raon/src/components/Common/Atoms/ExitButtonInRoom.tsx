import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import eraser from '../../../assets/Images/eraser.png';

interface JoinButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
}

const RoomExitButton = ({ onClick }: JoinButtonProps) => {
  const textStyle: React.CSSProperties = {
    fontFamily: "'CookieRun'",
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
    <div style={textStyle} onClick={onClick} role="button" tabIndex={0}>
      나가기
    </div>
  );
};

RoomExitButton.defaultProps = {
  onClick: console.log('test'),
};

export default RoomExitButton;
