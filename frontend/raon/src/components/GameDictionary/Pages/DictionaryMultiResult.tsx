import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router';
import { useResetRecoilState } from 'recoil';
import { multiDictState, roomManageState } from '../../../recoil/Atoms';
import MultiModeResult from '../Organisms/MultiFinalResult';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';
import { useWebSocket } from '../../../websocket/WebSocketContext';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
function DictionaryMultiResult() {
  const navigate = useNavigate();
  const QuizReset = useResetRecoilState(multiDictState);
  const RoomReset = useResetRecoilState(roomManageState);
  const Stomp = useWebSocket();

  useEffect(() => {
    Stomp.leaveRoom();
  }, []);
  const handleExit = () => {
    QuizReset();
    RoomReset();
    navigate('/main');
  };
  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>
        <MultiModeResult />
        <ExitButton
          onClick={() => {
            handleExit();
          }}
        />
      </ContentDiv>
    </div>
  );
}

export default DictionaryMultiResult;
