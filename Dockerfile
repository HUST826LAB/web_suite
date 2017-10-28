#================
#写个dockerfile 
#快速部署
#================
#基础镜像 官方java 8-jre
FROM  java:8-jre

#维护人 徐嘉埥
MAINTAINER xujiaqing <jqxu0331@gmail.com>

#添加包
COPY ./web_Suite.zip /var/

#解压包 修改文件权限
RUN /bin/bash -c 'unzip -d /var/ /var/web_Suite.zip && chmod -R 744 /var/apache-tomcat-8.0.46'


#CMD ["sh","/var/czwapps/bin/startup.sh"]

#对外暴露端口8080
EXPOSE      8080
