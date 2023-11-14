import React, { useState, ChangeEvent, KeyboardEvent } from 'react';

interface InputBoxProps {
  inputText: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  onEnter: () => void;
}

const SchoolInputBox = ({ inputText, onChange, onEnter }: InputBoxProps) => {
  const [isFocused, setIsFocused] = useState(false);

  const handleFocus = () => {
    setIsFocused(true);
  };

  const handleBlur = () => {
    setIsFocused(false);
  };
  const handleKeyPress = (event: KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      // Enter 키를 눌렀을 때 onEnter 함수 호출
      onEnter();
    }
  };

  const handleFormSubmit = (event: React.FormEvent) => {
    event.preventDefault();
  };

  const formStyle = {
    width: '100%',
  };

  const inputStyle: React.CSSProperties = {
    boxSizing: 'border-box',
    height: '3.125rem',
    width: '31.25rem',
    padding: '0.625rem',
    borderRadius: '1rem',
    borderColor: isFocused ? '#a2d6ab' : '#c0c0c0',
    borderStyle: 'solid',
    outline: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  };

  return (
    <form style={formStyle} onSubmit={handleFormSubmit}>
      <input
        value={inputText}
        style={inputStyle}
        placeholder="값을 입력해주세요"
        onFocus={handleFocus}
        onBlur={handleBlur}
        onChange={onChange}
        onKeyPress={handleKeyPress}
        autoComplete="off"
      />
    </form>
  );
};

export default SchoolInputBox;
