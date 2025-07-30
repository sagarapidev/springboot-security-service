# Docker Commands for MySQL Security Service

## List running containers

```
docker ps
```

## Run MySQL container

```
docker run --name security-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=sbs -p 3306:3306 -d mysql:8.0
```

## Check MySQL server status inside the container

```
docker exec security-service mysqladmin ping -h localhost -u root -password
```


