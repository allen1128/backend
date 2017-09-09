On-line food order system
=========================

An example backend application based on microservices architecture that use spring boot, spring cloud, Hibernate/JPA technologies.

About
-----

The project aims to demonstrate the Java implementation of a simple REST interface. The following topics are covered.

1. Architecture diagram.
2. API design
3. Working microservice infrastructure
4. REST service that allows user to order food online.

Technologies
------------
* [Hibernate](http://hibernate.org/)
* [Spring Cloud](http://projects.spring.io/spring-cloud/)
* [Spring Boot](http://projects.spring.io/spring-boot/)

Running
-------

Make sure Java >= 8, [Maven](http://maven.apache.org/) >= 3.0, Mysql >=5.7.18, Docker >=17.06.1-ce are installed.
git clone https://github.com/allen1128/backend/online-food-order
cd online-food-order
mvn clean install

1. start the eureka server
java -jar framework/eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar

2. start hystrix dashboard
java -jar framework/hystrix-dashboard/target/eureka-server-0.0.1-SNAPSHOT.jar



some explanation for the project design: the project uses the spring cloud infrastructure for implementating the microservices. Eureka 
server is used for service registration and discovery. Zuul for API gateway. Restaurant Discovery service is an independent services.
Food Order Service uses RabbitMQ to communicate with the Payment service. Mysql is used by each service for data persistency.


