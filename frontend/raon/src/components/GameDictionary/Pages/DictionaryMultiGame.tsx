import React, { useState } from 'react';
import styled from 'styled-components';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { useNavigate } from 'react-router-dom';


const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;


const DictionaryWaitingRoom = () => {
  
  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>게임시작</ContentDiv>
    </div>
  );
};

export default DictionaryWaitingRoom;
