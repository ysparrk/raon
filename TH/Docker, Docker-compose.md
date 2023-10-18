## Docker

> 애플리케이션을 신속하게 구축, 테스트 및 배포할 수 있는 소프트웨어 플랫폼
컨테이너로 패키징 하여 올림
> 

**Container vs 가상 머신**


- 가상 머신은 각각의 시스템 마다 독립된 운영체제를 필요로 하지만, `Docker` 는 호스트 운영체제의 기능을 공유 받음
- container는 각각의 인스턴스에서 운영체제 만큼의 리소스를 아낄 수 있음
- CGroups와 Namespace를 이용해 앱을 가상화 하고 격리함. Windows에는 해당 기능이 없기 때문에 WSL과 Ubuntu가 필요함

**그러면 도커 왜 사용해???**

- 환경 일관성 : 어떤 환경에서도 동일한 실행을 보장함
- 경량화 : 위 이유로 VM에 비해 Docker 컨테이너가 훨씬 가볍고 빠름
- 모듈화, 확장성 : 도커를 통해 컴포넌트를 독립적인 컨테이너로 분리하여 개발하고 배포할 수 있음

### Docker 설치하기

1. 필요 패키지 설치

```bash
sudo apt-get -y install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
```

- `-y` : 이 명령어를 실행하면서 발생하는 모든 항목에 동의한다는 옵션
- `apt-transport-https` : HTTPS를 통해 SW를 다운받기위한 패키지
- `ca-certificates` : 인증된 SSL/TLS 인증서의 리스트를 관리하는 패키지
- `curl` : URL에서 데이터를 전송하는 데 사용되는 도구. 이를 이용해 특정 웹사이트에서 데이터를 받을 수 있음
- `gnupg-agent` : GNU 프라이버시 가드의 일부로, 사용자 인증에 사용되는 키 관리와 관련된 작업을 수행함
- `software-properties-common` : PPA(개인 패키지 저장소)를 추가할 때 사용함. 사적으로 만든 패키지를 받을 때 사용 됨
1. Docker에 대한 GPG key 등록

```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add
```

1. Dokcer 레포지토리 등록

```bash
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) s
table"
```

1. Docker 설치

```bash
sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io
```

1. 확인

```bash
# 버전 확인
docker -v

# 접속 사용자 확인
whoami

# Docker에 사용자 추가 & 재시작
sudo usermod -aG docker ubuntu
sudo service docker restart
```

### Docker-compose 설치하기

> 멀티 컨테이너 도커 어플리케이션의 설정과 배포를 간소화 할 수 있는 도구. YAML 스크립트 기반으로 동작함
> 
1. 필요 패키지 설치

```bash
sudo apt install jq
```

1. Docker-compose 버전 찾기

```bash
DCVERSION=$(curl --silent https://api.github.com/repos/docker/compose/releases/latest | jq .name -r)
```

1. 경로 지정

```bash
DCDESTINATION=/usr/bin/docker-compose
```

1. 원하는 경로에 다운 받기

```bash
sudo curl -L https://github.com/docker/compose/releases/download/${DCVERSION}/docker-compose-$(uname -
s)-$(uname -m) -o $DCDESTINATION
```

1. 권한 부여하기

```bash
sudo chmod 755 $DCDESTINATION
```

1. 확인

```bash
docker-compose -v
```

### MariaDB를 올려보자!!

1. 먼저 Docker 네트워크 생성

```bash
docker network create deploy
```

1. MariaDB 올리기

```bash
docker run -d \
	--network={네트워크 이름}\
	--name {컨테이너 이름} \
	-p 3306:3306 \
	-e MYSQL_ROOT_PASSWORD={비밀번호}\
	-e MYSQL_USER=ssafy \
	-e MYSQL_PASSWORD=ssafy \
	-e MYSQL_DATABASE=SSAFY \
	-v ssafy-db:/var/lib/mysql \
	-e MYSQL_CHARACTER_SET_SERVER=utf8mb4 \
	-e MYSQL_COLLATION_SERVER=utf8mb4_unicode_ci \
	mariadb:latest => 버전
```

- `-e` : 환경변수 설정
1. docker-compose로 MariaDB 올리기
먼저 mariadb 디렉토리 만들고 yml파일 생성

```bash
mkdir mariadb
cd mariadb
sudo vim docker-compose.yml
```

1. 생성한 파일 내용

```bash
version: "3"
services:
	db:
		image: mariadb:latest
		container_name: ssafy-db
		environment:
			MYSQL_ROOT_PASSWORD: ssafy
			MYSQL_USER: ssafy
			MYSQL_PASSWORD: ssafy
			MYSQL_DATABASE: SSAFY
			MYSQL_CHARACTER_SET_SERVER: utf8mb4
			MYSQL_COLLATION_SERVER: utf8mb4_unicode_ci
		ports:
			- 3306:3306
		volumes:
			- ssafy-db:/var/lib/mysql
		networks:
			- deploy
networks:
	deploy:
		external: true
volumes:
	ssafy-db:
```

- `version: “3”` : 파일의 규격을 결정하는 스크립트. “3” 이라고 적으면 3으로 시작하는 최신 버전을 사용함.
[호환성 체크 공식 문서](https://docs.docker.com/compose/compose-file/compose-versioning/)
- `service 블럭` : 실행하려는 컨테이너들을 정의.
- `db` : 서비스의 이름. 해당 컨테이너에 대한 정보가 들어감
- `iamge`
    - 사용할 이미지 결정
    - docker hub의 공식 mariadb 이미지 사용
- `container_name` : 컨테이너 이름을 명시적으로 지정
- `environment` : docker run의 -e 옵션에 있던 내용 적음
- `networks` : 이미 생성되어 있는 deploy 네트워크 사용
- `volumes` : Docker 볼륨이 존재하지 않는다면 생성
1. 파일 저장 후 `docker-compose.yml`이 있는 폴더에서 아래 명령어 실행

```bash
# detach 모드로 실행
sudo docker-compose up -d
```

1. 확인

```bash
sudo docker ps
```