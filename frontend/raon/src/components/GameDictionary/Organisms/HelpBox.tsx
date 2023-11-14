import React, { useState } from 'react';
import styled from 'styled-components';
import helpDictionaryImage1 from '../../../assets/Images/helpDictionaryImage1.png';
import helpDictionaryImage2 from '../../../assets/Images/helpDictionaryImage2.png';
import helpDictionaryImage3 from '../../../assets/Images/helpDictionaryImage3.png';
import helpDictionaryImage4 from '../../../assets/Images/helpDictionaryImage4.png';
import helpDictionaryImage5 from '../../../assets/Images/helpDictionaryImage5.png';
import helpDictionaryImage6 from '../../../assets/Images/helpDictionaryImage6.png';
import { ReactComponent as Arrow } from '../../../assets/Images/arrow.svg';
import BlurView from '../../GameSummarize/Atoms/BlurView';


const TopBox: React.FC<{
  imageIndex: number;
  handleLeftArrowClick: () => void;
  handleRightArrowClick: () => void;
}> = ({ imageIndex, handleLeftArrowClick, handleRightArrowClick }) => {
  const images = [
    helpDictionaryImage1,
    helpDictionaryImage2,
    helpDictionaryImage3,
    helpDictionaryImage4,
    helpDictionaryImage5,
    helpDictionaryImage6,
  ];

  return (
    <>
      <BlurView />
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
    </>
  );
};

const HelpBox: React.FC = () => {
  const [index, setIndex] = useState(0);

  const comments = [
    '1. 국어사전 놀이에는 두 종류의 문제가 나옵니다.',
    '2. 초성퀴즈 문제는 설명과 초성을 보고 정답을 맞춰주세요!',
    '3. 동서남북 퀴즈는 ?에 들어갈 한 글자를 맞춰야 해요.',
    '4. 휴?, 개?, ?교, ?생 4개의 단어에 공통으로 들어갈 ?를 맞춰주세요.',
    '5. 총 10문제가 나옵니다. 도중에 문제를 그만 풀고 싶다면 나가기를 눌러주세요.',
    '6. 10문제를 모두 맞추고 나면 본인의 점수를 확인할 수 있어요.',
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
