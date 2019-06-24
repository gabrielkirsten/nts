# NTS

## Arquitetura
A arquitetura do software foi construido conforme a arquitetura de microsserviços.

![Architecture](https://github.com/gabrielkirsten/nts/blob/master/docs-assets/nts-architecture.png?raw=true)

Os serviços foram baseados no pattern [Database per Service](https://microservices.io/patterns/data/database-per-service.html). Para resolver o problema de sincronização gerado pelos bancos foi utilizado o pattern [Choreography-based Saga](https://microservices.io/patterns/data/saga.html).

Para a comunicação dos eventos dos serviços foi utilizado o Message Queue [RabbitMQ](https://rabbitmq.com).

### Serviços implementados:
  - **campaign-service**: serviço relacionado as campanhas.
  - **team-service**: serviço relacionado as times.
  - **customer-service**: serviço que armazena as campanhas.
  - **customer-campaign-service**: serviço relacionado as campanhas dos clientes.
  - **naming-service**: service discovery [Netflix Eureka](https://github.com/Netflix/eureka).
  - **api-gateway-service**: api gateway [Netflix Zuul](https://github.com/Netflix/zuul).
  
### Documentação dos serviços

Cada serviço conta com a documentação correspondente implementada no [Swagger](https://swagger.io/). 

### Build e Deploy

O projeto é contruido com o gerenciador de dependências [Maven](https://maven.apache.org/). 

#### Build
Execute os goals `clean` e `install` do maven para que seja testado, baixadas as dependências e gerado o executavel `.jar` do projeto. O Projeto foi preparado para funcionar em módulos, sendo que somente uma execução desse comando na raiz do projeto seja necessária.

```bash
$ mvn clean install
```

#### Deploy 
Para facilitar o deploy do projeto está preparado para funcionar com o Docker. Cada projeto utiliza um container e os projetos são orquestrados com o [Docker compose](https://docs.docker.com/compose/).

Utilizando o Docker Composer:

```bash 
$ docker-composer up --build
```

### Tolerância a falhas
Para suportar a tolerância a falhas foi utilizado o Circuit Breaker [Hystrix](https://github.com/Netflix/Hystrix). Quando o [Feign](https://github.com/OpenFeign/feign) não consegue realizar a conexão com o servidor, o Hystrix trata o erro. 

### Links úteis
- Documentação: http://base_url:8765/service_name/swagger-ui.html (*possivelmente a `base_url` será localhost*)
    - **customer-service**: http://base_url:8765/customer-service/swagger-ui.html
    - **customer-campaign-service**: http://base_url:8765/customer-campaign-service/swagger-ui.html
    - **team-service**: http://base_url:8765/team-service/swagger-ui.html
    - **campaign-service**: http://base_url:8765/campaign-service/swagger-ui.html
- Eureka: http://base_url:8761/
- API Gateway: http://base_url:8765

### Teste de desempenho
Para o teste de desempenho foi utilizado o [Gatling](https://gatling.io/).

### Banco de dados
Inicialmente foi utilizado o H2. Porém após os testes de desempenho, testando para 100 conexões simultâneas foi constatado que não é possivel a sua utilização para essa quantidade de acessos. Então o banco de dados foi alterado para o [MongoDB](https://www.mongodb.com/).




