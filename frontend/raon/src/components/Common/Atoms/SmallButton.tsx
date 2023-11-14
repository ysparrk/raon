import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface StartButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  content?: string;
  fontColor?: string;
}

const SmallButton = ({ onClick, content, fontColor }: StartButtonProps) => {
  const textStyle: React.CSSProperties = {
    fontFamily: "'ONE-Mobile-POP'",
    cursor: 'pointer',
    // color: fontColor || 'black',
    // WebkitTextStroke: '1px black',
    color: 'white',
    fontSize: '2.125rem',
  };

  const buttonStyle: React.CSSProperties = {
    backgroundColor: `${fontColor}`,
    borderRadius: '0.75rem',
    boxShadow: `0.0325rem 0.0325rem 0.125rem lightgray`,
    padding: '0.375rem',
    cursor: 'pointer',
  };

  return (
    <div style={buttonStyle} onClick={onClick} role="button" tabIndex={0}>
      <div style={textStyle}>{content}</div>
    </div>
  );
};

SmallButton.defaultProps = {
  onClick: console.log('test'),
  content: 'test',
  fontColor: 'black',
};

export default SmallButton;
