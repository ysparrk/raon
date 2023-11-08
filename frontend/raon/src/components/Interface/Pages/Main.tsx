import React, { useEffect } from 'react';
import styled from 'styled-components';
import MainMenu from '../Organisms/MainMenu';
import { useBGM } from '../../../sound/SoundContext';

const Main = () => {
  const { startBGM } = useBGM();

  useEffect(() => {
    const startAudioContext = async () => {
      const audioContext = new AudioContext();
      audioContext.resume().then(() => {
        startBGM('main'); // 오디오 시작
      }); // 오디오 컨텍스트 시작
    };

    startAudioContext();
  }, []);
  return (
    <div>
      <MainMenu />
    </div>
  );
};

export default Main;
