import './App.css';
import { RecoilRoot } from 'recoil';
import AppRouter from './route/AppRouter';
import { GlobalStyle } from './style/GlobalStyle';
import BackgroundImage from './components/Common/Atoms/Background.tsx';

function App() {
  return (
    <RecoilRoot>
      <BackgroundImage />
      <GlobalStyle />
      <AppRouter />
    </RecoilRoot>
  );
}
export default App;
