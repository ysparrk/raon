import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router';
import SingleModeResult from '../Organisms/SingleModeResult';
import TitleBox from '../../Common/Atoms/TitleBox';
import ExitButton from '../../Common/Atoms/ExitButtonInRoom';
import gildong from '../../../assets/Images/gildong.png';

const GildongImage = styled.img<{ shouldMove: boolean }>`
  width: 300px;
  height: 450px;
  transition: transform 1s ease-in-out;

  position: absolute;
  bottom: 0px;
  right: -400px;
  z-index: 2;
  transform: ${({ shouldMove }) =>
    shouldMove ? 'translateX(-380px)' : 'translateX(0px)'};
`;

const GildongImageSecond = styled.img<{ shouldMove: boolean }>`
  width: 300px;
  height: 450px;
  transition: transform 1s ease-in-out;
  transform: ${({ shouldMove }) =>
    `${shouldMove ? 'translateX(380px)' : 'translateX(0px)'} scaleX(-1)`};
  position: absolute;
  bottom: 0;
  left: -400px;
`;
const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
function SummarizeResultPage() {
  const [shouldMoveRight, setShouldMoveRight] = useState(false);
  const [shouldMoveLeft, setShouldMoveLeft] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    // 오른쪽 이미지를 움직이게 하는 타이머
    const timerRight = setTimeout(() => setShouldMoveRight(true), 1500);

    // 왼쪽 이미지를 움직이게 하는 타이머
    let timerLeft: number | undefined;

    // 오른쪽 이미지가 돌아온 후 왼쪽 이미지를 움직이게 하는 인터벌
    const interval = setInterval(() => {
      setShouldMoveRight((prev) => {
        if (prev) {
          // 오른쪽 이미지가 움직였으면, 왼쪽 이미지 타이머를 설정
          timerLeft = setTimeout(() => setShouldMoveLeft(true), 1500);
        }
        return !prev;
      });

      setShouldMoveLeft((prev) => {
        if (!prev) {
          // 왼쪽 이미지가 돌아왔으면, 타이머를 정리
          clearTimeout(timerLeft);
        }
        return !prev;
      });
    }, 6000); // 전체 시퀀스를 반복하는 인터벌

    return () => {
      clearTimeout(timerRight);
      clearTimeout(timerLeft);
      clearInterval(interval);
    };
  }, []);

  return (
    <div>
      <GildongImage src={gildong} alt="gildong" shouldMove={shouldMoveRight} />
      <GildongImageSecond
        src={gildong}
        alt="gildong"
        shouldMove={shouldMoveLeft}
      />
      <TitleBox>국어사전 놀이</TitleBox>
      <ContentDiv>
        <SingleModeResult />
      </ContentDiv>
      <ExitButton
        onClick={() => {
          navigate('/main');
        }}
      />
    </div>
  );
}

export default SummarizeResultPage;
