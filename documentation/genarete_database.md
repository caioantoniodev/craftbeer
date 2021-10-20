# Como o Hibernate não funciona com Postgres, fazemos do jeito "raiz" hahaha

## Primeiro passo
```postgresql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp"
```

```postgresql
CREATE SCHEMA craftbeer
```
## Segundo passo

```postgresql
CREATE TABLE craftbeers.beers
(
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR NOT NULL,
    alcohol_content INTEGER NOT NULL,
    price DECIMAL NOT NULL
);

CREATE UNIQUE INDEX beers_id_uindex
ON craftbeers.beers (id);

ALTER TABLE craftbeers.beers
ADD CONSTRAINT beers_pk
PRIMARY KEY (id);
```