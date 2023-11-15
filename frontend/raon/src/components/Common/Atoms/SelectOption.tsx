import React from 'react';
import styled from 'styled-components';

interface SelectOptionProps {
  imgSrc?: string;
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  optionText?: string;
  isAble?: boolean;
}

const SelectOptionDiv = styled.div`
  display: flex;
  position: relative;
  width: 35.625rem;
  height: 7.5rem;
  margin: 1.25rem;
`;
const Circle = styled.div<SelectOptionProps>`
  position: absolute;
  top: 0%;
  left: 0%;
  z-index: 3;
  width: 12.5rem;
  height: 12.5rem;
  border-radius: 50%;
  border: 0.5rem solid black;
  background-image: ${({ imgSrc }) => (imgSrc ? `url(${imgSrc})` : 'none')};
  background-size: cover;
  background-color: lightgrey;
`;

const Square = styled.div`
  position: absolute;
  display: flex;
  top: 2.1875rem;
  left: 8.75rem;
  width: 25rem;
  height: 7.5rem;
  border-radius: 1.875rem;
  border: 0.5rem solid black;
  align-items: center;
  justify-content: right;
  font-size: 3.75rem;
  font-family: 'ONE-Mobile-POP';
  padding: 0.5rem;
  background-color: white;
`;
const DarkSquare = styled.div`
  position: absolute;
  display: flex;
  top: 2.1875rem;
  left: 8.75rem;
  width: 25rem;
  height: 7.5rem;
  border-radius: 1.875rem;
  border: 0.5rem solid black;
  align-items: center;
  justify-content: right;
  font-size: 3.75rem;
  font-family: 'ONE-Mobile-POP';
  padding: 0.5rem;
  background-color: darkgray;
`;

function SelectOption({
  optionText,
  imgSrc,
  onClick,
  isAble,
}: SelectOptionProps) {
  if (isAble) {
    return (
      <SelectOptionDiv>
        <Circle onClick={onClick} imgSrc={imgSrc} />
        <Square onClick={onClick}>{optionText}</Square>
      </SelectOptionDiv>
    );
  }
  return (
    <SelectOptionDiv>
      <Circle onClick={onClick} imgSrc={imgSrc} />
      <DarkSquare onClick={onClick}>{optionText}</DarkSquare>
    </SelectOptionDiv>
  );
}

SelectOption.defaultProps = {
  optionText: '',
  imgSrc: '',
  onClick: console.log('test'),
  isAble: true,
};

export default SelectOption;
