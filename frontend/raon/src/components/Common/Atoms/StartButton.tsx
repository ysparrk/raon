import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface StartButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  content?: string;
  fontColor?: string;
}

const StartButton = ({ onClick, content, fontColor }: StartButtonProps) => {
  const textStyle: React.CSSProperties = {
    fontFamily: "'ONE-Mobile-POP'",
    cursor: 'pointer',
    // color: fontColor || 'black',
    // WebkitTextStroke: '1px black',
    color: 'white',
    fontSize: '6.125rem',
  };

  const buttonStyle: React.CSSProperties = {
    backgroundColor: `${fontColor}`,
    borderRadius: '1.5rem',
    border: `0.3rem solid black`,
    boxShadow: `0.0625rem 0.0625rem 0.25rem black`,
    padding: '0.75rem',
    cursor: 'pointer',
  };

  return (
    <div style={buttonStyle} onClick={onClick} role="button" tabIndex={0}>
      <div style={textStyle}>{content}</div>
    </div>
  );
};

StartButton.defaultProps = {
  onClick: console.log('test'),
  content: 'test',
  fontColor: 'black',
};

export default StartButton;
