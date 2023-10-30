import React, { useState, useEffect, ChangeEvent } from 'react';
import { useSetRecoilState } from 'recoil';
import styled, { css } from 'styled-components';
import { summarizeState } from '../../../recoil/Atoms';

const InputTextarea = styled.textarea`
  width: 18.75rem;
  height: 15rem;
  font-family: 'CookieRun';
  font-size: 1.25rem;
  border-radius: 1.25rem;
  background-color: white;
  box-shadow: 0.0625rem 0.0625rem 0.3125rem rgba(0, 0, 0, 0.5);
  justify-content: center;
  text-align: left;
  resize: none;
  padding: 0.625rem;
`;

const Placeholder = styled.div<{ ishidden: boolean }>`
  font-size: 1.875rem;
  font-family: 'CookieRun';
  position: absolute;
  pointer-events: none;
  top: 6.875rem;
  left: 1.375rem;
  color: #999;
  display: ${(props) => (props.ishidden ? 'none' : 'block')};
`;

function SummarizeInputBox() {
  const [text, setText] = useState('');
  const setSummarize = useSetRecoilState(summarizeState);

  useEffect(() => {
    setSummarize('');
  }, []);

  const handleTextareaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    setText(event.target.value);
    setSummarize(event.target.value);
  };

  const isPlaceholderHidden = text !== '';

  return (
    <div style={{ position: 'relative' }}>
      <InputTextarea
        placeholder=" "
        value={text}
        onChange={handleTextareaChange}
      />
      <Placeholder ishidden={!!isPlaceholderHidden}>
        요약문을 작성해주세요
      </Placeholder>
    </div>
  );
}

export default SummarizeInputBox;
