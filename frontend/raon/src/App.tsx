import './App.css';
import { RecoilRoot } from 'recoil';
import AppRouter from './route/AppRouter';
import { GlobalStyle } from './style/GlobalStyle';

function App() {
  return (
    <RecoilRoot>
      <GlobalStyle />
      <AppRouter />
    </RecoilRoot>
  );
}
export default App;
