FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/Cheeseria-0.0.1-SNAPSHOT.jar patientzerocheeseria.jar
EXPOSE 3000
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar patientzerocheeseria.jar
