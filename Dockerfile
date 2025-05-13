FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Copiar los archivos esenciales para la construcción (incluyendo el wrapper)
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./
RUN chmod +x gradlew

# Copiar el código fuente
COPY src ./src

# Compilar
RUN ./gradlew clean build --no-daemon
