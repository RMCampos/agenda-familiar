CREATE DATABASE agendafamiliar;

CREATE TABLE usuarios (
    usuario_id SERIAL PRIMARY KEY,
    nome       VARCHAR(50) NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    senha      VARCHAR(255) NOT NULL
);

CREATE INDEX idx_usuarios_email ON usuarios (email);

CREATE TABLE authorities (
    email  VARCHAR(100) NOT NULL REFERENCES usuarios (email),
    authority VARCHAR(50) NOT NULL
);

CREATE TABLE calendarios (
    calendario_id SERIAL PRIMARY KEY,
    usuario_id    INTEGER NOT NULL REFERENCES usuarios (usuario_id),
    nome          VARCHAR(30) NOT NULL
);

CREATE TABLE datas (
    data_id       SERIAL PRIMARY KEY,
    usuario_id    INTEGER NOT NULL REFERENCES usuarios (usuario_id),
    calendario_id INTEGER NOT NULL REFERENCES calendarios (calendario_id),
    dia           INTEGER NOT NULL,
    mes           INTEGER NOT NULL,
    ano           INTEGER NOT NULL,
    descricao     VARCHAR(30) NOT NULL
);
