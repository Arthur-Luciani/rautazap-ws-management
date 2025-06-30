# RAUTAZAP-WS-MANAGEMENT

Sistema de gerenciamento de WebSocket para integração de mensagens em tempo real, utilizando Kafka, MongoDB, Redis e ActiveMQ Artemis. Este projeto foi desenvolvido para facilitar a comunicação entre diferentes serviços e usuários, garantindo escalabilidade e performance.

## Pré-requisitos

- Docker e Docker Compose instalados
- Java 17+
- Maven 3.8+

## Como executar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/rautazap-ws-management.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd rautazap-ws-management
   ```
3. Inicie os serviços necessários utilizando os comandos Docker abaixo.
4. Execute a aplicação Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```

## Comandos docker

> Execute cada comando separadamente para subir os serviços necessários.

### Kafka
```powershell
docker run -p 9092:9092 apache/kafka:4.0.0
```

### MongoDB
```powershell
docker run --name mongodb_local -d \
  -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=mongoadmin \
  -e MONGO_INITDB_ROOT_PASSWORD=secret \
  -v mongo_data:/data/db \
  mongo:latest
```

### Redis
```powershell
docker run --name redis_local -d \
  -p 6379:6379 \
  -v redis_data:/data \
  redis:latest
```

### ActiveMQ Artemis
```powershell
docker run -d --name mq \
  -e ARTEMIS_USERNAME=artemis \
  -e ARTEMIS_PASSWORD=artemis \
  -p 8161:8161 \
  -p 61616:61616 \
  vromero/activemq-artemis:latest
```

## Estrutura do Projeto

- `src/main/java`: Código-fonte principal (domínio, adaptadores, infraestrutura)
- `src/main/resources`: Arquivos de configuração e recursos estáticos
- `src/test/java`: Testes automatizados
