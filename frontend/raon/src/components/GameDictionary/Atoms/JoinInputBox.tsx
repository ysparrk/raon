import React, { useState, useEffect, ChangeEvent } from 'react';
import styled from 'styled-components';

interface JoinInputBoxProps {
  onInputChange: (value: string) => void;
}
const InputTextarea = styled.textarea`
  width: 25rem;
  height: 2.1875rem;
  font-family: 'CookieRun';
  font-size: 1.5625rem;
  border-radius: 0.3125rem;
  background-color: white;
  box-shadow: 0.0625rem 0.0625rem 0.3125rem rgba(100, 100, 100, 0.5);
  justify-content: center;
  text-align: center;
  resize: none;
  padding: 0.3125rem;
  margin: 2.8125rem;
`;
function JoinInputBox({ onInputChange }: JoinInputBoxProps) {
  const [inputCode, setInputCode] = useState('');

  const maxCharacterLimit = 20;

  const handleTextareaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    const inputValue = event.target.value;

    // Check if the input length is within the character limit
    if (inputValue.length <= maxCharacterLimit) {
      setInputCode(inputValue);
      onInputChange(inputValue);
    }
  };

  return (
    <div>
      <InputTextarea
        placeholder="참여 코드를 입력해주세요"
        value={inputCode}
        onChange={handleTextareaChange}
      />
    </div>
  );
}

export default JoinInputBox;
