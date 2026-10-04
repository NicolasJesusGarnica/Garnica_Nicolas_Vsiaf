FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY . .
RUN ./gradlew build -x test
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/vsiaf-0.0.1-SNAPSHOT.jar"]