import React from 'react';
import styled from 'styled-components';
import mic from '../../../assets/Images/mic.png';

interface MicButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
}

const MicDiv = styled.div`
  background-image: url(${mic});
  background-size: cover;
  width: 130px;
  height: 130px;
`;

function MicButton({ onClick }: MicButtonProps) {
  return <MicDiv onClick={onClick} />;
}

MicButton.defaultProps = {
  onClick: console.log('test'),
};

export default MicButton;
