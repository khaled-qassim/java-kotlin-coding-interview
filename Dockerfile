FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /app/target/rockpaperscissor-1.0-SNAPSHOT.jar ./app.jar

COPY --from=build /root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/2.1.0/kotlin-stdlib-2.1.0.jar ./kotlin-stdlib.jar

CMD ["java", "-cp", "app.jar:kotlin-stdlib.jar", "com.hadsol.MainKt"]