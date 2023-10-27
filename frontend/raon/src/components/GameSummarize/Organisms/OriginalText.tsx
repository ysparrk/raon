import React from 'react';
import styled from 'styled-components';

const TextDiv = styled.div`
  width: 56.25rem;
  height: 37rem;
  font-family: 'CookieRun';
  font-size: 1.875rem;
  color: white;
  margin: 1.25rem;
`;

const OriginalText = () => {
  const content =
    '지난주 미국 유에스 뉴스 앤드 월드 리포트지에 흥미롭지만 우리에게는 결코 흥미로울 수만은 없는 기사가 실렸다. 전 세계 나라를 분야별로 조사해서 등급을 매긴, 말하자면 세계 여론조사였다. 한국은 전체적으로 ‘가장 좋은 나라(Best country)’ 부문에서 21위를 차지했는데 ‘강력한 국가(Strong country)’ 부문에서는 6위에 올랐다. 한마디로 ‘힘은 센 나라인데 삶의 질(質)은 힘에 비해 떨어진다’는 해석이 가능하다. 외교·국방·경제 등에서는 일본을 제칠 정도이지만 사회적 목적(40위), 모험성(54위), 사업 개방도(74위) 등에서 크게 떨어져 전체 순위를 끌어내린 것이라고 기사는 설명하고 있다. 그런 항목은 없었지만 여기에 ‘국내 정치’를 넣었다면 우리는 분명 하위로 크게 추락했을 것이다. 그런데 이 기사의 댓글 가운데 눈길을 끄는 것이 있다. “전 세계 유례없는 저출산율로 소멸 직전의 나라인데 21위라니 너무 높다”는 비꼼조의 촌평이었다. 소름이 돋는 지적이다. 우리나라는 저출산과 고령화로 서서히 가라앉는 배와 같다는 느낌이 무겁게 다가왔다.';
  return <TextDiv>{content}</TextDiv>;
};

export default OriginalText;
