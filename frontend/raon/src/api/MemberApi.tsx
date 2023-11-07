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

export { getMemberActive, postMemberSignup };
