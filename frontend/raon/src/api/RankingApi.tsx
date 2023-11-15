import { AxiosResponse } from 'axios';
import { api } from './Api';

const getPersonalSpelling = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/grammar/ranking/country-my`);
    return response;
  } catch (error) {
    console.log('맞춤법 전국 개인 확인 실패', error);
    throw error;
  }
};

const getClassSpelling = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/grammar/ranking/school-my`);
    return response;
  } catch (error) {
    console.log('맞춤법 교내 랭킹 확인 실패', error);
    throw error;
  }
};

const getSchoolSpelling = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/grammar/ranking/school`);
    return response;
  } catch (error) {
    console.log('맞춤법 학교 랭킹 확인 실패', error);
    throw error;
  }
};

const getPersonalDictionary = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/dictionary/ranking/country-my`);
    return response;
  } catch (error) {
    console.log('국어사전 전국 개인 확인 실패', error);
    throw error;
  }
};

const getClassDictionary = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/dictionary/ranking/school-my`);
    return response;
  } catch (error) {
    console.log('국어사전 교내 랭킹 확인 실패', error);
    throw error;
  }
};

const getSchoolDictionary = async (): Promise<AxiosResponse> => {
  try {
    const response = await api.get(`api/dictionary/ranking/school`);
    return response;
  } catch (error) {
    console.log('국어사전 학교 랭킹 확인 실패', error);
    throw error;
  }
};

export {
  getPersonalSpelling,
  getClassSpelling,
  getSchoolSpelling,
  getPersonalDictionary,
  getClassDictionary,
  getSchoolDictionary,
};
