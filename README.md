## 🧑‍🏫 라온 🇰🇷

아이들을 위한 놀이를 통한 국어 학습 게임 웹 앱 서비스


### Overview

> 💡 SNS의 발달과 코로나 이후 아이들의 국어 능력이 저하되고 있습니다. <ins>**_라온_** 은 "즐거운"이라는 우리말</ins>로 친구들과 함께하는 놀이를 통해 국어를 자연스럽게 학습할 수 있도록 돕는 서비스입니다.


### Project Info

🗓️ 2023. 10. 10 ~ 2023. 11. 17. (총 6주)

🏆 프로젝트 우수상, 베스트 멤버 선정

<br/>

### 👨‍👩‍👦‍👦 팀원 역할 및 소개

| Frontend | 역할      | 담당                  |
| -------- | --------- | --------------------- |
| 정준혁   | 팀장      | 페이지 구성, API 연결 |
| 서인덕   | FE-Leader | 전역 데이터 흐름 제어 |

| Backend | 역할          | 담당                                          |
| ------- | ------------- | --------------------------------------------- |
| 고재원  | BE-Leader     | Web Socket 멀티 플레이                        |
| 박영서  | Full Stack    | Web Socket 멀티 플레이, 퀴즈 랭킹 조회        |
| 하재우  | Backend       | Spring Security + JWT                         |
| 김태현  | Backend/Infra | 퀴즈 랭킹 조회, 사용자 정보 입력 및 학교 검색 |



## ✨ 핵심 기능

### 🌈 맞춤법 놀이 <br/>
**`평소에 몰랐거나 햇갈리던 맞춤법을 퀴즈로 재미있게!`** <br/>

### ❄ 국어사전 놀이 <br/>

**`단어의 의미를 퀴즈를 통해 재미있게 배울 수 있어요!`** <br/> > **`동서남북 퀴즈를 하며 사고력을 길러요!`**

### ⭐ 순위 <br/>
**`전국에서 혹은 교내에서 내가 몇등인지 확인해요!`** <br/> > **`친구들과 함께하며 즐겨요!`**


</br>

## ⚙️ 시스템 설계

### System Architecture

![Untitled](./exec/images/infraStructure.png)

### ER-Diagram


</br>

## 🛠️ Skills

### Server

<p>
    <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Conda-Forge&logoColor=white" /> 
    <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat&logo=springboot&logoColor=white"/>
    <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=flat&logo=springsecurity&logoColor=white"/>
    <img src="https://img.shields.io/badge/JWT-000000?style=flat&logo=jsonwebtokens&logoColor=white"/>
    <img src="https://img.shields.io/badge/spring_Web_Socket-6DB33F?style=flat&logo=socket.io&logoColor=white"/>
    <img src="https://img.shields.io/badge/gradle-02303A?style=flat&logo=gradle&logoColor=white">
</p>

### Database

<p>
    <img src="https://img.shields.io/badge/redis-DC382D?style=flat&logo=redis&logoColor=white"/>
    <img src="https://img.shields.io/badge/MariaDB-4479A1?style=flat&logo=mariadb&logoColor=white"/>
</p>

### Front-end

<p>
    <img src="https://img.shields.io/badge/TypeScript-3178C6?style=flat&logo=typescript&logoColor=white"/>
    <img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=react&logoColor=white"/>
    <img src="https://img.shields.io/badge/Recoil-3578E5?style=flat&logo=recoil&logoColor=white"/>
    <img src="https://img.shields.io/badge/pwa-5A0FC8?style=flat&logo=pwa&logoColor=white"/>
    <img src="https://img.shields.io/badge/Styled_Components-DB7093?style=flat&logo=styledcomponents&logoColor=white"/>
</p>

### Infra

<p>
    <img src="https://img.shields.io/badge/amazonec2-FF9900?style=flat&logo=amazonec2&logoColor=white"/>
    <img src="https://img.shields.io/badge/ubuntu-E95420?style=flat&logo=ubuntu&logoColor=white"/>
    <img src="https://img.shields.io/badge/docker-2496ED?style=flat&logo=docker&logoColor=white"/>
    <img src="https://img.shields.io/badge/nginx-009639?style=flat&logo=nginx&logoColor=white"/>
    <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/>
</p>

### Project Management

<p>
    <img src="https://img.shields.io/badge/jira-0052CC?style=flat&logo=jira&logoColor=white"/>
    <img src="https://img.shields.io/badge/gitlab-FC6D26?style=flat&logo=gitlab&logoColor=white"/>
    <img src="https://img.shields.io/badge/swagger-85EA2D?style=flat&logo=Swagger&logoColor=white"/>
    <img src="https://img.shields.io/badge/notion-000000?style=flat&logo=notion&logoColor=white"/>
    <img src="https://img.shields.io/badge/mattermost-0058CC?style=flat&logo=mattermost&logoColor=white"/>
