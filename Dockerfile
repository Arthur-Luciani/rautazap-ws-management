# Estágio 1: Build (Opcional, se você não quiser construir o JAR localmente)
# FROM maven:3.8.5-openjdk-21 AS build
# COPY . .
# RUN mvn clean package -DskipTests

# Estágio 2: Imagem Final
# Usamos uma imagem base leve, apenas com o Java Runtime
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR da pasta target (gerado pelo 'mvn package') para o container
# Certifique-se de que o nome do JAR corresponde ao gerado pelo seu pom.xml
COPY target/ws-management-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação Spring Boot usa
EXPOSE 8080

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java","-jar","app.jar"]