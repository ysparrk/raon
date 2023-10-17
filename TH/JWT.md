# TIL (2023.10.16)

## Spring Security란?

> Spring Security는 JAVA 애플리케이션에 인증 및 권한 부여를 모두 제공하는 데 중점을 둔 프레임워크.
아무튼 보안 도와주는 친구!!!
> 

인증과 권한에 대한 부분을 Filter 흐름에 따라 처리하고 있다. Filter는 Dispatcher Servlet으로 가기 전에 적용되므로 가장 먼저 URL 요청을 받지만 Interceptor는 Dispatcher와 Controller사이에 위치한다는 점에서 적용 시기의 차이가 있음.

**특징**

- 인증 및 권한 부여에 대한 포괄적이고 확장 가능한 지원
- 여러가지 공격으로부터 보호
- 서블릿 API 통합
- 등등ㄷ읃읃읃등등 여러가지…

![1](https://github.com/Hyeon0706/imageRepository/assets/83438780/b7718f1e-a622-4780-ac20-388d07ef3aa9)

## 간략한 구조

### 구조(Login)

![2](https://github.com/Hyeon0706/imageRepository/assets/83438780/15dd8410-2adb-4da2-b77b-6602cb222077)

![3](https://github.com/Hyeon0706/imageRepository/assets/83438780/ac1c97af-cb43-4c16-a6db-3f8de5d501af)

인증 처리 전

---

1. AntPathRequestmatcher
    - 사용자가 요청한 요청정보를 확인하여 요청정보 URL이 /login으로 시작하는지 확인
    - 요청한다면 다음단계(인증처리)로 진행되고, 일치하지 않는다면 다음 필터로 진행 됨(chain.doFilter)
    - /login URL은 `.loginProcessingUrl()` 으로 변경 가능
2. Authentication
    - 실제 인증처리를 담당.
    - 로그인 페이지에서 입력한 id와 pw를 인증객체(Authentication)에 저장하여 인증처리(AuthenticationManager)를 맡기는 역할을 함

인증 처리 후

---

1. AuthenticationManager
    - 내부적으로 AuthenticationProvier에게 인증처리를 위임
    - 해당 Provider가 인증처리를 담당하는 클래스로써 인증에 성공/실패를 반환
        - 실패를 반환할 경우 AuthenticationException 예외를 반환하여 UsernamePasswordAuthenticationFilter로 돌아가서 예외처리를 수행
        - 성공을 반환할 경우 Authentication객체를 생성하여 User객체와 Authorities 객체를 담아서 AuthenticationManager에게 반환
    - Provider로부터 반환받은 인증객체(인증결과 유저(User), 유저권한정보(Authorities))를 SecurityContext객체에 저장한다.
2. SecurityContext
    - Session에도 저장되어 전역적으로 SecurityContext를 참조할 수 있음
3. 인증 성공 이후 SuccessHandler에서 인증 성공이후의 로직을 수행

### 구조(Logout)

![4](https://github.com/Hyeon0706/imageRepository/assets/83438780/7457a052-1e8b-4f38-be43-04140330d3b6)

![5](https://github.com/Hyeon0706/imageRepository/assets/83438780/d2802a3d-4f6c-4f41-823e-4abd792bd18d)

1. 요청이 Logout URL인지 확인
2. 맞으면 SecurityContext에서 인증객체(Authentiation)객체를 꺼냄
3. SecurityContextLogoutHandler에서 세션 무효화, 쿠키삭제, clearContext()를 통해 SecurityContext객체를 삭제하고 인증객체도 null로 만듬
4. SimpleUrlLogtouSuccsessHandler를 통해 로그인페이지로 리다이렉트