# NTS  

## Arquitetura do projeto
A arquitetura do software foi construido conforme a arquitetura de microsserviços.  
  
![Architecture](https://github.com/gabrielkirsten/nts/blob/master/docs-assets/nts-architecture.png?raw=true)  
  
Os serviços foram baseados no pattern [Database per Service](https://microservices.io/patterns/data/database-per-service.html). Para resolver o problema de sincronização gerado pelos bancos foi utilizado o pattern [Choreography-based Saga](https://microservices.io/patterns/data/saga.html).  
  
Para a comunicação dos eventos dos serviços foi utilizado o Message Queue [RabbitMQ](https://rabbitmq.com).  
  
### Serviços implementados:  
 - **campaign-service**: serviço relacionado a operações com as campanhas.  
 - **team-service**: serviço relacionado a operações com os times.  
 - **customer-service**: serviço relacionado a operações com os clientes.  
 - **customer-campaign-service**: serviço relacionado a operações com as campanhas dos clientes.  
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
$ docker-composer up --build -d  
```  

O docker já sobe todos os sistemas necessários para o funcionamento, incluindo o banco de dados e o servidor de AMPQ.
  
### Tolerância a falhas  
Para suportar a tolerância a falhas foi utilizado o Circuit Breaker [Hystrix](https://github.com/Netflix/Hystrix). Quando o [Feign](https://github.com/OpenFeign/feign) não consegue realizar a conexão com o servidor, o Fallback do Hystrix trata o erro.   
  
### Links úteis  
- Documentação: http://base_url:8765/service_name/swagger-ui.html (*possivelmente a `base_url` será localhost*)  
    - **customer-service**: http://base_url:8765/customer-service/swagger-ui.html  
    - **customer-campaign-service**: http://base_url:8765/customer-campaign-service/swagger-ui.html  
    - **team-service**: http://base_url:8765/team-service/swagger-ui.html  
    - **campaign-service**: http://base_url:8765/campaign-service/swagger-ui.html  
- Eureka: http://base_url:8761/  
- API Gateway: http://base_url:8765  
  
### Teste de desempenho  
Para o teste de desempenho foi utilizado o [Gatling](https://gatling.io/).  Utilizando 100 requests simultaneos, somente foi realizado no endpoint de cadastro e obtenção de campanha (por ser considerado o mais custoso).
  
    ================================================================================
    ---- Global Information --------------------------------------------------------
    > request count                                        200 (OK=200    KO=0     )
    > min response time                                      5 (OK=5      KO=-     )
    > max response time                                  11615 (OK=11615  KO=-     )
    > mean response time                                  2288 (OK=2288   KO=-     )
    > std deviation                                       3291 (OK=3291   KO=-     )
    > response time 50th percentile                        164 (OK=164    KO=-     )
    > response time 75th percentile                       3731 (OK=3731   KO=-     )
    > response time 95th percentile                       9700 (OK=9700   KO=-     )
    > response time 99th percentile                      11214 (OK=11214  KO=-     )
    > mean requests/sec                                 15.385 (OK=15.385 KO=-     )
    ---- Response Time Distribution ------------------------------------------------
    > t < 800 ms                                           111 ( 56%)
    > 800 ms < t < 1200 ms                                   8 (  4%)
    > t > 1200 ms                                           81 ( 41%)
    > failed                                                 0 (  0%)
    ================================================================================


### Banco de dados  
Inicialmente foi utilizado o H2. Porém após os testes de desempenho, testando para 100 conexões simultâneas foi constatado que não é possivel a sua utilização para essa quantidade de acessos. Então o banco de dados foi alterado para o [MongoDB](https://www.mongodb.com/).


### Cache
Em alguns endpoints trechos do código foi implementado o Cache com Spring para que melhore o tempo de resposta. 

### Testes

#### Unitários
Foram implementados testes unitários nas regras de negócios referentes ao cadastro de novas campanhas, utilizando o [Mockito](https://site.mockito.org/) para realizar os mocks das classes dependentes. 

#### Integração
Os testes de integração foram implementados na API de Campanhas, também utilizando o Mockito para realizar os mocks das classes dependentes.

### Problemas de concorrência
Foram indentificados problemas de concorrência para acessos simultâneos na API de Campanhas, especificamente o endpoint de cadastro de campanha, o qual conta com uma regra especifica que impede que campanhas terminem no mesmo dia. 

Para solicionar o problema de concorrência, foi utilizado o sistema de Locks de maneira em que o método que aplica a regra seja executada de maneira atômica, através do `syncronized` do Java. 


