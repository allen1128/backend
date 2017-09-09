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
* [RabbitMQ](https://www.rabbitmq.com/)

Running
-------

Make sure Java >= 8, [Maven](http://maven.apache.org/) >= 3.0, Mysql >=5.7.18, Docker >=17.06.1-ce are installed.

git clone https://github.com/allen1128/backend/
cd backend/online-food-order
mvn clean install
1. start rabbimq server
docker-compose up

2. start the eureka server
java -jar framework/eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar

3. start hystrix dashboard
java -jar framework/hystrix-dashboard/target/eureka-server-0.0.1-SNAPSHOT.jar

4. start the rest of services.
java -jar payment-service/target/payment-service-0.0.1-SNAPSHOT.jar
java -jar restaurant-service/target/restaurant-service-0.0.1-SNAPSHOT.jar
java -jar shopping-cart-service/target/shopping-cart-service-0.0.1-SNAPSHOT.jar

License
-------

[The Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)
