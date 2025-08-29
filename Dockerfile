# Etapa 1: Construcción con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiamos el pom.xml y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y construimos el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para correr el JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiamos el JAR generado en la etapa de build
COPY --from=builder /app/target/CibernetBackendJava-0.0.1-SNAPSHOT.jar app.jar

# Puerto que expone la aplicación
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
