# Gonçalves Aço API

## ⚙️ Java Setup

Este é um  em Java 21 usando o Maven como gerenciador de dependências. 

## Pré-requisitos

Antes de começar, você precisará ter o seguinte software instalado:

- [JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão 21 ou superior)
- [Apache Maven](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/products/docker-hub/)

## Passo 1: Configuração do Ambiente

### 1.1 Instale o JDK

Para verificar se o JDK está instalado corretamente, execute o comando a seguir no terminal:

```bash
$ java -version
```

### 1.2 Instale o Maven
```bash
$ mvn -v
```

### 2.0 Faça o fork do projeto
```bash
$ git clone https://github.com/vitorzg/goncalvesaco.git
```

### 2.1 Faça o dowload das dependências
```bash
$ mvn clean install
```

### 3.0 Configurar banco de dados de desenvolvimento
Para o Desenvolvimento usaremos o Banco Postgres por um container Docker
```bash
$ docker-compose up -d
```

### 3.1 Verificar o container
```bash
$ docker ps
```

### 4.0 Iniciar o Projeto
```bash
$ mvn spring-boot:run
```