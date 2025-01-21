
FROM amazoncorretto:17
WORKDIR /app
COPY build/libs/Flight-service-v1.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]
