import React from 'react';
import styled from 'styled-components';

interface SelectOptionProps {
  imgSrc?: string;
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  optionText?: string;
}

const SelectOptionDiv = styled.div`
  display: flex;
  position: relative;
  width: 660px;
  height: 248px;
  margin: 15px;
`;
const Circle = styled.div<SelectOptionProps>`
  position: absolute;
  top: 0%;
  left: 0%;
  z-index: 3;
  width: 200px;
  height: 200px;
  border-radius: 50%;
  border: 8px solid black;
  background-image: ${({ imgSrc }) => (imgSrc ? `url(${imgSrc})` : 'none')};
  background-size: cover;
  background-color: lightgrey;
`;

const Square = styled.div`
  position: absolute;
  display: flex;
  top: 35px;
  left: 140px;
  width: 400px;
  height: 120px;
  border-radius: 30px;
  border: 8px solid black;
  align-items: center;
  justify-content: right;
  font-size: 60px;
  font-family: 'ONE-Mobile-POP';
  padding: 8px;
  background-color: white;
`;

function SelectOption({ optionText, imgSrc, onClick }: SelectOptionProps) {
  return (
    <SelectOptionDiv>
      <Circle onClick={onClick} imgSrc={imgSrc} />
      <Square onClick={onClick}>{optionText}</Square>
    </SelectOptionDiv>
  );
}

SelectOption.defaultProps = {
  optionText: '',
  imgSrc: '',
  onClick: console.log('test'),
};

export default SelectOption;
