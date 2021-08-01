# REST-investment

## Create Database Docker

Create command.

```sh
docker run --name rest_mysql -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql:8
```
Wait docker to fully initialize before using it.

See log progress.
```sh
docker logs -f rest_mysql
```

Enter in the database.
```sh
mysql -h 127.0.0.1 -P 3306 -uroot -proot
```

Start and Stop commands.
```sh
docker stop rest_mysql
docker start rest_mysql
```

## Create Stock Manager Docker (Given code)

Create command.

```sh
docker container run --name rest_stock_manager -p 8080:8080 -d lucasvilela/stock-manager
```

Start and Stop commands.

```sh
docker stop rest_stock_manager
docker start rest_stock_manager
```

## Create Quote Manager Docker

Run command below to build and run application Docker.
```sh
./run_script.sh -b
```

Run command below to only run the last created application Docker.
```sh
./run_script.sh
```

# Examples of possible Calls

## REST Application (Quote Manager)

List All Quotes:
```sh
curl --request GET \
    --url http://localhost:8081/quote/all
```

List One Quote:
```sh
curl --request GET \
    --url http://localhost:8081/quote/PETR4
```

Add Valid Quote:
```sh
curl --request POST \
    --url http://172.17.0.4:8081/quote/add \
    --header 'Content-Type: application/json' \
    --data '{
        "stockId": "PETR4",
        "date": "2021-08-09",
        "price": 35.0
    }'
```

Add Invalid Quote:
```sh
curl --request POST \
    --url http://172.17.0.4:8081/quote/add \
    --header 'Content-Type: application/json' \
    --data '{
        "stockId": "INVALID",
        "date": "2021-08-08",
        "price": 35.0
    }'
```

Delete Stocks Cache:
```sh
curl --request DELETE \
    --url http://localhost:8081/stockcache
```

## Stock Manager

List Stocks:
```sh
curl --request GET \
    --url http://localhost:8080/stock
```

Add Stock:
```sh
curl --request POST \
    --url http://localhost:8080/stock \
    --header 'Content-Type: application/json' \
    --data '{
    	"id": "petr5",
    	"description": "test petr"
    }'
```

Register for Notifications:
```sh
curl --request POST \
    --url http://localhost:8080/notification \
    --header 'Content-Type: application/json' \
    --data '{
    	"host": "localhost",
    	"port": 8081
    }'
```

# Todo

Things that I couldn't finish before the due date.
- Endpoint tests for QuoteController add quote.
- Tidy code with comments and remove unused imports.

# Links
Read between 2021-07-23 and 2021-08-01

Spring initial guides:
- https://spring.io/guides/gs/rest-service/
- https://spring.io/guides/gs/accessing-data-mysql/
- https://spring.io/guides/gs/consuming-rest/
- https://www.baeldung.com/spring-bean


Database:
- https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
- https://www.baeldung.com/spring-component-repository-service
- https://www.baeldung.com/hibernate-persisting-maps


Consuming REST:
- https://www.baeldung.com/spring-rest-template-list
- https://spring.io/guides/tutorials/rest/
- https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
- https://www.baeldung.com/spring-resttemplate-post-json
- https://www.baeldung.com/java-org-json


Cache:
- https://code.google.com/archive/p/ehcache-spring-annotations/wikis/UsingCacheable.wiki
- https://www.baeldung.com/spring-boot-evict-cache


Run code on startup:
- https://www.baeldung.com/running-setup-logic-on-startup-in-spring


Tests:
- https://reflectoring.io/unit-testing-spring-boot/
- https://javadoc.io/static/org.mockito/mockito-core/3.11.2/org/mockito/Mockito.html#14
- https://tkstone.blog/2019/03/05/mockito-thenreturn-vs-thenanswer/
- https://blog.trifork.com/2012/12/11/properly-testing-spring-mvc-controllers/

Shell Scripts:
- https://www.redhat.com/sysadmin/arguments-options-bash-scripts