import styled from 'styled-components';

const BlurBox = styled.div`
  width: 100vw;
  height: 100vh;
  top: 0;
  left: 0;
  backdrop-filter: blur(0.175rem);
  background-color: rgba(255, 255, 255, 0.5);
  position: absolute;
  z-index: 1;
`;

const BlurBoxDiv = () => {
  return <BlurBox />;
};
export default BlurBoxDiv;
