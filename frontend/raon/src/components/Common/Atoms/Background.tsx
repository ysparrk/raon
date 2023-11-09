import React from 'react';
import styled from 'styled-components';
import backgroundImage from '../../../assets/Images/backgroundimage.png';
import VolumeBtn from './MuteBtn';
import { useBGM } from '../../../sound/SoundContext';

const MuteBoxWrapper = styled.div`
  position: fixed;
  top: 3%;
  right: 2%;
  z-index: 100;
  /* pointer-events: none; */
`;

const BackgroundImage: React.FC = () => {
  const { startBGM, stopBGM, toggleMute, isMuted } = useBGM();

  const handleVolumeButtonClick = async () => {
    console.log('눌렀어요!');
    await toggleMute();
    if (isMuted) {
      console.log('재생하기');
      setTimeout(() => {
        startBGM('main');
      }, 300);
    } else {
      console.log('멈추기');
      stopBGM();
    }
  };
  return (
    <>
      <div
        style={{
          backgroundImage: `url(${backgroundImage})`,
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'center',
          position: 'fixed',
          top: 0,
          left: 0,
          width: '100%',
          height: '100%',
          zIndex: -1,
        }}
      />
      {localStorage.accessToken && (
        <MuteBoxWrapper>
          <VolumeBtn
            isActive={isMuted}
            onClick={() => {
              handleVolumeButtonClick();
            }}
          />
        </MuteBoxWrapper>
      )}
    </>
  );
};

export default BackgroundImage;
