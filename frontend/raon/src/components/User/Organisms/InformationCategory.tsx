import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { css } from 'styled-components';
import Swal from 'sweetalert2';
import InputBox from '../../Common/Atoms/InputBox.tsx';
import ComboBox from '../../Common/Atoms/ComboBox.tsx';
import DuplicationCheckButton from '../Atoms/DuplicateCheckButton.tsx';
import {
  postMemberSignup,
  postDuplicateCheck,
  postSchoolsList,
} from '../../../api/MemberApi.tsx';
import BlurBoxDiv from '../../Common/Atoms/BlurBackGround.tsx';
import SmallButton from '../../Common/Atoms/SmallButton.tsx';
import SchoolInputBox from '../Atoms/SchoolSearchInputBox.tsx';

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

const SchoolInput = styled.input`
  height: 3.125rem;
  width: 31.25rem;
  box-sizing: border-box;
  padding: 0.625rem;
  border-radius: 1rem;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
`;
const SchoolDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  position: fixed;
  top: 50%;
  left: 50%;
  padding: 1rem;
  width: 38.4625rem;
  height: 40rem;
  transform: translate(-50%, -50%);
  z-index: 2;
  background-color: #ffcd4a;
  font-family: 'ONE-Mobile-POP';
  border-radius: 0.9375rem;
  border: 0.25rem solid black;
  gap: 0.475rem;
`;

const SchoolTitleDiv = styled.div`
  display: flex;
  justify-content: flex-start;
  text-align: start;
  color: black;
  width: 94%;
  font-size: 5rem;
  margin: 0.3125rem;
`;

const SchoolInputDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
`;

const InputButtonDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  background-color: lightsteelblue;
  border: 0.125rem solid darkgray;
  color: white;
  width: 6rem;
  height: 3rem;
  border-radius: 13px;
`;

const SchoolButtonDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 1.875rem;
`;
interface SchoolListDivProps {
  showPadding: boolean;
}
const SchoolListDiv = styled.div<SchoolListDivProps>`
  display: flex;
  flex-direction: column;
  /* justify-content: flex-start; */
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  border-radius: 8px;
  background-color: ivory;
  border: 0.1125rem solid black;
  margin-top: 0.75rem;
  width: 94%;
  height: 60%;
  gap: 0.3125rem;
  overflow-y: auto;
  ${(props) =>
    props.showPadding &&
    css`
      padding-top: 60%;
    `}
`;

const SchoolElemDiv = styled.div`
  display: flex;
  justify-content: space-between;
  text-align: center;
  width: 60%;
  font-family: 'NanumBarunGothic';
  color: black;
`;

const SchoolRegionSpan = styled.span`
  color: gray;
  font-size: 1.125rem;
`;

const Selection = styled.div`
  display: flex;
  justify-content: space-between;
  text-align: center;
  width: 100%;
  padding: 0.25rem;
  border: 0.3125rem solid #7f15f7;
  border-radius: 0.9375rem;
`;

