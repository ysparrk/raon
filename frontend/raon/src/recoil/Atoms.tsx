import { atom } from 'recoil';

export const submitState = atom<string[]>({
  key: 'submitState',
  default: [],
});

export const answerState = atom<string[]>({
  key: 'answerState',
  default: [],
});

export const summarizeState = atom({
  key: 'summarizeState',
  default: {
    topic: '',
    title: '',
    content: '',
    summarize_content: '',
  },
});

export const dictScoreState = atom<number>({
  key: 'dictScoreState',
  default: 0,
});
