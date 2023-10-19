# Docker
> Docker란 호스트 OS상에 논리적인 구획을 만들고 애플리케이션을 실행하기 위한 라이브러리와 애플리케이션을 모아 별도의 서버인 것처럼 동작하도록 구현한 컨테이너 기술

### 특징
- 호스트 OS의 리소스를 논리적으로 분리하고 여러 개의 컨테이너가 공유하여 사용하므로 가상머신과 비교했을 때 빠르고 오버헤드가 적다.
- 필요한 모듈을 컨테이너로 모으고 이 컨테이너들을 조합하여 사용 가능하므로 MSA에 친화적이다.

---

### 튜토리얼 -> Nginx Docker 사용하여 설치하기
- docker pull nginx  도커 이미지 풀받기
- docker image ls  도커 이미지 확인
- docker container run --name webserver -d -p 80:80 nginx  
- nginx 이미지를 사용하여 webserver라는 이름의 도커 컨테이너 실행
- docker container ps  nginx 서버 상태 확인
- docker container stats webserver
- docker stop webserver
- docker start webserver

### 도커 사용 스프링 부트 배포
- 도커 파일 작성
```
FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
- $ docker build --tag <도커계정명>/springapp:1.0.1 . -> 도커 파일 실행
- $ docker images
- $ docker login
- $ docker push
- 서버에 접속
- $ docker login
- $ docker pull <이미지 이름>
- $ docker run -i -t -p 8080:8080 <도커이미지> -> 포트 포워딩