import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import InputBox from '../../Common/Atoms/InputBox.tsx';
import ComboBox from '../../Common/Atoms/ComboBox.tsx';
import DuplicationCheckButton from '../Atoms/DuplicateCheckButton.tsx';
import {
  postMemberSignup,
  postDuplicateCheck,
} from '../../../api/MemberApi.tsx';
import Swal from 'sweetalert2';

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
  const [nicknameCheck, setNicknameCheck] = useState(false);

  const genderOptions = ['MALE', 'FEMALE'];

  const handleSubmit = async () => {
    if (!nicknameCheck) {
      Swal.fire({
        title: '사용하고 있는 닉네임이에요!',
        text: '다른 닉네임으로 중복 검사를 다시 진행해주세요!',
        icon: 'warning',
      });
      return;
    }
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

  const handleDuplicateCheck = async () => {
    try {
      const response = await postDuplicateCheck(nickname);
      if (response.data.message === '사용 가능한 닉네임 입니다.') {
        Swal.fire({
          title: '사용 가능한 닉네임입니다.',
          text: '이 닉네임으로 회원가입을 진행해보아요!',
        });
        setNicknameCheck(true);
      } else if (response.data.message === '중복된 닉네임 입니다.') {
        Swal.fire({
          title: '중복된 닉네임입니다.',
          html: '이 닉네임을 사용하는 다른 사람이 있어요!<br>다른 닉네임으로 회원가입을 진행해보아요!',
        });
        setNicknameCheck(false);
      }
    } catch (error) {
      console.error('중복 검사 실패:', error);
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
        <DuplicationCheckButton onClick={handleDuplicateCheck} />
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
