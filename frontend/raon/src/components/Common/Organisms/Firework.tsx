import React, { useState, useEffect } from 'react';
import JSConfetti from 'js-confetti';
import styled from 'styled-components';

const Firework = () => {
  const [showFirework, setShowFirework] = useState(true); // 폭죽 효과가 보이는지 여부
  const jsConfetti = new JSConfetti();

  const FireworkContainer = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    z-index: 10000; // ModalOverlay보다 높은 z-index 설정
    pointer-events: none;
  `;

  useEffect(() => {
    jsConfetti.addConfetti({
      confettiColors: [
        '#ff0a54',
        '#ff477e',
        '#ff7096',
        '#ff85a1',
        '#fbb1bd',
        '#f9bec7',
      ],
      confettiRadius: 5,
      confettiNumber: 1000,
    });

    // 일정 시간(예: 5초) 후에 폭죽 효과가 사라지게 하기
    const timer = setTimeout(() => {
      setShowFirework(false);
    }, 2000);

    // 컴포넌트 언마운트 시 타이머 정리
    return () => clearTimeout(timer);
  }, []);

  return showFirework ? <FireworkContainer /> : null;
};

export default Firework;
