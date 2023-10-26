import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { submitState, answerState } from '../../../recoil/Atoms.tsx';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
`;

const Content = styled.div`
  font-family: 'CookieRun';
  font-size: 60px;
  color: white;
  margin-bottom: 100px;
`;

const Options = styled.div`
  display: flex;
  gap: 400px;
`;

const Option = styled.div`
  font-family: 'ONE-Mobile-POP';
  color: white;
  font-size: 48px;
  cursor: pointer;
  transition: background-color 0.2s;

  &:hover {
    background-color: rgba(0, 0, 0, 0.05);
  }
`;

const quiz_list = [
  {
    content: '문제1',
    option_one: '1',
    option_two: '2',
    answer: '1',
  },
  {
    content: '문제2',
    option_one: '감',
    option_two: '검',
    answer: '감',
  },
  {
    content: '문제3',
    option_one: '하이',
    option_two: '바이',
    answer: '바이',
  },
  {
    content: '문제4',
    option_one: '그레이',
    option_two: '그레용',
    answer: '그레이',
  },
  {
    content: '문제5',
    option_one: '타이타닉',
    option_two: '타요타요',
    answer: '타이타닉',
  },
  {
    content: '문제6',
    option_one: '트럼프',
    option_two: '푸틴',
    answer: '푸틴',
  },
  {
    content: '문제7',
    option_one: '버스커버스커',
    option_two: '버서커버서커',
    answer: '버스커버스커',
  },
  {
    content: '문제8',
    option_one: '퍼레요',
    option_two: '퍼래요',
    answer: '퍼레요',
  },
  {
    content: '문제9',
    option_one: '커피',
    option_two: '코피',
    answer: '커피',
  },
  {
    content: '문제10',
    option_one: '사랑',
    option_two: '사람',
    answer: '사랑',
  },
];

const SpellingProblem = () => {
  const navigate = useNavigate();

  const [currentIndex, setCurrentIndex] = useState(0);
  const setSubmitList = useSetRecoilState(submitState);
  const setAnswerList = useSetRecoilState(answerState);

  const handleOptionClick = (option: string) => {
    setSubmitList((prevList: string[]) => [...prevList, option]);

    if (currentIndex < quiz_list.length - 1) {
      setCurrentIndex(currentIndex + 1);
    }

    if (currentIndex === quiz_list.length - 1) {
      navigate('/game/spelling-result');
    }
  };

  useEffect(() => {
    const correctAnswers = quiz_list.map((quiz) => quiz.answer);
    setAnswerList(correctAnswers);
    setSubmitList([]);
  }, []);

  return (
    <Container>
      <Content>{quiz_list[currentIndex].content}</Content>
      <Options>
        <Option
          onClick={() => handleOptionClick(quiz_list[currentIndex].option_one)}
        >
          {quiz_list[currentIndex].option_one}
        </Option>
        <Option
          onClick={() => handleOptionClick(quiz_list[currentIndex].option_two)}
        >
          {quiz_list[currentIndex].option_two}
        </Option>
      </Options>
    </Container>
  );
};

export default SpellingProblem;
