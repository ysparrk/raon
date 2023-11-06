import React, { useState, ChangeEvent } from 'react';

interface AnswerInputBoxProps {
  inputText: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

const AnswerInputBox = ({ inputText, onChange }: AnswerInputBoxProps) => {
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
    height: '5.125rem',
    width: '10.25rem',
    padding: '0.625rem',
    borderRadius: '1rem',
    borderColor: isFocused ? '#a2d6ab' : '#c0c0c0',
    borderStyle: 'solid',
    outline: 'none',
    fontFamily: 'ONE-Mobile-POP',
    fontSize: '2.125rem',
    textAlign: 'center',
  };

  return (
    <form style={formStyle} onSubmit={handleFormSubmit}>
      <input
        value={inputText}
        style={inputStyle}
        placeholder="정답 입력"
        onFocus={handleFocus}
        onBlur={handleBlur}
        onChange={onChange}
        autoComplete="off"
      />
    </form>
  );
};

export default AnswerInputBox;
