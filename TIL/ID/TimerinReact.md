# 타이머

React에서 타이머(timer)를 만들고 작동시키려면 JavaScript의 setTimeout 또는 setInterval 함수를 사용할 수 있습니다. 아래는 React에서 타이머를 만들고 작동시키는 간단한 예제입니다:

React 앱에서 타이머 상태를 관리하기 위한 상태 변수(state)를 설정합니다.

```
import React, { useState, useEffect } from 'react';

function TimerApp() {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setSeconds(seconds + 1);
    }, 1000);

    // 컴포넌트가 언마운트되면 타이머를 정리(clear)합니다.
    return () => clearInterval(timer);
  }, [seconds]);

  return (
    <div>
      <h1>타이머: {seconds} 초</h1>
    </div>
  );
}

export default TimerApp;
```

위의 예제에서는 useState로 seconds 상태를 초기화하고, useEffect를 사용하여 setInterval을 통해 1초마다 seconds 상태를 업데이트합니다. useEffect 함수에서 반환하는 함수를 사용하여 컴포넌트가 언마운트될 때 타이머를 정리합니다.

이제 이 컴포넌트를 다른 컴포넌트나 앱에 포함시키면 타이머가 작동할 것입니다.
