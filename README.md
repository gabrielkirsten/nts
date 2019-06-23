# NTS

## Arquitetura
A arquitetura do software foi construido conforme a arquitetura de microsserviços.

![Architecture](https://github.com/gabrielkirsten/nts/blob/master/docs-assets/nts-architecture.png?raw=true)

Os serviços foram baseados no pattern [Database per Service](https://microservices.io/patterns/data/database-per-service.html). Para resolver o problema de sincronização gerado pelos bancos foi utilizado o pattern [Choreography-based Saga](https://microservices.io/patterns/data/saga.html).

Para a comunicação dos eventos dos serviços foi utilizado o Message Queue [RabbitMQ](https://rabbitmq.com)

### Serviços implementados:
  - **campaign-service**: serviço relacionado as campanhas.
  - **team-service**: serviço relacionado as times.
  - **customer-service**: serviço que armazena as campanhas.
  - **customer-campaign-service**: serviço relacionado as campanhas dos clientes.
  - **naming-service**: service discovery [Netflix Eureka](https://github.com/Netflix/eureka).
  - **api-gateway-service**: api gateway [Netflix Zuul](https://github.com/Netflix/zuul).
