import React, { createContext, useState, useContext, useMemo } from 'react';
import useSound from 'use-sound';
import mainBGM from './SoundAssets/Backgrounds/audio_hero_Preschoolers_SIPML_Q-0125.mp3';
import correctSound from './SoundAssets/Effects/zapsplat_fantasy_magic_spell_wand_ping_classic_mallet_correct_right_answer_002_88659.mp3';

const BGMContext = createContext<
  | {
      startBGM: (bgmType: 'main' | 'correct') => void;
      stopBGM: () => void;
      toggleMute: () => void; // 무음 모드를 토글하기 위한 함수
      isMuted: boolean; // 무음 모드 상태를 나타내는 변수
    }
  | undefined
>(undefined);

export const BGMProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [playMain, { stop: stopMain }] = useSound(mainBGM, {
    volume: 0.4,
    loop: true,
  });
  //   const [playStage, { stop: stopStage }] = useSound(stageBGM, {
  //     volume: 0.4,
  //     loop: true,
  //   });
  //   const [playDraw, { stop: stopDraw }] = useSound(drawBGM, {
  //     volume: 0.4,
  //     loop: true,
  //   });
  const [playCorrect] = useSound(correctSound, {
    volume: 0.4,
  });
  const [mainPlaying, setMainPlaying] = useState(false);
  //   const [stagePlaying, setStagePlaying] = useState(false);
  //   const [drawPlaying, setDrawPlaying] = useState(false);
  const [isMuted, setIsMuted] = useState(false); // 초기값은 무음 모드가 아님

  // useMemo를 사용하여 value 객체를 최적화
  const value = useMemo(
    () => ({
      mainPlaying,
      //   stagePlaying,
      //   drawPlaying,
      isMuted,
      startBGM: (bgmType: 'main' | 'correct') => {
        console.log('play', isMuted);
        if (!isMuted) {
          switch (bgmType) {
            case 'main':
              if (!mainPlaying) {
                // if (stagePlaying) {
                //   stopStage();
                //   setStagePlaying(false);
                // }
                // if (drawPlaying) {
                //   stopDraw();
                //   setDrawPlaying(false);
                // }
                playMain();
                setMainPlaying(true);
              }
              break;
            // case 'stage':
            //   if (!stagePlaying) {
            //     if (mainPlaying) {
            //       stopMain();
            //       setMainPlaying(false);
            //     }
            //     if (drawPlaying) {
            //       stopDraw();
            //       setDrawPlaying(false);
            //     }
            //     playStage();
            //     setStagePlaying(true);
            //   }
            //   break;
            case 'correct':
              playCorrect();
              break;
            default:
              break;
          }
        }
      },
      stopBGM: () => {
        if (mainPlaying) {
          stopMain();
          setMainPlaying(false);
        }
        // if (stagePlaying) {
        //   stopStage();
        //   setStagePlaying(false);
        // }
        // if (drawPlaying) {
        //   stopDraw();
        //   setDrawPlaying(false);
        // }
      },
      toggleMute: () => {
        setIsMuted(!isMuted); // 무음 모드를 토글
      },
    }),
    [
      mainPlaying,
      //   stagePlaying,
      playMain,
      stopMain,
      //   playStage,
      //   stopStage,
      playCorrect,
      isMuted,
    ],
  );

  return <BGMContext.Provider value={value}>{children}</BGMContext.Provider>;
};

export const useBGM = () => {
  const context = useContext(BGMContext);
  if (context === undefined) {
    throw new Error('useBGM must be used within a BGMProvider');
  }
  return context;
};
