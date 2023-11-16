import React, { useEffect } from 'react';
import SpellingProblem from '../Organisms/SpellingProblem';
import TitleBox from '../../Common/Atoms/TitleBox';
import { useBGM } from '../../../sound/SoundContext';

const SpellingQuiz = () => {
  const { startBGM, isMuted } = useBGM();
  useEffect(() => {
    if (!isMuted) {
      startBGM('singleGame');
    }
  }, []);
  return (
    <div>
      <TitleBox>맞춤법 놀이</TitleBox>
      <SpellingProblem />
    </div>
  );
};

export default SpellingQuiz;
