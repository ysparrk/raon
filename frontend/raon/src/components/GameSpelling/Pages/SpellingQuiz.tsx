import React from 'react';
import SpellingProblem from '../Organisms/SpellingProblem';
import TitleBox from '../../Common/Atoms/TitleBox';

const SpellingQuiz = () => {
  return (
    <div>
      <TitleBox>맞춤법 놀이</TitleBox>
      <SpellingProblem />
    </div>
  );
};

export default SpellingQuiz;
