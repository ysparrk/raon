import React, { useState } from 'react';
import helpSpellingImage1 from '../../../assets/Images/helpSpellingImage1.png';
import helpSpellingImage2 from '../../../assets/Images/helpSpellingImage2.png';
import helpSpellingImage3 from '../../../assets/Images/helpSpellingImage3.png';
import helpSpellingImage4 from '../../../assets/Images/helpSpellingImage4.png';
import { ReactComponent as Arrow } from '../../../assets/Images/arrow.svg';

const TopBox: React.FC<{
  imageIndex: number;
  handleLeftArrowClick: () => void;
  handleRightArrowClick: () => void;
}> = ({ imageIndex, handleLeftArrowClick, handleRightArrowClick }) => {
  const images = [
    helpSpellingImage1,
    helpSpellingImage2,
    helpSpellingImage3,
    helpSpellingImage4,
  ];

  return (
    <div
      style={{
        backgroundColor: '#F9E8B6',
        position: 'fixed',
        top: 0,
        left: '5%',
        width: '90%',
        height: '78%',
        zIndex: 7,
        border: '5px solid black',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        fontSize: '24px',
      }}
    >
      <Arrow onClick={handleLeftArrowClick} />
      <img
        src={images[imageIndex]}
        alt="Spelling Help"
        style={{
          maxWidth: '80%',
          maxHeight: '80%',
          objectFit: 'contain',
        }}
      />
      <Arrow
        onClick={handleRightArrowClick}
        style={{ transform: 'rotate(180deg)' }}
      />
    </div>
  );
};

const HelpBox: React.FC = () => {
  const [index, setIndex] = useState(0);

  const comments = [
    '1. 시작하기 버튼을 누르면 화면에 1문제씩 나옵니다. 질문에 맞는 답을 골라주세요',
    '2. 총 10문제가 나옵니다. 도중에 문제를 그만 풀고 싶다면 나가기를 눌러주세요',
    '3. 문제를 풀면 난이도가 나옵니다. 별이 많을수록 많은 사람이 틀린 어려운 문제에요',
    '4. 10문제를 풀고나면 결과창이 나옵니다. 본인의 점수를 확인해보세요!',
  ];

  const handleRightArrowClick = () => {
    if (index < comments.length - 1) {
      setIndex(index + 1);
    }
  };

  const handleLeftArrowClick = () => {
    if (index > 0) {
      setIndex(index - 1);
    }
  };

  return (
    <>
      <TopBox
        imageIndex={index}
        handleLeftArrowClick={handleLeftArrowClick}
        handleRightArrowClick={handleRightArrowClick}
      />
      <div
        style={{
          backgroundColor: '#6F5F46',
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'bottom',
          position: 'fixed',
          bottom: 0,
          left: '5%',
          width: '90%',
          height: '22%',
          zIndex: 7,
          border: '5px solid black',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          fontSize: '28px',
          fontFamily: 'CookieRun',
          color: 'white',
        }}
      >
        {comments[index]}
      </div>
    </>
  );
};

export default HelpBox;
