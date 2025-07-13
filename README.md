# RautaZAP

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Arquitetura](https://img.shields.io/badge/Arquitetura-Hexagonal%20%26%20DDD-blueviolet)
![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?logo=kubernetes)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker)

### Visão Geral

O **RautaZAP** é um backend de alta performance para o gerenciamento de comunicação em tempo real via WebSockets. O projeto foi construído como um estudo aprofundado de arquiteturas de software modernas, aplicando estritamente os princípios da **Arquitetura Hexagonal (Ports & Adapters)** e do **Domain-Driven Design (DDD)** para garantir um sistema desacoplado, testável e evoluível.

A aplicação utiliza uma arquitetura orientada a eventos com Apache Kafka para gerenciar a comunicação e a persistência de mensagens, garantindo resiliência e escalabilidade.

### Arquitetura

O núcleo do projeto segue a **Arquitetura Hexagonal**, que isola a lógica de negócio das tecnologias de infraestrutura.

* **`domain`**: Contém as regras de negócio puras e o modelo de domínio rico (Entidades com comportamento), sem dependências externas.
* **`application`**: Orquestra os casos de uso (Use Cases) e define as **Portas** (interfaces) que representam a API do núcleo do negócio.
* **`infrastructure`**: Contém os **Adaptadores** que implementam as portas, conectando o núcleo de negócio a tecnologias como Spring Boot, Kafka, MongoDB e Redis.


### Tecnologias Utilizadas

* **Backend**: Java 21, Spring Boot 3
* **Arquitetura**: Domain-Driven Design, Arquitetura Hexagonal
* **Mensageria**: Apache Kafka, ActiveMQ Artemis
* **Banco de Dados**: MongoDB
* **Cache**: Redis
* **Comunicação**: WebSocket com STOMP
* **Conteinerização & Orquestração**: Docker, Kubernetes (Minikube)
* **Gerenciamento de Pacotes K8s**: Helm
* **Monitoramento**: Prometheus & Grafana

### Deploy com Minikube

Este guia assume que você tem o **Docker**, **Minikube** e **Helm** instalados na sua máquina.

#### 1. Preparar o Ambiente

```bash
# Inicie o Minikube com recursos suficientes
minikube start --cpus=6 --memory=12288

# Habilite o metrics-server (necessário para o HPA)
minikube addons enable metrics-server

@Habilita o Dashboard do Minikube
minikube dashboard
```

#### 2. Instalar Dependências via Helm

```bash
# Adicionar o repositório da Bitnami
helm repo add bitnami [https://charts.bitnami.com/bitnami](https://charts.bitnami.com/bitnami)
helm repo update

# Instalar MongoDB
helm install mongodb bitnami/mongodb --set auth.enabled=false

# Instalar Redis
helm install redis bitnami/redis --set auth.enabled=false --set architecture=standalone

# Instalar Kafka
helm install kafka bitnami/kafka --set auth.enabled=false --set kraft.enabled=true --set listeners.client.protocol=PLAINTEXT --set controller.replicaCount=1
```

#### 3. Fazer o Deploy da Aplicação e do Artemis

Todos os manifestos necessários (`.yaml`) estão na pasta `/k8s` do projeto.

```bash
# Empacotar a aplicação Java
./mvnw clean package

# Apontar o seu terminal para o Docker do Minikube
# Para PowerShell:
minikube -p minikube docker-env | Invoke-Expression
# Para Linux/macOS:
# eval $(minikube -p minikube docker-env)

# Construir a imagem Docker da aplicação
docker build -t rautazap:latest .

# Aplicar todos os manifestos (incluindo Artemis e a aplicação RautaZAP)
kubectl apply -f k8s/
```

#### 4. Acessar a Aplicação

Para expor o serviço para fora do cluster, é necessário criar um túnel.

```bash
# Em um terminal separado, deixe este comando executando:
minikube tunnel
```

O serviço estará agora acessível através do IP externo fornecido pelo comando `kubectl get services`.

### Monitoramento com Prometheus & Grafana

A stack de monitoramento pode ser instalada via Helm para visualizar métricas da aplicação e da infraestrutura.

#### 1. Instalar a Stack de Monitoramento

```bash
# Adicionar o repositório da comunidade Prometheus
helm repo add prometheus-community [https://prometheus-community.github.io/helm-charts](https://prometheus-community.github.io/helm-charts)
helm repo update

# Instalar a stack
helm install prometheus prometheus-community/kube-prometheus-stack
```

#### 2. Acessar o Grafana

```bash
# Criar um túnel de porta para o serviço do Grafana
kubectl port-forward svc/prometheus-grafana 3000:80
```

Acesse `http://localhost:3000` no seu navegador. O login é `admin` e a senha pode ser obtida com o comando `kubectl get secret prometheus-grafana -o jsonpath="{.data.admin-password}" | base64 --decode` (ajuste para PowerShell se necessário).

No Grafana, você pode explorar os dashboards pré-configurados ou importar dashboards da comunidade (ex: ID `7589` para o Kafka) para visualizar as métricas.
