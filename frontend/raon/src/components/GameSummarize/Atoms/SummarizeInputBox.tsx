import React, { useState, ChangeEvent } from 'react';
import styled, { css } from 'styled-components';

const InputTextarea = styled.textarea`
  width: 300px;
  height: 360px;
  font-family: 'CookieRun';
  font-size: 20px;
  border-radius: 20px;
  background-color: white;
  box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.5);
  justify-content: center;
  text-align: left;
  resize: none;
  padding: 10px;
`;

const Placeholder = styled.div<{ isHidden: boolean }>`
  font-size: 30px;
  font-family: 'CookieRun';
  position: absolute;
  pointer-events: none;
  top: 155px;
  left: 20px;
  color: #999;
  display: ${(props) => (props.isHidden ? 'none' : 'block')};
`;

function SummarizeInputBox() {
  const [text, setText] = useState('');

  const handleTextareaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    setText(event.target.value);
  };

  const isPlaceholderHidden = text !== '';

  return (
    <div style={{ position: 'relative' }}>
      <InputTextarea
        placeholder=" "
        value={text}
        onChange={handleTextareaChange}
      />
      <Placeholder isHidden={isPlaceholderHidden}>
        요약문을 작성해주세요
      </Placeholder>
    </div>
  );
}

export default SummarizeInputBox;
