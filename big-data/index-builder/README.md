Build Keyword Reverse Index with MemCached
===========================================

Preparation
------------

1. git clone https://github.com/allen1128/backend
2. import project backend/big-data/index-builder to intellij
3. start Memcached service
4. start mysql service
5. execute resources/DB_TABLE.sql to generate table "ad" in MySQL Workbench.
6. start rabbitMQ and create a queue called "q_product"
7. push a message with the data in resources/payload.json



Run
----
1. run application.java
2. check the mysql workbench in schema "searchads" for generated ad records.

