This is for the first phase of the project. It includes:

1. Architecture diagram.
2. API design
3. Project skeleton
4. Working infrastructure

some explanation for the project design: the project uses the spring cloud infrastructure for implementating the microservices. Eureka 
server is used for service registration and discovery. Zuul for API gateway. Restaurant Discovery service is an independent services.
Food Order Service uses RabbitMQ to communicate with the Payment service.


