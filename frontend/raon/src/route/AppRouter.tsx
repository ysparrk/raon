import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';

// Interface
import Landing from '../components/Interface/Pages/Landing.tsx';
import Main from '../components/Interface/Pages/Main.tsx';

// // User
// import Login from '../components/User/Pages/Login.tsx';
import Information from '../components/User/Pages/Information.tsx';
import SocialLogin from '../components/User/Pages/SocialLogin.tsx';

// // GameSpelling
import SpellingQuiz from '../components/GameSpelling/Pages/SpellingQuiz.tsx';
import SpellingResult from '../components/GameSpelling/Pages/SpellingResult.tsx';
import SpellingInit from '../components/GameSpelling/Pages/SpellingInit.tsx';

// // GameSummarize
import SummarizeCategory from '../components/GameSummarize/Pages/SummarizeCategory.tsx';
import SummarizeQuiz from '../components/GameSummarize/Pages/SummarizeQuiz.tsx';
import SummarizeResult from '../components/GameSummarize/Pages/SummarizeResult.tsx';

// // GameDictionary
import DictionaryJoin from '../components/GameDictionary/Pages/DictionaryJoin.tsx';
import DictionaryQuiz from '../components/GameDictionary/Pages/DictionaryQuiz.tsx';
import DictionarySingleGame from '../components/GameDictionary/Pages/DictionarySingleGame.tsx';
import DictionaryInit from '../components/GameDictionary/Pages/DictionaryInit.tsx';
import DictionaryWaitingRoom from '../components/GameDictionary/Pages/DictionaryWaitingRoom.tsx';
import DictionarySingleResult from '../components/GameDictionary/Pages/DictionarySingleResult.tsx';
// import DictionaryResult from '../components/GameDictionary/Pages/DictionaryResult.tsx';

// // Ranking
// import Ranking from '../components/Ranking/Pages/Ranking.tsx';

const AppRouter: React.FC = () => {
  return (
    // <div className="App">
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/main" element={<Main />} />
        <Route path="/information/submit" element={<Information />} />
        <Route path="/social" element={<SocialLogin />} />
        <Route
          path="/game/summarize-category"
          element={<SummarizeCategory />}
        />
        <Route path="/game/summarize-quiz" element={<SummarizeQuiz />} />
        <Route path="/game/summarize-result" element={<SummarizeResult />} />
        <Route path="/game/spelling-init" element={<SpellingInit />} />
        <Route path="/game/spelling-quiz" element={<SpellingQuiz />} />
        <Route path="/game/spelling-result" element={<SpellingResult />} />
        <Route path="/game/dictionary-init" element={<DictionaryInit />} />
        <Route path="/game/dictionary-join" element={<DictionaryJoin />} />
        <Route path="/game/dictionary-quiz" element={<DictionaryQuiz />} />

        <Route
          path="/game/dictionary-single-game"
          element={<DictionarySingleGame />}
        />
        <Route
          path="/game/dictionary-game/waiting-room"
          element={<DictionaryWaitingRoom />}
        />
        <Route
          path="/game/dictionary-single-result"
          element={<DictionarySingleResult />}
        />
        {/* <Route path="/user/" element={<Foundation />} /> */}
      </Routes>
    </BrowserRouter>
    // </div>
  );
};

export default AppRouter;
