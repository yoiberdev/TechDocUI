# Etapa 1: Construcción con Gradle
FROM gradle:8.5-jdk21 AS build

# Copiar el código fuente
WORKDIR /app

# Copiamos los archivos gradle necesarios primero para aprovechar cache
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
COPY gradlew ./

# Descargar dependencias (opcional, útil para caché)
RUN ./gradlew dependencies

# Copiar el resto del código
COPY src ./src

# Compilar el proyecto y generar el jar
RUN ./gradlew clean bootJar --no-daemon

# Etapa 2: Imagen final para ejecutar
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar el jar desde la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto que usa tu app
EXPOSE 8080

# Comando para ejecutar el jar
CMD ["java", "-jar", "app.jar"]
