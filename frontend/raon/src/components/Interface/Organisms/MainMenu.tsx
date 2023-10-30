import React from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import SelectOption from '../../Common/Atoms/SelectOption';
import main1 from '../../../assets/Images/main1.png';
import main2 from '../../../assets/Images/main2.png';
import main3 from '../../../assets/Images/main3.png';
import main4 from '../../../assets/Images/main4.png';
import ProfileButton from '../Atoms/ProfileButton';

const MenuContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  max-width: 90%;
  margin: 0 auto;
  height: 90vh; // 뷰포트의 높이에 따라 컨테이너의 높이를 설정
`;

const MainMenu = () => {
  const navigate = useNavigate();

  return (
    <div>
      <MenuContainer>
        <SelectOption
          imgSrc={main1}
          optionText="맞춤법 놀이"
          onClick={() => navigate('/game/spelling-quiz')}
        />
        <SelectOption
          imgSrc={main2}
          optionText="글 요약 놀이"
          onClick={() => navigate('/game/summarize-category')}
        />
        <SelectOption
          imgSrc={main3}
          optionText="국어사전 놀이"
          //   onClick={() => handleOptionClick('Option 3')}
        />
        <SelectOption
          imgSrc={main4}
          optionText="순위"
          //   onClick={() => handleOptionClick('Option 4')}
        />
      </MenuContainer>
      <ProfileButton />
    </div>
  );
};

export default MainMenu;