</p>
<br/>

## ⭐ Issue
### Back-end
📌 **조회 속도 향상(실시간 랭킹 조회)**

- 문제: 랭킹 시스템의 경우 실시간으로 기록을 업데이트 되어야 하고, 업데이트 되는 기간이 존재 → RDBMS 활용에 대한 의문점이 생김
- 해결: In-Memory DB인 Redis의 sorted-set을 통해 구현
- 비고: 10만개의 더미 데이터를 활용해 조회 속도를 비교해 본 결과 4배 빠른 성능이 보임을 확인

</br>

### Front-end
📌 **StompJS를 활용한 소켓 통신 구현**

- 문제: iframe을 통해 접근할 수 없는 문제 발생
- 원인: X-Frame-Options to DENY 설정 때문
- 해결:
    
    1) header, Socket Option 설정을 ‘sameOrigin’으로 변경하여 해결

</br>

## 🎬 시나리오

1. 최초에는 초기 화면으로 이동하게 됩니다.

   로그인은 `카카오` 로 진행할 수 있습니다.

<table>
    <tr align="center">
        <td><B>스플래시</B></td>
        <td><B>메인 페이지</B></td>
    </tr>
    <tr align="center">
        <td>
            <img src="./exec/images/splash.png" alt="스플래시">
        </td>
        <td>
            <img src="./exec/images/main.png" alt="메인페이지">
        </td>
    </tr>
</table>

2.  맞춤법 놀이는 빈칸에 들어갈 알맞은 말을 고르는 놀이입니다.

    한 문제를 풀 때마다 정답 여부와 해당 문제의 난이도를 보여줍니다.

    난이도는 정답율에 기반하여 산정됩니다.

    총 10문제를 풀고 나서 점수와 결과를 확인할 수 있습니다.

<table>
    <tr align="center">
        <td><B>문제 풀기</B></td>
        <td><B>정답 여부 확인</B></td>
        <td><B>최종 점수 확인</B></td>
    </tr>
    <tr align="center">
        <td>
            <img src="./exec/images/spelling1.png" alt="문제풀기">
        </td>
        <td>
            <img src="./exec/images/spelling2.png" alt="정답여부확인">
        </td>
        <td>
            <img src="./exec/images/spelling3.png" alt="최종점수확인">
        </td>
    </tr>
</table>

3.  국어사전 놀이는 두가지 놀이를 제공합니다.

    단어의 의미와 초성을 보고 정답을 입력하거나,

    위, 아래, 왼쪽, 오른쪽 모두 단어가 될 수 있도록 하는 글자를 입력할 수 있습니다.

<table>
    <tr align="center">
        <td><B>모드 선택</B></td>
        <td><B>사전 퀴즈</B></td>
        <td><B>동서남북 퀴즈</B></td>
    </tr>
    <tr align="center">
        <td>
            <img src="./exec/images/dictionary1.png" alt="모드선택">
        </td>
        <td>
            <img src="./exec/images/dictionary2.png" alt="사전퀴즈">
        </td>
        <td>
            <img src="./exec/images/dictionary3.png" alt="동서남북퀴즈">
        </td>
    </tr>
</table>

4.  멀티 플레이는 친구와 경쟁을 하며 놀이를 즐길 수 있습니다.

    방 코드는 카카오톡으로 공유할 수 있으며,

    정답을 가장 빨리 맞춘 플레이어에게 제일 높은 점수가 부여됩니다.

<table>
    <tr align="center">
        <td><B>방 생성</B></td>
        <td><B>대기 방</B></td>
        <td><B>중간 결과</B></td>
        <td><B>최종 결과</B></td>
    </tr>
    <tr align="center">
        <td>
            <img src="./exec/images/multi1.png" alt="방 생성">
        </td>
        <td>
            <img src="./exec/images/multi2.png" alt="대기 방">
        </td>
        <td>
            <img src="./exec/images/multi3.png" alt="중간 결과">
        </td>
        <td>
            <img src="./exec/images/multi4.png" alt="최종 결과">
        </td>
    </tr>
</table>

5.  순위를 확인하며 친구와 재미있게 경쟁할 수 있으며,

    내 정보를 변경할 수 있습니다.

<table>
    <tr align="center">
        <td><B>순위</B></td>
        <td><B>내 정보</B></td>
    </tr>
    <tr align="center">
        <td>
            <img src="./exec/images/rank.png" alt="순위">
        </td>
        <td>
            <img src="./exec/images/mypage.png" alt="내 정보">
        </td>
    </tr>
</table>
