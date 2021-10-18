## First step
```postgresql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp"
```

```postgresql
CREATE SCHEMA craftbeer
```
## Second step

```postgresql
CREATE TABLE craftbeer.craftbeers
(
id UUID NOT NULL DEFAULT gen_random_uuid(),
name varchar NOT NULL
);

CREATE UNIQUE INDEX craftbeers_id_uindex
ON craftbeer.craftbeers (id);

ALTER TABLE craftbeer.craftbeers
ADD CONSTRAINT craftbeers_pk
PRIMARY KEY (id);
```