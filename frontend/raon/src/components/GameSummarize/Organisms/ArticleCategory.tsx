import React from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import SelectOption from '../../Common/Atoms/SelectOption';
import social from '../../../assets/Images/social.png';
import science from '../../../assets/Images/science.png';
import world from '../../../assets/Images/world.png';
import life from '../../../assets/Images/life.png';
import ExitButton from '../../Common/Atoms/ExitButton';

const CategoryContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  max-width: 90%;
  margin: 0 auto;
  height: 50vh; // 뷰포트의 높이에 따라 컨테이너의 높이를 설정
`;
const ExitDiv = styled.div`
  position: absolute;
  bottom: 5%;
  right: 5%;
`;

const ArticleCategory = () => {
  const navigate = useNavigate();

  const handleClick = (category: string) => {
    console.log(category);
    navigate('/game/summarize-quiz');
  };
  return (
    <div>
      <CategoryContainer>
        <SelectOption
          imgSrc={social}
          optionText="사회"
          onClick={() => handleClick('social')}
        />
        <SelectOption
          imgSrc={life}
          optionText="생활"
          onClick={() => handleClick('life')}
        />
        <SelectOption
          imgSrc={science}
          optionText="과학"
          onClick={() => handleClick('science')}
        />
        <SelectOption
          imgSrc={world}
          optionText="세계"
          onClick={() => handleClick('world')}
        />
      </CategoryContainer>
      <ExitDiv>
        <ExitButton to="/main/" />
      </ExitDiv>
    </div>
  );
};

export default ArticleCategory;
