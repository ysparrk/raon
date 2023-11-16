import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import TitleBox from '../../Common/Atoms/TitleBox.tsx';
import MyInformationCategory from '../Organisms/MyInformationCategory.tsx';

const ImageContainer = styled.div`
  overflow-x: hidden;
  position: relative;
  height: 100vh;
  width: 100vw;
`;

const MyInformation = () => {
  return (
    <div>
      <ImageContainer>
        <TitleBox>내 정보</TitleBox>
        <MyInformationCategory />
      </ImageContainer>
    </div>
  );
};

export default MyInformation;
