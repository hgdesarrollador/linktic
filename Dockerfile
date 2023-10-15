#docker build -t producto .
#docker run -p 8082:8090 producto
# build

FROM maven:3-eclipse-temurin-17

WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn dependency:copy-dependencies

COPY . .

RUN mvn install

#imagen base
FROM eclipse-temurin:17-jdk-alpine

#crea un volumen que se llama tmp
VOLUME /tmp

#define un argumento para la imagen
ARG JAR_FILE

#copiar un archivo de la maquina a la imagen
COPY --from=0 /usr/src/app/target/*.jar app.jar

#Comando que se va a ejecutar cuando arranque el contenerdor
ENTRYPOINT ["java","-jar","/app.jar"]