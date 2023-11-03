import React, { useState, useEffect } from 'react';
import JSConfetti from 'js-confetti';
import styled from 'styled-components';

const Tear = () => {
  const [showTear, setShowTear] = useState(true);
  const jsConfetti = new JSConfetti();

  const TearContainer = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    z-index: 10000;
  `;

  useEffect(() => {
    jsConfetti.addConfetti({
      emojis: ['💧'],
      emojiSize: 100,
      confettiNumber: 300,
    });

    const timer = setTimeout(() => {
      setShowTear(false);
    }, 2000);

    return () => clearTimeout(timer);
  }, []);

  return showTear ? <TearContainer /> : null;
};

export default Tear;
