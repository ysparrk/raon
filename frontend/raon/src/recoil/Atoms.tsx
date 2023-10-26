import { atom } from 'recoil';

export const submitState = atom<string[]>({
  key: 'submitState',
  default: [],
});

export const answerState = atom<string[]>({
  key: 'answerState',
  default: [],
});
