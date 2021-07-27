# REST-investment

The application to be developed will use two services that run on docker containers.

## Create Database Docker

Create command.

```sh
docker run --name rest_mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql:8
```

Start and Stop commands.

```sh
docker stop rest_mysql
docker start rest_mysql
```

## Create Producer Docker (Given code)

Create command.

```sh
docker container run --name rest_stock_manager -p 8080:8080 -d lucasvilela/stock-manager
```

Start and Stop commands.

```sh
docker stop rest_stock_manager
docker start rest_stock_manager
```

## Create / Run Consumer Docker

Script creates .jar, build and run application Docker.
```sh
./run_script.sh
```

