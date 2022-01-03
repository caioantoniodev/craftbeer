# Como o Hibernate não funciona com Postgres, fazemos do jeito "raiz" hahaha

## Primeiro passo
```postgresql
CREATE SCHEMA craftbeer
```

## Segundo passo

```postgresql
CREATE TABLE craftbeer.beers
(
	id SERIAL NOT NULL,
	name VARCHAR(50) NOT NULL,
	price DECIMAL NOT NULL,
	description VARCHAR(100) NOT NULL,
	alcohol_by_volume DECIMAL NOT NULL,
	type VARCHAR(50) NOT NULL
);

CREATE UNIQUE INDEX beers_id_uindex
ON craftbeer.beers (id);

ALTER TABLE craftbeer.beers
ADD CONSTRAINT beers_pk
PRIMARY KEY (id);
```