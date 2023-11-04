FROM openjdk:17
VOLUME /tmp
EXPOSE 8081
ADD /target/equipment-0.0.1-SNAPSHOT.jar equipment.jar
ENTRYPOINT ["java", "-jar", "equipment.jar"]
