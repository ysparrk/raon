import { AxiosResponse } from 'axios';
import { api } from './Api';

interface GrammarResult {
  id: number;
  hit: number;
}

interface GrammarResultList {
  grammarResultList: GrammarResult[];
}

const getSpellingList = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/grammar/quiz`);
    return response;
  } catch (error) {
    console.log('맞춤법 문제 받아오기 실패', error);
    throw error;
  }
};

const postSpellingResult = async (
  data: GrammarResultList,
): Promise<AxiosResponse> => {
  try {
    const response = await api.post(`api/grammar/result`, data);
    return response;
  } catch (error) {
    console.log('맞춤법 정답 보내기 실패', error);
    throw error;
  }
};

export { getSpellingList, postSpellingResult };
