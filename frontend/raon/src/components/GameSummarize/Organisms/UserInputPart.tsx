import React, { useState } from 'react';
import styled from 'styled-components';
import ActionButton from '../Atoms/ActionButton';
import MicButton from '../Atoms/MicButton';
import SummarizeInputBox from '../Atoms/SummarizeInputBox';

const UserInputPartDiv = styled.div`
  display: flex;
  flex-direction: column;
  width: 400px;
  height: 600px;
  background-color: grey;
`;

const TopInputPartDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: end;
  align-items: center;
  width: 100%;
  height: 55%;
  margin: 0;
`;
const BotInputPartDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 45%;
  margin: 0;
`;

function UserInputPart() {
  const [isRecord, setIsRecord] = useState(false);
  const [isTyping, setIsTyping] = useState(false);
  return (
    <UserInputPartDiv>
      <TopInputPartDiv>
        <MicButton />
      </TopInputPartDiv>
      <BotInputPartDiv>
        <ActionButton />
        <ActionButton />
        <ActionButton />
      </BotInputPartDiv>
    </UserInputPartDiv>
  );
}

export default UserInputPart;
