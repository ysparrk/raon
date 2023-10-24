### CI/CD

1. Ubuntu 에 Jenkins 를 올려서 gradle + springboot + jpa 웹 어플리케이션 자동 빌드 & 배포 시스템 구축

2. DBMS는 mysql을 사용하고 다른 컨테이너에 올려서 링크

3. 웹 컨테이너에서 Jenkins:30000, 웹:8080를 사용하고 DB 컨테이너에서는 mariadb:3306 사용할 예정

    |   *(웹 컨테이너에는 Tomcat 없이 gradle 내장 tomcat 사용하여 배포 예정임)*

4. 내 도커 허브에 이미지 올려놓고 사용하기