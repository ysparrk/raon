import React from 'react';
import { useNavigate } from 'react-router-dom';

const ExitButton = ({ to }: { to: string }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(to);
  };

  const textStyle: React.CSSProperties = {
    fontFamily: "'CookieRun'",
    cursor: 'pointer',
    color: '#FFCD4A',
    fontSize: '100px',
  };

  return (
    <div>
      <div style={textStyle} onClick={handleClick} role="button" tabIndex={0}>
        나가기
      </div>
    </div>
  );
};

export default ExitButton;
