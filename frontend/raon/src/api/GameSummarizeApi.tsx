import { AxiosResponse } from 'axios';
import { api } from './Api';

const getNews = async (category: string): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/summaries/${category}`);
    return response;
  } catch (error) {
    console.log('뉴스 받아오기 실패', error);
    throw error;
  }
};

export { getNews };
