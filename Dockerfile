FROM java:8-jre
ADD ./target/demo-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]