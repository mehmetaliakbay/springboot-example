FROM openjdk:17
EXPOSE 8080
ADD target/springboot-example.jar springboot-example.jar
ENTRYPOINT ["java", "-jar", "/springboot-example.jar"]
