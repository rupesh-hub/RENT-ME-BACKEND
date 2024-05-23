# BUILD STAGE
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# RUNTIME STAGE
FROM amazoncorretto:17

# ARGUMENTS
ARG PROFILE=dev
ARG VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/rent-me-prototype-*.jar /app/
EXPOSE 8088

# ENVIRONMENT VARIABLES
ENV DB_URL=jdbc:postgresql://postgres-sql-rent-me:5432/rent_me_db
ENV ACTIVE_PROFILE=${PROFILE}
ENV APP_VERSION=${VERSION}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} /app/rent-me-prototype-*.jar
