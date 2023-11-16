import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import QuizLetter from '../Organisms/QuizLetter';
import QuizCrossWord from '../Organisms/QuizCrossWord';
import TitleBox from '../../Common/Atoms/TitleBox';
import { getQuiz } from '../../../api/GameDictionaryApi';
import { dictScoreState } from '../../../recoil/Atoms';
import { useBGM } from '../../../sound/SoundContext';

const ContentDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const CountDiv = styled.div`
  position: fixed;
  display: flex;
  top: 5%;
  right: 5%;
  font-size: 3.125rem;
  font-family: 'ONE-Mobile-POP';
  color: white;
`;
function mergeLists(initialList: any[], directionList: any[], totalItems: 10) {
  const mergedList = [];
  let initialIndex = 0;
  let directionIndex = 0;

  for (let i = 0; i < totalItems; i += 1) {
    if (i % 3 === 2) {
      // Every third item, add a direction question
      const directionQuestion = directionList[directionIndex];
      directionQuestion.type = 'D';
      mergedList.push(directionQuestion);
      directionIndex += 1;
    } else {
      // Otherwise, add an initial question
      const initialQuestion = initialList[initialIndex];
      initialQuestion.type = 'T';
      mergedList.push(initialQuestion);
      initialIndex += 1;
    }
  }

  return mergedList;
}
const DictionaryGame = () => {
  const [quizList, setQuizList] = useState<any[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const navigate = useNavigate();
  const setDictScore = useSetRecoilState(dictScoreState);
  const { startBGM, isMuted } = useBGM();
  useEffect(() => {
    if (!isMuted) {
      startBGM('singleGame');
    }
  }, []);
  useEffect(() => {
    const fetchQuizList = async () => {
      try {
        const response = await getQuiz();
        const combinedQuiz = await mergeLists(
          response.data.data.initialQuizList,
          response.data.data.directionQuizList,
          10,
        );

        setQuizList(combinedQuiz);
      } catch (error) {
        console.log(error);
      }
    };
    if (quizList.length === 0) {
      fetchQuizList();
      setDictScore(0);
    } else {
      console.log(quizList);
    }
  }, []);
  return (
    <div>
      <TitleBox>국어사전 놀이</TitleBox>
      <CountDiv>{currentIndex + 1} / 10</CountDiv>
      <ContentDiv>
        {currentIndex < quizList.length &&
          quizList[currentIndex].type === 'T' && (
            <QuizLetter
              word={quizList[currentIndex].word}
              initial={quizList[currentIndex].initial}
              meaning={quizList[currentIndex].meaning}
              nextClick={() => {
                if (currentIndex < quizList.length - 1) {
                  setCurrentIndex((prevIndex) => prevIndex + 1);
                } else {
                  navigate('/game/dictionary-single-result');
                }
              }}
            />
          )}
        {currentIndex < quizList.length &&
          quizList[currentIndex].type === 'D' && (
            <QuizCrossWord
              word={quizList[currentIndex].answer}
              north_word={quizList[currentIndex].northWord}
              east_word={quizList[currentIndex].eastWord}
              west_word={quizList[currentIndex].westWord}
              south_word={quizList[currentIndex].southWord}
              nextClick={() => {
                if (currentIndex < quizList.length - 1) {
                  setCurrentIndex((prevIndex) => prevIndex + 1);
                } else {
                  navigate('/game/dictionary-result');
                }
              }}
            />
          )}
      </ContentDiv>
    </div>
  );
};

export default DictionaryGame;
