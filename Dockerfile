FROM java:8-jre
ADD target/b2b-service.jar /opt/b2b-service/
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV TZ "Asia/Shanghai"
EXPOSE 9999
WORKDIR /opt/b2b-service/
CMD ["java", "-jar", "b2b-service.jar"]
