DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
    name VARCHAR(255),
    created_at TIMESTAMP
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

INSERT INTO urls (name, created_at) VALUES
('example', '2024-05-27 10:42:04'),
('another', '2024-01-26 16:00:34'),
('onemore', '2023-03-25 21:21:04');

INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) VALUES
(1, 200, 'Header 1', 'Title', 'Description', '2023-03-25 23:21:04'),
(1, 200, 'Header 1', 'Title', 'Description', '2023-03-25 23:25:04'),
(2, 200, 'Another Header 1', 'Another Title', 'Another Description', '2024-01-29 15:25:04');
