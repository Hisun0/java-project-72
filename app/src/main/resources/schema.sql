DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
    name VARCHAR(255),
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE url_checks (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    url_id BIGINT REFERENCES urls(id) NOT NULL,
    status_code INT,
    h1 VARCHAR(255),
    title varchar(255),
    description text,
    created_at TIMESTAMP NOT NULL
);
