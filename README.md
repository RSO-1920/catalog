# catalog
Files catalog for channels

## Prerequisites

* run docker PostgressSQL
```bash
docker run -d --name pg-catalog-api -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=image-metadata -p 5435:5432 postgres:12
```

* connect to PostgressSQL docker DB:

```bash
docker exec -it pg-catalog-api psql -h 127.0.0.1 -U postgres 
```

## API

* [GET] files on channel:
```
http://<your_ip>:8088/v1/file/channel/<channel_id>
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

## Docker

```$xslt
docker build -t rso1920/catalog:1.0.0-SNAPSHOT-19102019 .
```

```$xslt
docker run -d --name rso1920-catalog-api -p 8088:8088 rso1920/catalog:1.0.0-SNAPSHOT-19102019
```