import React, { useEffect } from 'react';
import styled, { keyframes } from 'styled-components';
import { useRecoilValue } from 'recoil';
import { roomManageState } from '../../../recoil/Atoms';

interface PodiumProps {
  firstUser?: User;
  secondUser?: User;
  thirdUser?: User;
}
interface User {
  profileImgUrl: string;
  nickname: string;
  schoolName: string;
}
const PodiumContainer = styled.div`
  display: flex;
  justify-content: space-around;
  margin-top: 1.25rem;
`;

const PodiumBase = styled.div`
  width: 7.5rem;
  height: 11.25rem;
  display: flex;
  align-items: flex-end;
`;
interface UserDivProps {
  userImage: string;
}
const FirstPlace = styled.div`
  background-color: gold;
  width: 100%;
  height: 100%;
  text-align: center;
  padding: 0.625rem;
`;
const CharaterfloatAnimation = keyframes`
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-0.625rem);
  }
`;
const UserDiv = styled.div<UserDivProps>`
  position: relative;
  top: -14.375rem;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  padding: 0.125rem;
  background-image: url(${(props) => props.userImage});
  background-size: cover;
  width: 6.25rem;
  height: 6.25rem;
  border-radius: 14px;
  background-color: lightgray;
  border: 0.125rem solid darkgray;
  animation: ${CharaterfloatAnimation} 1s infinite alternate;
`;
const UserNameText = styled.div`
  text-align: center;
  font-size: 1rem;
  font-family: 'ONE-Mobile-POP';
  color: ivory;
  text-shadow:
    -0.0938rem -0.0938rem 0 #000,
    0.0938rem -0.0938rem 0 #000,
    -0.0938rem 0.0938rem 0 #000,
    0.0938rem 0.0938rem 0 #000;
`;
const UserSchoolDiv = styled.div`
  position: absolute;
  display: flex;
  margin-bottom: -1.25rem;
  justify-content: center;
  text-align: center;
  align-items: center;
  flex-direction: column;
  font-size: 0.85rem;
  text-align: center;
  font-family: 'CookieRun';
  color: black;
  width: 120%;
`;

const SecondPlace = styled.div`
  background-color: silver;
  width: 100%;
  height: 65%;
  text-align: center;
  padding: 0.625rem;
`;

const ThirdPlace = styled.div`
  background-color: #cd7f32;
  width: 100%;
  height: 45%;
  text-align: center;
  padding: 0.625rem;
`;

const Podium = ({ firstUser, secondUser, thirdUser }: PodiumProps) => {
  const Room = useRecoilValue(roomManageState);
  return (
    <PodiumContainer>
      <PodiumBase>
        <SecondPlace>
          2
          {secondUser?.profileImgUrl && (
            <UserDiv userImage={secondUser.profileImgUrl}>
              <UserNameText>{secondUser.nickname}</UserNameText>
              <UserSchoolDiv>{secondUser.schoolName}</UserSchoolDiv>
            </UserDiv>
          )}
        </SecondPlace>
      </PodiumBase>
      <PodiumBase>
        <FirstPlace>
          1
          {firstUser?.profileImgUrl && (
            <UserDiv userImage={firstUser.profileImgUrl}>
              <UserNameText>{firstUser.nickname}</UserNameText>
              <UserSchoolDiv>{firstUser.schoolName}</UserSchoolDiv>
            </UserDiv>
          )}
        </FirstPlace>
      </PodiumBase>
      <PodiumBase>
        <ThirdPlace>
          3
          {thirdUser?.profileImgUrl && (
            <UserDiv userImage={thirdUser.profileImgUrl}>
              <UserNameText>{thirdUser.nickname}</UserNameText>
              <UserSchoolDiv>{thirdUser.schoolName}</UserSchoolDiv>
            </UserDiv>
          )}
        </ThirdPlace>
      </PodiumBase>
    </PodiumContainer>
  );
};

export default Podium;

Podium.defaultProps = {
  firstUser: undefined,
  secondUser: undefined,
  thirdUser: undefined,
};
