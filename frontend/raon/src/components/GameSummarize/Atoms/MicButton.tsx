import React from 'react';
import styled from 'styled-components';
import mic from '../../../assets/Images/mic.png';

interface MicButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
}

const MicDiv = styled.div`
  background-image: url(${mic});
  background-size: cover;
  width: 8.125rem;
  height: 8.125rem;
`;

function MicButton({ onClick }: MicButtonProps) {
  return <MicDiv onClick={onClick} />;
}

MicButton.defaultProps = {
  onClick: console.log('test'),
};

export default MicButton;
