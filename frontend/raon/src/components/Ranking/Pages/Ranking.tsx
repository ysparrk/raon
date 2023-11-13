import React from 'react';
import styled from 'styled-components';
import RankingBody from '../Organisms/RankingBody';
import TitleBox from '../../Common/Atoms/TitleBox';

const Ranking = () => {
  return (
    <div>
      <TitleBox>순위 확인</TitleBox>
      <RankingBody />
    </div>
  );
};

export default Ranking;
