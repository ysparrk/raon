import './App.css';
import { RecoilRoot } from 'recoil';
import AppRouter from './route/AppRouter';
import { GlobalStyle } from './style/GlobalStyle';
import BackgroundImage from './components/Common/Atoms/Background.tsx';
import { BGMProvider } from './sound/SoundContext.tsx';
import { WebSocketProvider } from './websocket/WebSocketContext.tsx';
import { Container } from './components/Common/Atoms/Alarm.tsx';

function App() {
  return (
    <RecoilRoot>
      <WebSocketProvider>
        <BGMProvider>
          <BackgroundImage />
          <GlobalStyle />
          <Container limit={1} position="top-center" theme="colored" />
          <AppRouter />
        </BGMProvider>
      </WebSocketProvider>
    </RecoilRoot>
  );
}
export default App;
