# Etapa 1: Construcción
FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Copia los archivos necesarios para construir el JAR
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

# Asegura permisos de ejecución
RUN chmod +x gradlew

# Construye el archivo .jar
RUN ./gradlew bootJar --no-daemon

# Etapa 2: Imagen final, ligera
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia el jar compilado desde la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Expone el puerto de la app
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "app.jar"]
