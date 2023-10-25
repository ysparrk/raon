import React, { useState, ChangeEvent } from 'react';

interface InputBoxProps {
  inputText: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

const InputBox = ({ inputText, onChange }: InputBoxProps) => {
  const [isFocused, setIsFocused] = useState(false);

  const handleFocus = () => {
    setIsFocused(true);
  };

  const handleBlur = () => {
    setIsFocused(false);
  };

  const handleFormSubmit = (event: React.FormEvent) => {
    event.preventDefault();
  };

  const formStyle = {
    width: '100%',
  };
  const inputStyle: React.CSSProperties = {
    boxSizing: 'border-box',
    height: '50px',
    width: '500px',
    padding: '10px',
    borderRadius: '1rem',
    borderColor: isFocused ? '#a2d6ab' : '#c0c0c0',
    borderStyle: 'solid',
    outline: 'none',
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
        autoComplete="off"
      />
    </form>
  );
};

export default InputBox;
