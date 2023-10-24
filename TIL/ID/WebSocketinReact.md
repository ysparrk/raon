WebSocket은 양방향 통신을 지원하는 프로토콜로, 웹 애플리케이션과 서버 간의 실시간 데이터 교환을 가능하게 합니다. React를 사용하여 WebSocket을 구현하기 위해 WebSocket API를 활용하고, 상태 관리를 위해 useState 훅을 사용하는 간단한 예제를 제공하겠습니다.

먼저, React 프로젝트를 설정하고 WebSocket을 사용할 준비를 해야 합니다. 아래는 간단한 React WebSocket 예제 코드입니다.

1. 먼저, React 앱을 생성하고 필요한 모듈을 설치합니다.

```
npx create-react-app websocket-example
cd websocket-example
npm install
```

2. WebSocket 통신을 관리하는 컴포넌트를 만듭니다. 예를 들어, WebSocketExample.js 파일을 생성하고 다음과 같이 작성합니다

```
import React, { useState, useEffect } from 'react';

function WebSocketExample() {
  const [message, setMessage] = useState('');
  const [ws, setWs] = useState(null);

  useEffect(() => {
    // 웹소켓 연결
    const socket = new WebSocket('ws://example.com'); // 웹소켓 서버 주소 입력

    socket.onopen = () => {
      console.log('WebSocket 연결이 열렸습니다.');
      setWs(socket);
    };

    socket.onmessage = (event) => {
      setMessage(event.data);
    };

    socket.onclose = () => {
      console.log('WebSocket 연결이 닫혔습니다.');
    };

    return () => {
      // 컴포넌트 언마운트 시 웹소켓 연결 해제
      socket.close();
    };
  }, []);

  const handleSendMessage = () => {
    if (ws) {
      ws.send('안녕, 서버!'); // 원하는 메시지 전송
    }
  };

  return (
    <div>
      <h1>WebSocket 예제</h1>
      <div>
        <button onClick={handleSendMessage}>서버에 메시지 전송</button>
      </div>
      <div>
        <p>서버로부터의 메시지: {message}</p>
      </div>
    </div>
  );
}

export default WebSocketExample;
```

3. WebSocketExample 컴포넌트를 앱에 추가합니다. src/App.js 파일을 열고 다음과 같이 수정합니다:

```
import React from 'react';
import './App.css';
import WebSocketExample from './WebSocketExample';

function App() {
  return (
    <div className="App">
      <WebSocketExample />
    </div>
  );
}

export default App;
```

4. 웹소켓 서버 주소를 실제 서버 주소로 바꾸어야 합니다. 이 예제에서는 'ws://example.com'로 설정되어 있으므로, 실제 서버 주소로 변경해야 합니다.

5. 앱을 실행합니다.

이제 웹 앱을 열면 WebSocket을 사용하여 서버와 실시간 통신을 할 수 있습니다. 서버로 메시지를 보내고 서버에서 오는 메시지를 받아 화면에 표시할 수 있습니다. 주의할 점은 실제 서버가 WebSocket을 지원하도록 설정되어 있어야 합니다. 이 예제에서는 웹소켓 연결이 열리면 메시지를 보내는 간단한 예제를 제공하였습니다.
