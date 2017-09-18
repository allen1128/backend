Build Keyword Reverse Index with MemCached
===========================================

Preparation
------------

1. git clone https://github.com/allen1128/backend
2. import project backend/big-data/index-builder to intellij
3. start mysql service
4. execute resource/DB_TABLE.sql to generate table "ad" in MySQL Workbench.
5. start rabbitMQ
6. start the other services, such as crawler_feeder and WebCrawler.


Run
----
1. build project and run the jar file.
2. check the mysql workbench in schema "searchads" for generated ad records.



