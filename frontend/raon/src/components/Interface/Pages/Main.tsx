import React, { useEffect } from 'react';
import styled from 'styled-components';
import MainMenu from '../Organisms/MainMenu';
import { useBGM } from '../../../sound/SoundContext';

const Main = () => {
  const { startBGM, isMuted } = useBGM();
  useEffect(() => {
    if (!isMuted) {
      startBGM('main');
    }
  }, []);
  return (
    <div>
      <MainMenu />
    </div>
  );
};

export default Main;
