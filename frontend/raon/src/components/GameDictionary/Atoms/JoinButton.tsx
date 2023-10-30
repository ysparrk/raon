import React from 'react';
import styled from 'styled-components';

interface JoinButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  optionText?: string;
  buttoncolor?: string;
}

const ButtonDiv = styled.div<JoinButtonProps>`
  display: flex;
  width: 25rem;
  height: 15.625rem;
  margin: 3.125rem;
  font-family: 'CookieRun';
  font-size: 3.75rem;
  border-radius: 1.25rem;
  box-shadow: 0.0625rem 0.0625rem 0.3125rem rgba(100, 100, 100, 0.5);
  background-color: ${(props) => props.buttoncolor};
  justify-content: center;
  align-items: center;
`;

function JoinButton({ onClick, optionText, buttoncolor }: JoinButtonProps) {
  return (
    <ButtonDiv onClick={onClick} buttoncolor={buttoncolor}>
      {optionText}
    </ButtonDiv>
  );
}

JoinButton.defaultProps = {
  optionText: '',
  onClick: console.log('test'),
  buttoncolor: 'white',
};

export default JoinButton;
