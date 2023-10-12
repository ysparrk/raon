# [Ubuntu] Docker로 MySQL 빌드

1. 이미지 받아오기
    
    ```bash
    sudo docker pull mysql
    
    ## 버전 지정 시
    docker pull mysql:8.0.22
    ```
    
    - 확인
        
        ```bash
        sudo docker images
        ```
        
        ![Untitled](%5BUbuntu%5D%20Docker%E1%84%85%E1%85%A9%20MySQL%20%E1%84%87%E1%85%B5%E1%86%AF%E1%84%83%E1%85%B3%201eb517a22e944a8f91d3c941929e7be1/Untitled.png)
        
2. 도커 생성하기
    
    ```bash
    # (이미 있는 경우 도커 삭제)
    sudo docker stop mysql-container
    sudo docker rm mysql-container
    
    # 도커 생성
    sudo docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=reality -d -p 3306:3306 mysql:latest
    ```
    
    - 확인
        
        ```bash
        sudo docker ps -a
        ```
        
        ![Untitled](%5BUbuntu%5D%20Docker%E1%84%85%E1%85%A9%20MySQL%20%E1%84%87%E1%85%B5%E1%86%AF%E1%84%83%E1%85%B3%201eb517a22e944a8f91d3c941929e7be1/Untitled%201.png)
        
3. 컨테이너 시작/중지
    
    ```bash
    # MySQL Docker 컨테이너 중지
    $ docker stop mysql-container
    
    # MySQL Docker 컨테이너 시작
    $ docker start mysql-container
    
    # MySQL Docker 컨테이너 재시작
    $ docker restart mysql-container
    ```
    
4. database 접속
    
    ```bash
    # mysql 컨테이너 접속
    sudo docker exec -it mysql-container bash
    
    # mysql 접속
    mysql -u root -p
    ```
    
5. database 생성
    
    ```bash
    mysql> create database curious;
    
    mysql> show databases;
    +--------------------+
    | Database           |
    +--------------------+
    | curious            |
    | information_schema |
    | mysql              |
    | performance_schema |
    | sys                |
    +--------------------+
    5 rows in set (0.00 sec)
    ```