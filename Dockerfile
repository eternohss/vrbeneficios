FROM openjdk:21-jdk

WORKDIR /app

COPY target/cartao-0.0.1.jar cartao-0.0.1.jar

CMD ["java", "-jar", "cartao-0.0.1.jar"]
