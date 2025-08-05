FROM eclipse-temurin:17
LABEL maintainer="leofigorelli@gmail.com"
WORKDIR /CommerceMailApp
COPY target/CommerceMail-0.0.1-SNAPSHOT.jar /CommerceMailApp/CommerceMail.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "CommerceMail.jar"]