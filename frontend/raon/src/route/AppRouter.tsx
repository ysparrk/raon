import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';

// Interface
import Landing from '../components/Interface/Pages/Landing.tsx';
import Main from '../components/Interface/Pages/Main.tsx';

// // User
// import Login from '../components/User/Pages/Login.tsx';
// import Information from '../components/User/Pages/Information.tsx';

// // GameSpelling
import SpellingQuiz from '../components/GameSpelling/Pages/SpellingQuiz.tsx';
import SpellingResult from '../components/GameSpelling/Pages/SpellingResult.tsx';

// // GameSummarize
// import SummarizeCategory from '../components/GameSummarize/Pages/SummarizeCategory.tsx';
// import SummarizeQuiz from '../components/GameSumarize/Pages/SummarizeQuiz.tsx';
// import SummarizeResult from '../components/GameSumarize/Pages/SummarizeResult.tsx';

// // GameDictionary
// import DictionaryCategory from '../components/GameDictionary/Pages/DictionaryCategory.tsx';
// import DictionaryJoin from '../components/GameDictionary/Pages/DictionaryJoin.tsx';
// import DictionaryQuiz from '../components/GameDictionary/Pages/DictionaryQuiz.tsx';

// // Ranking
// import Ranking from '../components/Ranking/Pages/Ranking.tsx';

const AppRouter: React.FC = () => {
  return (
    // <div className="App">
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/main" element={<Main />} />
        <Route path="/game/spelling-quiz" element={<SpellingQuiz />} />
        <Route path="/game/spelling-result" element={<SpellingResult />} />
        {/* <Route path="/user/" element={<Foundation />} /> */}
      </Routes>
    </BrowserRouter>
    // </div>
  );
};

export default AppRouter;
