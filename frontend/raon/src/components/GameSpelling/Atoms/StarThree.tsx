import React from 'react';
import styled from 'styled-components';
import greenStar from '../../../assets/Images/greenStar.png';

const StarContainer = styled.div`
  display: flex;
  align-items: center;
`;

const StarImage = styled.img`
  margin: 0 5px;
`;

const StarThree = () => {
  return (
    <StarContainer>
      <StarImage src={greenStar} alt="Green Star" />
      <StarImage src={greenStar} alt="Green Star" />
      <StarImage src={greenStar} alt="Green Star" />
    </StarContainer>
  );
};

export default StarThree;
