import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import InputBox from '../../Common/Atoms/InputBox.tsx';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Content = styled.div`
  display: flex;
  align-items: center;
  margin-top: 40px;
  font-family: 'CookieRun';
  font-size: 40px;
  color: #ffffff;
`;

const Button = styled.button`
  margin: 20px;
  padding: 20px 80px;
  border: none;
  border-radius: 20px;
  background-color: #fae100;
  margin-top: 80px;
  font-size: 32px;
  font-family: 'CookieRun';
  color: white;
  cursor: pointer;

  &:hover {
    background-color: #ffcd4a;
  }
`;

const Label = styled.span`
  white-space: nowrap;
  margin-right: 20px;
`;

const InformationCategory = () => {
  const navigate = useNavigate();

  const [nickname, setNickname] = useState('');
  const [birthday, setBirthday] = useState('');
  const [gender, setGender] = useState('');
  const [school, setSchool] = useState('');

  // API 완성되면 console로 띄우는게 아니라 Post로 보내줄 것.
  const handleSubmit = () => {
    console.log('닉네임:', nickname);
    console.log('생일:', birthday);
    console.log('성별:', gender);
    console.log('학교:', school);
    navigate('/main');
  };

  return (
    <Container>
      <Content>
        <Label>닉네임 :</Label>
        <InputBox
          inputText={nickname}
          onChange={(e) => setNickname(e.target.value)}
        />
      </Content>
      <Content>
        <Label>출생년도 :</Label>
        <InputBox
          inputText={birthday}
          onChange={(e) => setBirthday(e.target.value)}
        />
      </Content>
      <Content>
        <Label>성별 :</Label>
        <InputBox
          inputText={gender}
          onChange={(e) => setGender(e.target.value)}
        />
      </Content>
      <Content>
        <Label>학교 :</Label>
        <InputBox
          inputText={school}
          onChange={(e) => setSchool(e.target.value)}
        />
      </Content>
      <Button onClick={handleSubmit}>제 출 하 기</Button>
    </Container>
  );
};

export default InformationCategory;
