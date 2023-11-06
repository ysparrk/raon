import React, { useState, useEffect, useRef } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { getSpellingList } from '../../../api/GameSpellingApi.tsx';
import {
  submitState,
  answerState,
  spellingCountState,
} from '../../../recoil/Atoms.tsx';
import SpellingRight from './SpellingRight.tsx';
import SpellingWrong from './SpellingWrong.tsx';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 60vh;
`;

const Content = styled.div`
  font-family: 'CookieRun';
  font-size: 3.75rem;
  color: white;
  margin-bottom: 6.25rem;
`;

const Options = styled.div`
  display: flex;
  gap: 25rem;
`;

const Option = styled.button`
  font-family: 'ONE-Mobile-POP';
  color: white;
  font-size: 3rem;
  cursor: pointer;
  transition: background-color 0.2s;
  background-color: #202fb2;
  border: 0.1875rem solid black;
  border-radius: 0.9375rem;
  padding: 2.5rem;

  &:hover {
    background-color: rgba(0, 0, 0, 0.05);
  }
`;

type Quiz = {
  content: string;
  option_one: string;
  option_two: string;
  answer: string;
};

const SpellingProblem = () => {
  const navigate = useNavigate();

  const [isRightModalVisible, setRightModalVisible] = useState(false);
  const [isWrongModalVisible, setWrongModalVisible] = useState(false);

  const [quizList, setQuizList] = useState<Quiz[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const setSubmitList = useSetRecoilState(submitState);
  const setAnswerList = useSetRecoilState(answerState);
  const setSpellingCountState = useSetRecoilState(spellingCountState);
  const startTime = useRef(Date.now());

  const handleOptionClick = (option: string) => {
    setSubmitList((prevList: string[]) => [...prevList, option]);

    if (option === quizList[currentIndex]?.answer) {
      setRightModalVisible(true);
    } else {
      setWrongModalVisible(true);
    }
  };

  const handleCloseModal = () => {
    setRightModalVisible(false);
    setWrongModalVisible(false);

    if (currentIndex < quizList.length - 1) {
      setCurrentIndex((prevIndex) => prevIndex + 1);
    } else {
      const endTime = Date.now();
      setSpellingCountState(endTime - startTime.current);
      navigate('/game/spelling-result');
    }
  };

  useEffect(() => {
    const fetchQuizList = async () => {
      try {
        const response = await getSpellingList();
        console.log(response.data.data);
        if (response && response.data && Array.isArray(response.data.data)) {
          setQuizList(response.data.data);
        }
      } catch (error) {
        console.log('catcherror 발생');
      }
    };

    startTime.current = Date.now();
    fetchQuizList();
  }, []);

  useEffect(() => {
    const correctAnswers = quizList.map((quiz) => quiz.answer);
    setAnswerList(correctAnswers);
    setSubmitList([]);
  }, [quizList]);

  return (
    <Container>
      <Content>{quizList[currentIndex]?.content}</Content>
      <Options>
        <Option
          onClick={() => handleOptionClick(quizList[currentIndex]?.option_one)}
        >
          {quizList[currentIndex]?.option_one}
        </Option>
        <Option
          onClick={() => handleOptionClick(quizList[currentIndex]?.option_two)}
        >
          {quizList[currentIndex]?.option_two}
        </Option>
      </Options>

      {isRightModalVisible && (
        <SpellingRight
          answer={quizList[currentIndex]?.answer}
          onClose={handleCloseModal}
        />
      )}
      {isWrongModalVisible && (
        <SpellingWrong
          answer={quizList[currentIndex]?.answer}
          onClose={handleCloseModal}
        />
      )}
    </Container>
  );
};

export default SpellingProblem;
