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