const InformationCategory = () => {
  const navigate = useNavigate();

  const [nickname, setNickname] = useState('');
  const [birthday, setBirthday] = useState('');
  const [gender, setGender] = useState('남자');
  const [school, setSchool] = useState('');
  const [nicknameCheck, setNicknameCheck] = useState(false);
  const [isSearch, setIsSearch] = useState(false);
  const [searchWord, setSearchWord] = useState('');
  const [searchSchools, setSearchSchools] = useState<any[]>([]);
  const genderOptions = ['남자', '여자'];

  const handleSubmit = async () => {
    if (!nicknameCheck) {
      Swal.fire({
        title: '닉네임 중복 검사를 진행해주세요',
        text: '확인이 필요합니다!',
        icon: 'warning',
      });
      return;
    }
    if (!nickname || !birthday || !school) {
      Swal.fire({
        title: '입력되지 않은 값이 있어요',
        text: '모든 항목을 입력해주세요!',
        icon: 'warning',
      });
      return;
    }
    const convertGender = (koreanGender: string): string => {
      const genderMapping: Record<string, string> = {
        남자: 'MALE',
        여자: 'FEMALE',
      };

      return genderMapping[koreanGender] || koreanGender;
    };

    const memberData = {
      nickname,
      school,
      yearOfBirth: Number(birthday),
      gender: convertGender(gender),
    };

    console.log(memberData);
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

  const handleYearCheck = (year: string) => {
    // 숫자만 입력받고, 그 숫자가 4자리인지 확인
    const regExp = /^[0-9]{4}$/;
  
    if (!regExp.test(year)) {
      Swal.fire({
        title: '올바르지 않은 입력입니다.',
        text: '출생년도는 숫자 4자리로 입력해주세요!',
        icon: 'warning',
      });
      setBirthday("");  // 입력값 초기화
    }
  };

  const handleNicknameCheck = (nickname: string) => {
    const regExp = /^[가-힣]{2,12}$/;
  
    if (!regExp.test(nickname)) {
      Swal.fire({
        title: '올바르지 않은 입력입니다.',
        text: '닉네임은 2글자 이상, 12글자 이하의 한글로 입력해주세요!',
        icon: 'warning',
      });
      setBirthday("");  // 입력값 초기화
    }
  };


  const handleSchool = async () => {
    try {
      const response = await postSchoolsList(searchWord);
      const newSchools = response.data.schoolNameList;
      setSearchSchools(newSchools);
    } catch (error) {
      alert('해당하는 학교가 없습니다');
    }
  };

  return (
    <Container>
      <Content>
        {isSearch && (
          <>
            <BlurBoxDiv />
            <SchoolDiv>
              <SchoolTitleDiv>학교 검색</SchoolTitleDiv>
              <SchoolInputDiv>
                <SchoolInputBox
                  inputText={searchWord}
                  onChange={(e) => setSearchWord(e.target.value)}
                  onEnter={() => {
                    if (searchWord && searchWord.length >= 2) {
                      handleSchool();
                    } else {
                      alert('이름을 2글자 이상 입력해주세요');
                    }
                  }}
                />
                <InputButtonDiv
                  onClick={() => {
                    if (searchWord && searchWord.length >= 2) {
                      handleSchool();
                    } else {
                      alert('이름을 2글자 이상 입력해주세요');
                    }
                  }}
                >
                  검색
                </InputButtonDiv>
              </SchoolInputDiv>
              <SchoolListDiv showPadding={searchSchools.length >= 19}>
                {searchSchools.length > 0 &&
                  searchSchools.map((schoolElem, index) => (
                    <SchoolElemDiv
                      key={schoolElem.id}
                      onClick={() => {
                        setSchool(schoolElem.schoolName);
                      }}
                    >
                      {school === schoolElem.schoolName ? (
                        <Selection>
                          {schoolElem.schoolName}
                          <SchoolRegionSpan>
                            {schoolElem.location}
                          </SchoolRegionSpan>
                        </Selection>
                      ) : (
                        <>
                          {schoolElem.schoolName}
                          <SchoolRegionSpan>
                            {schoolElem.location}
                          </SchoolRegionSpan>
                        </>
                      )}
                    </SchoolElemDiv>
                  ))}
              </SchoolListDiv>

              <SchoolButtonDiv>
                <SmallButton
                  content="확인하기"
                  fontColor="skyblue"
                  onClick={() => {
                    setIsSearch(false);
                    setSearchSchools([]);
                    setSearchWord('');
                  }}
                />
                <SmallButton
                  content="취소하기"
                  fontColor="lightsalmon"
                  onClick={() => {
                    setIsSearch(false);
                    setSearchSchools([]);
                    setSchool('');
                    setSearchWord('');
                  }}
                />
              </SchoolButtonDiv>
            </SchoolDiv>
          </>
        )}
        <Label>닉네임 :</Label>
        <InputBox
          inputText={nickname}
          onChange={(e) => setNickname(e.target.value)}
          placeHolder='2글자 이상, 12글자 이하의 한글을 입력해주세요!'
          onBlur={() => handleNicknameCheck(nickname)}
        />
        <DuplicationCheckButton onClick={handleDuplicateCheck} />
      </Content>
      <Content>
        <Label>출생년도 :</Label>
        <InputBox
            inputText={birthday}
            onChange={(e) => setBirthday(e.target.value)}
            onBlur={() => handleYearCheck(birthday)}
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
        <SchoolInput
          value={school}
          placeholder="다니고 있는 학교를 입력해주세요!"
          onClick={() => {
            setIsSearch(true);
          }}
        />
      </Content>
      <Button onClick={handleSubmit}>제 출 하 기</Button>
    </Container>
  );
};

export default InformationCategory;
