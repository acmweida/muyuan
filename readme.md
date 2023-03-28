## command
~~~
linux:
firewall-cmd --zone=public --add-port=1080/tcp --permanent
systemctl restart firewalld
~~~

## muyuan-business
### docker 
~~~
yum install docker

docker compose up -d
docker compose stop
~~~
### gitlab
1. docker 安装gitlab
2. 与github同步

### nginx Jenkins

### docker-compose配置
```yaml
version: '3'

services:                                      # 集合
  docker_jenkins:
    environment:
      - TZ=Asia/Shanghai
    user: root                                 # 为了避免一些权限问题 在这我使用了root
    restart: always                            # 重启方式
    image: jenkins/jenkins:lts                 # 指定服务所使用的镜像 在这里我选择了 LTS (长期支持)
    container_name: jenkins                    # 容器名称
    ports:                                     # 对外暴露的端口定义
      - 8081:8080
      - 50000:50000
    volumes:                                   # 卷挂载路径
      - /home/docker/jenkins/jenkins_home/:/var/jenkins_home  # 这是我们一开始创建的目录挂载到容器内的jenkins_home目录
      - /var/run/docker.sock:/var/run/docker.sock
      - /usr/bin/docker:/usr/bin/docker                # 这是为了我们可以在容器内使用docker命令
      - /usr/local/bin/docker-compose:/usr/local/bin/docker-compose
      - /usr/local/jdk1.8:/usr/local/jdk1.8
      - /usr/local/maven:/usr/local/maven
      - /etc/profile:/etc/profile
      - /root/.ssh/id_rsa:/root/.ssh/id_rsa
  docker_nginx:
    restart: always
    image: nginx
    container_name: nginx
    environment:
      - TZ=Asia/Shanghai
    ports:
      - 80:80
      - 433:433
    volumes:
      - /home/docker/nginx/conf:/etc/nginx
      - /home/docker/nginx/html:/etc/nginx/html
      - /home/docker/nginx/logs:/var/log/nginx
      - /home/docker/webserver/vue3-demo/dist:/usr/share/nginx/html
  docker_gitlab:
    restart: always
    image: gitlab/gitlab-ce
    container_name: gitlab
    environment:
      - TZ=Asia/Shanghai
    ports:
      - 10443:443
      - 1080:80
      - 1022:22
    volumes: #数据卷的意思 即数据缓存 即使源文件丢失 在右侧的文件会缓存之前的信息
      - /home/docker/gitlab/config:/etc/gitlab
      - /home/docker/gitlab/logs:/var/log/gitlab
      - /home/docker/gitlab/data:/var/opt/gitlab
```
