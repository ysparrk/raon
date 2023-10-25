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
  width: 820px;
  height: 298px;
  margin: 10px;
`;
const Circle = styled.div<SelectOptionProps>`
  position: absolute;
  top: 0%;
  left: 0%;
  z-index: 3;
  width: 280px;
  height: 280px;
  border-radius: 50%;
  border: 8px solid black;
  background-image: ${({ imgSrc }) => (imgSrc ? `url(${imgSrc})` : 'none')};
  background-size: cover;
  background-color: lightgrey;
`;

const Square = styled.div`
  position: absolute;
  display: flex;
  top: 70px;
  left: 180px;
  width: 600px;
  height: 140px;
  border-radius: 30px;
  border: 8px solid black;
  align-items: center;
  justify-content: right;
  font-size: 80px;
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
