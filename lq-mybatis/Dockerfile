FROM openjdk:8u102-jre
RUN mkdir /apps
#RUN cd /liuqing
WORKDIR /liuqing/
COPY ./target/lq-mybatis-0.0.1-SNAPSHOT.jar /apps/app.jar
ENTRYPOINT ["java","-jar","/apps/app.jar"]