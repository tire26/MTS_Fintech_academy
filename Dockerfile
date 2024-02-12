FROM amazoncorretto:11-alpine-jdk
LABEL authors="tire26"
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} MTS_Fintech_academy-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/MTS_Fintech_academy-1.0-SNAPSHOT.jar"]