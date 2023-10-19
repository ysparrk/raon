- TypeScript
    - 정적 타입 언어
    - 변수, 함수, 객체 등의 모든 요소에 데이터 타입을 명시적으로 지정해줌.
    - 개발자에게 코드의 가독성, 안정성을 향상시켜줌.
    - 큰 규모 프로젝트에서 협업, 유지보수에 장점을 가짐.


    - 주요 특징 :
        1. 정적 타입 지원: 변수와 함수의 매개변수, 반환 값 등에 데이터 타입을 지정할 수 있어 코드의 예측 가능성과 안정성을 높입니다. 이로 인해 컴파일 시간에 타입 관련 오류를 미리 발견할 수 있습니다.

        2. 강력한 타입 추론: 타입스크립트는 타입을 직접 명시하지 않아도 대부분의 경우에서 변수와 함수의 타입을 추론할 수 있습니다. 이로써 코드를 더 간결하게 작성할 수 있습니다.

        3. 모듈 지원: 타입스크립트는 모듈 시스템을 지원하여 코드를 모듈로 분리하고 재사용 가능한 구성 요소로 사용할 수 있습니다.

        4. 인터페이스와 제네릭: 인터페이스와 제네릭을 사용하여 추상화 수준을 높이고 코드 재사용성을 높일 수 있습니다.

        5. 확장 가능한 타입 시스템: 타입스크립트는 사용자 정의 타입과 타입 연산자를 활용하여 고급 타입 시스템을 구축할 수 있습니다.

        6. 자바스크립트 호환성: 기존 자바스크립트 코드를 타입스크립트로 마이그레이션하기 쉽습니다. 타입스크립트는 JavaScript의 모든 기능을 지원하며, .js 파일을 .ts 파일로 변환하기만 하면 됩니다.

        7. 생태계와 도구 지원: 타입스크립트는 강력한 개발 도구와 함께 사용됩니다. Visual Studio Code와 같은 훌륭한 에디터 지원뿐만 아니라 다양한 빌드 도구 및 패키지 관리자와 통합하여 개발을 더욱 편리하게 만듭니다.

    타입스크립트는 대규모 프로젝트와 협업 환경에서 특히 유용하며, 코드의 가독성과 유지보수성을 향상시키면서 런타임 에러를 줄이는데 도움을 줍니다. 그러므로 모던 웹 및 애플리케이션 개발에 매우 인기 있는 언어 중 하나입니다.

- TypeScript 설치

    - node -v 를 입력하여 node가 설치되어 있는지 확인합니다.

 

    - node를 설치후 다음을 진행합니다. 

    - npm install typescript -g
    타입스크립트 글로벌 설치

 

    - 설치후 VScode에서 

 

    - tsc 파일명.ts 을 입력하면 해당  ts파일을 인식하여 같은경로에 js파일로 변환하여 생성해줍니다.

 

    - 그 다음 해당 경로에서

 

    - tsconfig.json 파일을 만들어 다음을 추가합니다.

    {
    "compilerOptions": {
        "allowJs": true,
        "checkJs": true,
        "noImplicitAny": true
    }
    }
    - compilerOptions 은 프로젝트에서 타입스크립트를 어떤 방식으로 이해할지 옵션을 정하는 것이라고 생각하면 됩니다.

    

    - "allowJS" 는 이 프로젝트에서 자바스크립트를 허용할지를 정하는 것 입니다.

    - "checkJS"는 자바스크립트에서 @ts-check와 같은 역할을 합니다.

    - @ts-check는 자바스크립트에서 타입검사 기능을 적용하고자 할때 사용하는 기능입니다.

    - "noImplicitAny"는 모든 타입에 대해서 최소한 기본적인 any 타입이라도 넣으라고 지정해주는 명령어입니다.



- styled-components란

    기존 돔을 만드는 방식인 css, scss 파일을 밖에 두고, 태그나 id, class이름으로 가져와 쓰지 않고, 동일한 컴포넌트에서 컴포넌트 이름을 쓰듯 스타일을 지정하는 것을 styled-components라고 부릅니다. 
    css 파일을 밖에 두지 않고, 컴포넌트 내부에 넣기 때문에, css가 전역으로 중첩되지 않도록 만들어주는 장점이 있습니다.

    #환경설정
    ```
    npm install styled-components
    ```
        
    #예시
    ```
    import React, { useState } from "react";
    import styled from "styled-components";

    function Example() {
    const [email, setEmail] = useState("");

    return (
        <ExampleWrap active={email.length}>
        <Button>Hello</Button>
        <NewButton color="blue">Im new Button</NewButton>
        </ExampleWrap>
    );
    }

    const ExampleWrap = styled.div`
    background: ${({ active }) => {
        if (active) {
        return "white";
        }
        return "#eee";
    }};
    color: black;
    `;

    const Button = styled.button`
    width: 200px;
    padding: 30px;
    `;

    // Button 컴포넌트 상속
    const NewButton = styled.Button`
    // NewButton 컴포넌트에 color가는 props가 있으면 그 값 사용, 없으면 'red' 사용
    color: ${props => props.color || "red"};
    `;

    export default Example;
    ```
        
    #styled components 만들기
    const 컴포넌트명 = styled.태그명스타일 넣기...문법으로 만들어집니다.
    만들고자하는 컴포넌트의 render 함수 밖에서 만듭니다.
    #스타일에 props 적용하기
    styled-component를 사용하는 장점 중 하나가 변수에 의해 스타일을 바꿀 수 있다는 점입니다.
    위 예시를 보면 email이라는 state값에 따라 ExampleWrap에 prop으로 내려준 active라는 값이 true or false로 바뀌게 됩니다.
    styled-component는 내부적으로 props을 받을 수 있고, 그 props에 따라 스타일을 변경할 수 있습니다.
    #스타일 상속
    const 컴포넌트명 = styled.스타일컴포넌트명스타일 넣기...문법으로 만들어집니다.
    기존에 있는 스타일컴포넌트를 상속받아 재사용합니다
