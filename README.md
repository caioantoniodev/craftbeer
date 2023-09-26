<br/>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
<br/>
<br/>

# 🍺 Craftbeer
Essa api com operações de um crud básico para cadastro de cerveja artesanais.
<br/>

## 📚 Resources

<kbd>/beers</kbd>

Recurso que representa uma cerveja

| METHOD     | ENDPOINT         | DESCRIPTION                                      | ESCOPE             |
|------------|------------------|------------------------------------------------- |--------------------|
| **POST**   | `/`              | Criar uma cerveja                                | <kbd>REQUEST</kbd> |
| **DELETE** | `/{id}`          | Remove uma cerveja específica pelo Id            | <kbd>REQUEST</kbd> |
| **GET**    | `/{id}`          | Lista uma cerveja específica pelo Id             | <kbd>REQUEST</kbd> |


<br/>

## 📐 Arquitetura

WIP

seguindo a estrutura de pastas abaixo

```
├── config
├── kafka
│   ├── consumer
│   ├── model
│   └── producer
├── rest
│   ├── model
│   └── controller

```

<br/>

## ⌛️ Serviços

WIP

<br/>

## ⚡ Getting started

Executa o docker compose para subir as imagens necessárias em container docker (docker-compose.yml)

```sh
cd docker-compose && docker-compose up -d
```

<br/>

## ☕ Executar

### Compilar o projeto

```sh
mvn clean install
```

### Executando **local**

```sh
mvn spring-boot:run 

or

java -jar target/WIP
```

### Executando os **testes**
```sh
mvn test
```

### **Swagger**

```
http://localhost:{you-port}/v1/api-docs
```

<br/>
