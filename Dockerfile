FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml mvnw* ./
COPY src ./src
RUN mvn -DskipTests -q package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/finops-example-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
