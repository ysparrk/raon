import React from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import cry from '../../../assets/Images/cry.png';
import Tear from '../../Common/Organisms/Tear.tsx';
import StarOne from '../Atoms/StarOne.tsx';
// import StarTwo from '../Atoms/StarTwo.tsx';
// import StarThree from '../Atoms/StarThree.tsx';

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
`;

const ModalContainer = styled.div`
  background-color: #ffffff;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  width: 440px;
  height: 680px;
  border-radius: 8px;
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
`;

const TopSection = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: 'CookieRun';
  font-size: 48px;
  width: 100%;
`;

const CryImage = styled.img`
  max-height: 300px; /* You can adjust based on your requirements */
`;

const ButtonGroup = styled.div`
  display: flex;
  justify-content: center;
  gap: 20px; // 간격을 주기 위해 추가
`;

const Button = styled.button`
  margin: 20px;
  padding: 20px 30px;
  border: none;
  border-radius: 80px;
  background-color: #ffcd4a;
  font-size: 20px;
  font-family: 'CookieRun';
  color: white;
  cursor: pointer;

  &:hover {
    background-color: #ffcd4a;
  }
`;

type Props = {
  onClose: () => void;
  answer: string;
};

const SpellingWrong: React.FC<Props> = ({ onClose, answer }) => {
  const navigate = useNavigate();

  return (
    <React.Fragment>
      {' '}
      <Tear />
      <ModalOverlay>
        <ModalContainer>
          <TopSection>
            <div>틀렸어요...</div>
            <CryImage src={cry} alt="Cry" />
            <div>난이도</div>
            <StarOne />
          </TopSection>
          <ButtonGroup>
            <Button onClick={onClose}>다음 문제</Button>
            <Button onClick={() => navigate('/main')}>나가기</Button>
          </ButtonGroup>
        </ModalContainer>
      </ModalOverlay>
    </React.Fragment>
  );
};

export default SpellingWrong;
