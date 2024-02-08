FROM maven:3.8.5-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests=true

ARG JAR_FILE=popularRepositories-0.0.1-SNAPSHOT.jar
FROM openjdk:17-oracle
COPY --from=build /usr/src/app/target/${JAR_FILE} /usr/src/app/${JAR_FILE}
ENTRYPOINT ["java","-jar","/usr/src/app/popularRepositories-0.0.1-SNAPSHOT.jar"]