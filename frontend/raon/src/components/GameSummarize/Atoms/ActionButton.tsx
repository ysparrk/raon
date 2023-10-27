import React from 'react';
import styled from 'styled-components';

interface ActionButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  optionText?: string;
  buttoncolor?: string;
}

const ButtonDiv = styled.div<ActionButtonProps>`
  display: flex;
  width: 18.75rem;
  height: 3.75rem;
  margin: 0.9375rem;
  font-family: 'CookieRun';
  font-size: 2.5rem;
  font-weight: 900;
  border-radius: 1.25rem;
  box-shadow: 0.125rem 0.125rem 0.4375rem rgba(0, 0, 0, 0.5);
  background-color: ${(props) => props.buttoncolor};
  justify-content: center;
  align-items: center;
`;

function ActionButton({ onClick, optionText, buttoncolor }: ActionButtonProps) {
  return (
    <ButtonDiv onClick={onClick} buttoncolor={buttoncolor}>
      {optionText}
    </ButtonDiv>
  );
}

ActionButton.defaultProps = {
  optionText: '',
  onClick: console.log('test'),
  buttoncolor: 'white',
};

export default ActionButton;
