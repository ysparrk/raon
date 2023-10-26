import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import ActionButton from '../Atoms/ActionButton';
import MicButton from '../Atoms/MicButton';
import SummarizeInputBox from '../Atoms/SummarizeInputBox';

const UserInputPartDiv = styled.div`
  display: flex;
  flex-direction: column;
  width: 330px;
  height: 450px;
`;

const TopInputPartDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: end;
  align-items: center;
  width: 100%;
  height: 40%;
  margin: 0;
`;
const BotInputPartDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 60%;
  margin: 0;
`;

function UserInputPart() {
  const navigate = useNavigate();
  const [isRecord, setIsRecord] = useState(false);
  const [isTyping, setIsTyping] = useState(false);
  if (isTyping) {
    return (
      <UserInputPartDiv>
        <TopInputPartDiv>
          <SummarizeInputBox />
        </TopInputPartDiv>
        <BotInputPartDiv>
          <ActionButton
            optionText="녹음하기"
            btnColor="lightgrey"
            onClick={() => {
              alert('제출하기를 눌러주세요');
            }}
          />
          <ActionButton
            optionText="직접 입력"
            btnColor="lightgrey"
            onClick={() => {
              alert('제출하기를 눌러주세요');
            }}
          />
          <ActionButton
            optionText="제출하기"
            btnColor="yellow"
            onClick={() => {
              navigate('/game/summarize-result');
            }}
          />
        </BotInputPartDiv>
      </UserInputPartDiv>
    );
  }
  return (
    <UserInputPartDiv>
      <TopInputPartDiv>
        <MicButton />
        {/* <SummarizeInputBox /> */}
      </TopInputPartDiv>
      <BotInputPartDiv>
        <ActionButton
          optionText="녹음하기"
          btnColor="coral"
          onClick={() => {
            alert('미구현');
          }}
        />
        <ActionButton
          optionText="직접 입력"
          btnColor="lightblue"
          onClick={() => setIsTyping(true)}
        />
        <ActionButton
          optionText="제출하기"
          btnColor="lightgrey"
          onClick={() => {
            alert('녹음하기 혹은 직접 입력을 통해 값을 먼저 입력해주세요');
          }}
        />
      </BotInputPartDiv>
    </UserInputPartDiv>
  );
}

export default UserInputPart;
