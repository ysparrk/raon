# TIL (2023.10.19)

## OAuth2

### 역할

- Resource Owner
    - 리소스 소유자. 본인의 정보에 접근할 수 있는 자격을 승인하는 주체.
- Client
    - Resource Owner의 리소스를 사용하고자 접근 요청을 하는 어플리케이션
- Resource Server
    - Resource Owner의 정보가 저장되어 있는 서버
- Authorization Server
    - 권한 서버. 인증/인가를 수행하는 서버로 클라의 접근 자격을 확인하고 AccessToken을 발급하여 권한을 부여하는 역할 수행

### 주요 용어

- Authentication (인증)
    - 인증, 접근 자격이 있는지 검증하는 단계
- Authorization (인가)
    - 자원에 접근할 권한을 부여하고 리소스 접근 권한이 담긴 AceessToken 제공

### 권한 부여 방식

4가지가 있지만 RefreshToken을 사용할 수 있는 방식은 두 가지가 있기 때문에 두 가지만 설명

1. Authorization Code Grant (권한 부여 승인 코드 방식)
- 권한 부여 승인을 위해 자체 생성한 Authorization Code를 전달하는 방식으로, 많이 쓰이고 기본이 되는 방식
- 간편 로그인 기능에서 사용되는 방식으로 클라가 사용자를 대신하여 특정 자원에 접근을 요청할 때 사용되는 방식
    
    
2. Resource Owner Password Credentials Grant (자원 소유자 자격증명 승인 방식)
- 간단하게 username, password로 AccessToken을 받는 형식
- 클라가 타사의 외부 프로그램일 경우에는 이 방식 안 됨

### 프로젝트에 적용 할 Oauth 구조


1. 사용자가 접근 및 이용을 시도
2. Client는 플랫폼(카카오, 구글)의 Authorization Server로 Client-id를 사용하여 로그인 페이지를 요청
3. 로그인 페이지에 입력한 id, password를 통해 Authorization Server로 Authorization code를 발급 받음
⇒ 각 플랫폼에 사용자가 입력한 redirect uri로
4. Autorization code를 통해 Authorization Server로 사용자 정보를 받아오기 위한 AccessToken을 받음
5. AccessToken을 사용하여 필요한 사용자 정보를 받아옴