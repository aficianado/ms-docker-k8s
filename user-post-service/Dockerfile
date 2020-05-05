#FROM maven:3.6.3-jdk-8-slim AS build
#WORKDIR /home/app
#COPY . /home/app
#RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:8-jdk-alpine
#WORKDIR /home/app
VOLUME /tmp
EXPOSE 9010
#COPY --from=build /home/app/target/*.jar user-post-service.jar
COPY target/*.jar user-post-service.jar
ENTRYPOINT [ "sh", "-c", "java -jar /user-post-service.jar" ]