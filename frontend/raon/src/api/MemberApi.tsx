import { AxiosResponse } from 'axios';
import { api } from './Api';

interface MemberSignup {
  nickname: string;
  school: string;
  yearOfBirth: number;
  gender: string;
}

const getMemberActive = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/members/active`);
    return response;
  } catch (error) {
    console.log('사용자 개인정보가 입력되어 있는지 확인 실패', error);
    throw error;
  }
};

const postMemberSignup = async (data: MemberSignup): Promise<AxiosResponse> => {
  try {
    console.log(data);
    const response = await api.post(`api/members/signup`, data);
    return response;
  } catch (error) {
    console.log('사용자 정보 입력 실패', error);
    throw error;
  }
};

const postDuplicateCheck = async (nickname: string): Promise<AxiosResponse> => {
  try {
    const response = await api.post(`api/members/check/nickname`, nickname);
    return response;
  } catch (error) {
    console.log('중복확인 검사 실패', error);
    throw error;
  }
};

const postSchoolsList = async (keyword: string): Promise<AxiosResponse> => {
  try {
    const response = await api.post(`api/members/check-school`, {
      keyword,
    });
    return response.data;
  } catch (error) {
    console.log('학교조회 실패', error);
    throw error;
  }
};

export {
  getMemberActive,
  postMemberSignup,
  postDuplicateCheck,
  postSchoolsList,
};
