import { AxiosResponse } from 'axios';
import { api } from './Api';

const getSpellingList = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/grammar/quiz`);
    return response;
  } catch (error) {
    console.log('맞춤법 문제 받아오기 실패', error);
    throw error;
  }
};

export { getSpellingList };
