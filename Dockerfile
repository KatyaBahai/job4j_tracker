FROM maven:3.9.9-eclipse-temurin-17
RUN mkdir job4j_tracker
WORKDIR job4j_tracker
COPY . .
RUN mvn package -Dmaven.test.skip=true
CMD ["java", "-jar", "/job4j_tracker/target/tracker.jar"]