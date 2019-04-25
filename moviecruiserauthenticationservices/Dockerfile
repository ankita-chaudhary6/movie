FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER=root12
ENV MYSQL_PASSWORD=root12
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
EXPOSE 2244:2244
ADD ./target/moviecruiserauthenticationservices-0.0.1-SNAPSHOT.jar /usr/src/moviecruiserauthenticationservices-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","moviecruiserauthenticationservices-0.0.1-SNAPSHOT.jar"]

