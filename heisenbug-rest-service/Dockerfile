FROM openjdk:8-jre-alpine
COPY ./target/heisenbug-rest-service-0.0.1-SNAPSHOT.jar /usr/src/app/
WORKDIR /usr/src/app

ENV JPDA_ADDRESS=8787
ENV JPDA_TRANSPORT=dt_socket

EXPOSE 8787
EXPOSE 8081
CMD ["java", "-jar", "heisenbug-rest-service-0.0.1-SNAPSHOT.jar", "jpda", "--another.service.address=http://mock-service:8080"]