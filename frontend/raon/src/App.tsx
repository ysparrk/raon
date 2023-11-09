import './App.css';
import { RecoilRoot } from 'recoil';
import AppRouter from './route/AppRouter';
import { GlobalStyle } from './style/GlobalStyle';
import BackgroundImage from './components/Common/Atoms/Background.tsx';
import { BGMProvider } from './sound/SoundContext.tsx';

function App() {
  return (
    <RecoilRoot>
      <BGMProvider>
        <BackgroundImage />
        <GlobalStyle />
        <AppRouter />
      </BGMProvider>
    </RecoilRoot>
  );
}
export default App;
