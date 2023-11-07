import React, { useState, ChangeEvent } from 'react';

interface ComboBoxProps {
  selectedOption: string;
  onChange: (e: ChangeEvent<HTMLSelectElement>) => void;
  options: string[];
}

const ComboBox = ({ selectedOption, onChange, options }: ComboBoxProps) => {
  const [isFocused, setIsFocused] = useState(false);

  const handleFocus = () => {
    setIsFocused(true);
  };

  const handleBlur = () => {
    setIsFocused(false);
  };

  const comboBoxStyle: React.CSSProperties = {
    boxSizing: 'border-box',
    height: '3.125rem',
    width: '31.25rem',
    padding: '0.625rem',
    borderRadius: '1rem',
    borderColor: isFocused ? '#a2d6ab' : '#c0c0c0',
    borderStyle: 'solid',
    outline: 'none',
    appearance: 'none',
    backgroundColor: 'white',
    cursor: 'pointer',

    backgroundImage:
      "url(\"data:image/svg+xml;charset=UTF-8,%3Csvg width='14' height='14' viewBox='0 0 24 24' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M7 10l5 5 5-5H7z' fill='%23434A54'/%3E%3C/svg%3E\")",
    backgroundRepeat: 'no-repeat',
    backgroundPosition: 'right 0.7rem top 50%', // 화살표 위치 조정
    backgroundSize: '1.5em', // 화살표 크기 조정
    paddingRight: '2.5em', // 화살표와 텍스트간의 간격 조정
  };
  return (
    <select
      value={selectedOption}
      style={comboBoxStyle}
      onFocus={handleFocus}
      onBlur={handleBlur}
      onChange={onChange}
    >
      {options.map((option, index) => (
        <option key={index} value={option}>
          {option}
        </option>
      ))}
    </select>
  );
};

export default ComboBox;
