import { AxiosResponse } from 'axios';
import { api } from './Api';

const postRoomId = async (
  nickname: string,
  roomId: string,
): Promise<AxiosResponse> => {
  try {
    console.log('post 요청');
    console.log(nickname, roomId);
    const payload = { nickname, roomId }; // Object directly, no need to stringify
    const response = await api.post('api/dictionary-quiz/check-room', {
      nickname,
      roomId,
    });
    return response;
  } catch (error) {
    console.log('방 id 확인 실패', error);
    throw error;
  }
};

export { postRoomId };
