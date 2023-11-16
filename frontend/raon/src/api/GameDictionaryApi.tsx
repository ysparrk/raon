import { AxiosResponse } from 'axios';
import { api } from './Api';

const getQuiz = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/dictionary/quiz`);
    return response;
  } catch (error) {
    console.log('문제 받아오기 실패', error);
    throw error;
  }
};
const postSingleResult = async (score: number): Promise<AxiosResponse> => {
  try {
    const response = await api.post(`api/dictionary/single-result`, { score });
    return response;
  } catch (error) {
    console.log('점수 저장 실패', error);
    throw error;
  }
};

export { getQuiz, postSingleResult };
