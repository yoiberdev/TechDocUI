# Etapa 1: Construcción con Gradle
FROM gradle:8.5-jdk21 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración primero para aprovechar cache de dependencias
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./

# Descarga de dependencias (útil para cache en builds posteriores)
RUN ./gradlew build --no-daemon || return 0

# Copia el código fuente del proyecto
COPY src ./src

# Compila el proyecto y genera el .jar
RUN ./gradlew clean build --no-daemon

# Etapa 2: Imagen final para ejecutar el JAR
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia el JAR compilado desde el build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer puerto (ajústalo si usas otro)
EXPOSE 8080

# Comando por defecto al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]