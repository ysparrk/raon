# TIL (2023.10.17)

## Nginx

> - 무료로 사용할 수 있는 오픈 소스 웹 서버 소프트웨어
- 웹 서버, 리버스 프록시, 로드 밸런싱 등의 기능을 가짐
- 비동기 이벤트 기반으로 동작
> 

왜 사용하는지는 알지??

**설정 방법**

- nginx.conf를 직접 수정
- conf.d 폴더에 설정 파일 (conf 확장자) 를 추가하는 방법 ⇒ 이 방법으로 할거임
1. /etc/nginx/conf.d 폴더에 `defualt.conf` 파일을 생성함

```bash
sudo vim default.conf
```

1. 다음 명령어를 입력한 뒤 저장

```bash
upstream frontend {
	server localhost:3000;
}
upstream backend {
	server localhost:8080;
}
server {
	listen 80;
		server_name <서버 주소>;
		location /api {
			rewrite ^/api(/.*)$ $1 break;
			proxy_pass http://backend;
			proxy_http_version 1.1;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection "upgrade";
			proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header X-Forwarded-Proto $scheme;
		}
		location / {
			proxy_pass http://frontend;
			proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header X-Forwarded-Proto $scheme;
		}
	}

```

- `server` 블록
    - 특정 프로토콜에 대한 설정들을 그룹화 하는데 사용되는 블록
    - `listen`
        - 어떤 포트에서 연결을 수신할지 지정
        - 모든 인터페이스의 80(http)번 포트에서 연결을 수신하도록 함
    - `server_name`
        - 어떤 URL로 접근했을 때 연결을 수신할지 지정
        - `DNS` 설정 이후 더 다양한 방법으로 활용할 수 있음
    - `location`
        - 특정 URI마다 처리하는 방법을 다르게 만들기 위해 사용
        - 프록시가 어떻게 동작할지, 리다이렉트, 로깅, 디도스 제한, 이 외에도 수 많은 설정을 할 수 있음
        - `rewrite`
            - URI를 이용해 수신할 location 블록은 결정했지만, 실제 서버에서 URI 패턴이 다른 경우 사용
            - 예를 들어 Backend 서버의 URI는 `/` 로 시작하지만, 단순히 proxy_pass만 설정할 경우 `/api` 라는 URI가 그대로 적용되어 404에러가 발생할 수 있음
            - 해당 정규식은 /api 라는 URI를 삭제하고 실제 요청이 들어온 나머지 URI만 남기는 정규식
        - `proxy_pass`
            - 위치를 알려서 요청이 뒷단 서버로 전달되도록 지정
            - `localhost:8080`과 같이 직접 URL을 적을 수도 있고, `upstream 블록`을 참조할 수도 있음
        - `proxy_http_version 1.1;`
        `proxy_set_header Upgrade $http_upgrade;`
        `proxy_set_header Connection “upgrade”;`
            - 설정하지 않으면 가장 기본적인 http 프로토콜인 http 1로 동작함. http 통신은 연결을 유지하는 속성이 없으므로 1.1 버전을 사용해야 하며, 이를 설정해야 연결을 유지할 수 있음 (소켓 등)
            - 추후에 성능 이슈가 발생할 경우 http 버전 문제인 경우가 종종 있음
        - 기타 설정
            - nginx 설정을 최적화하고, 리버스 프록시 구성에 대부분 유효하게 동작하는 설정
- `upstream 블록`
    - 부하 분산을 위해 Nginx가 지원하는 Stream 모듈
    - 서버의 그룹을 지정할 수 있으며, 이를 이용해 로드 밸런싱, HealthCheck등 구현 가능
1. 위 설정을 마친 후 nginx 재시작

```bash
sudo service nginx restart
```