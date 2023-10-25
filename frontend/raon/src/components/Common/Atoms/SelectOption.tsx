import React from 'react';
import styled from 'styled-components';
import newone from '../../../assets/Images/new.png';

interface SelectOptionProps {
  imgSrc?: string;
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  optionText?: string;
}

const SelectOptionDiv = styled.div`
  display: flex;
  position: relative;
  background-color: grey;
  margin-left: 100px;
  margin-bottom: 100px;
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

function SelectOption() {
  return (
    <SelectOptionDiv>
      <Circle imgSrc={newone} />
      <Square>여섯 글자 짜리</Square>
    </SelectOptionDiv>
  );
}

export default SelectOption;
