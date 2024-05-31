DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255),
    created_at TIMESTAMP
);

INSERT INTO URLS (name, created_at) VALUES
('example', '2024-05-27 10:42:04'),
('another', '2024-01-26 16:00:34'),
('onemore', '2023-03-25 21:21:04');
