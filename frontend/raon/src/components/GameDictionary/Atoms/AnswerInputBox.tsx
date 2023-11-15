import React, { useState, ChangeEvent, KeyboardEvent } from 'react';

interface AnswerInputBoxProps {
  inputText: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  onEnter: () => void;
}

const AnswerInputBox = ({
  inputText,
  onChange,
  onEnter,
}: AnswerInputBoxProps) => {
  const [isFocused, setIsFocused] = useState(false);
  const maxCharacterLimit = 3;

  const handleFocus = () => {
    setIsFocused(true);
  };

  const handleBlur = () => {
    setIsFocused(false);
  };

  const handleFormSubmit = (event: React.FormEvent) => {
    event.preventDefault();
  };
  const handleKeyPress = (event: KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      // Enter 키를 눌렀을 때 onEnter 함수 호출
      onEnter();
    }
  };
  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    // 정규표현식을 사용하여 한글만 허용
    const koreanOnly = /^[ㄱ-ㅎ가-힣]*$/;
    if (
      koreanOnly.test(event.target.value) &&
      event.target.value.length <= maxCharacterLimit
    ) {
      onChange(event);
    }
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
        onChange={handleInputChange}
        onKeyPress={handleKeyPress}
        autoComplete="off"
      />
    </form>
  );
};

export default AnswerInputBox;
