FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Copiamos primero los archivos necesarios para cachear dependencias
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./
RUN chmod +x gradlew

# (opcional, descarga deps)
RUN ./gradlew build --no-daemon || true

# Copiamos el resto del código fuente
COPY src ./src

# Compilamos
RUN ./gradlew clean build --no-daemon
