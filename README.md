# catalog
Files catalog for channels

## Pre requirement

* run docker PostgresSQL
```bash
docker run -d --name rso1920-pg-catalog-api --network rso1920 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=catalog -p 5435:5432 postgres:12 
```

* connect to PostgresSQL docker DB:

```bash
docker exec -it rso1920-pg-catalog-api psql -h 127.0.0.1 -U postgres 
```

## API

* [GET] files on channel:
```
http://<your_ip>:8088/v1/catalog/channel/<channel_id>
```

* [GET] user files:
```
http://<your_ip>:8088/v1/catalog/user/<user_id>
```

* [POST] create new file metadata
```
http://<your_ip>:8088/v1/file

REQUEST BODY:
{
	"filePath": "<file_path>",
	"fileName": "<file_name>",
	"fileType": "<file_type>",
	"userId": <user_id>,
	"channelId": <channel_id>
}
```

* [DELETE] file:
```
http://<your_ip>:8088/v1/file/<file_id>
```

## Docker

```$xslt
docker build -t rso1920/catalog:1.0.0-SNAPSHOT-19102019 .
```

```$xslt
docker run -d --name rso1920-catalog-api --network rso1920 -e KUMULUZEE_CONFIG_ETCD_HOSTS=http://etcd:2379 -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://rso1920-pg-catalog-api:5432/catalog  -p 8088:8088 rso1920/catalog:latest
```