## 필수 유틸 설치

```bash
sudo apt update
sudo apt install \
	apt-transport-https \
	ca-certificates \
    curl \
    software-properties-common
```

## 도커 설치

```bash
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

sudo apt update

sudo apt install docker-ce

// running 상태면 완료
sudo systemctl status docker
```

## 도커 컴포즈 설치

```bash
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

docker-compose -v
```