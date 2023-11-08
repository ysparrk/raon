import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import InputBox from '../../Common/Atoms/InputBox.tsx';
import ComboBox from '../../Common/Atoms/ComboBox.tsx';
import { postMemberSignup } from '../../../api/MemberApi.tsx';

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
  const [gender, setGender] = useState('MALE');
  const [school, setSchool] = useState('');

  const genderOptions = ['MALE', 'FEMALE'];

  const handleSubmit = async () => {
    const memberData = {
      nickname,
      school,
      yearOfBirth: Number(birthday),
      gender,
    };
    try {
      const response = await postMemberSignup(memberData);
      console.log('회원가입 성공:', response);
      localStorage.setItem('nickname', nickname);
      navigate('/main');
    } catch (error) {
      console.error('회원가입 실패:', error);
    }
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
        <ComboBox
          selectedOption={gender}
          onChange={(e) => setGender(e.target.value)}
          options={genderOptions}
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
