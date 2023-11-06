import React from 'react';
import styled from 'styled-components';
import greyStar from '../../../assets/Images/greyStar.png';
import greenStar from '../../../assets/Images/greenStar.png';

const StarContainer = styled.div`
  display: flex;
  align-items: center;
`;

const StarImage = styled.img`
  margin: 0 0.3125rem;
`;

const StarTwo = () => {
  return (
    <StarContainer>
      <StarImage src={greenStar} alt="Green Star" />
      <StarImage src={greenStar} alt="Green Star" />
      <StarImage src={greyStar} alt="Grey Star" />
    </StarContainer>
  );
};

export default StarTwo;
