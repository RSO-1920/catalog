FROM openjdk:11.0.4-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./catalog-api/target/catalog-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8088

CMD ["java", "-jar", "catalog-api-1.0.0-SNAPSHOT.jar"]
