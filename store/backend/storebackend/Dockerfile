FROM rsunix/yourkit-openjdk17
COPY ./target/storebackend-0.0.1-SNAPSHOT.jar /storebackend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/storebackend.jar"]
