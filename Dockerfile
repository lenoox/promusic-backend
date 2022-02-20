FROM maven:3.6.3 AS build
WORKDIR /app
COPY . ./
RUN mvn -B clean package -DskipTests

FROM openjdk:8-alpine
WORKDIR /app
COPY --from=build /app/target/promusic-demo-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
ENTRYPOINT ["java","-jar","promusic-demo-0.0.1-SNAPSHOT.jar"]