import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface StartButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  content?: string;
  fontColor?: string;
}

const DuplicationCheckButton = ({
  onClick,
  content,
  fontColor,
}: StartButtonProps) => {
  const textStyle: React.CSSProperties = {
    fontFamily: "'ONE-Mobile-POP'",
    cursor: 'pointer',
    color: 'white',
    fontSize: '1.125rem',
  };

  const buttonStyle: React.CSSProperties = {
    backgroundColor: '#fae100',
    borderRadius: '1.5rem',
    padding: '0.5rem 1rem',
    cursor: 'pointer',
    width: '50px',
    textAlign: 'center',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  };

  return (
    <div style={buttonStyle} onClick={onClick} role="button" tabIndex={0}>
      <div style={textStyle}>{content}</div>
    </div>
  );
};

DuplicationCheckButton.defaultProps = {
  onClick: console.log('test'),
  content: '중복체크',
};

export default DuplicationCheckButton;
