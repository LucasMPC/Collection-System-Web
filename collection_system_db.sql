-- 1. Cria o Banco de Dados
CREATE DATABASE collection_system_db;
USE collection_system_db;

-- 2. Tabela usuario
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento VARCHAR(10)
);

-- 3. Tabela desenvolvedora
CREATE TABLE desenvolvedora (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    nome VARCHAR(100) NOT NULL
);

-- 4. Tabela colecao
CREATE TABLE colecao (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    nome VARCHAR(100) NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

-- 5. Tabela jogo
CREATE TABLE jogo (
    id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    nome VARCHAR(100) NOT NULL,
    data_lancamento VARCHAR(10),
    descricao TEXT,
    genero VARCHAR(50),
    tipo_midia VARCHAR(50),
    id_colecao INT NOT NULL,
    id_desenvolvedora INT,
    FOREIGN KEY (id_colecao) REFERENCES colecao(id) ON DELETE CASCADE,
    FOREIGN KEY (id_desenvolvedora) REFERENCES desenvolvedora(id)
);

ALTER TABLE colecao ADD COLUMN icone VARCHAR(50);

INSERT INTO desenvolvedora (nome) VALUES 
('Nintendo'),
('Sony Interactive Entertainment'),
('Xbox Game Studios'),
('Sega'),
('Capcom'),
('Konami'),
('Square Enix'),
('Bandai Namco'),
('Electronic Arts (EA)'),
('Ubisoft'),
('Activision Blizzard'),
('Rockstar Games'),
('Naughty Dog'),
('CD Projekt Red'),
('FromSoftware'),
('Valve'),
('Bethesda Softworks'),
('Santa Monica Studio'),
('Insomniac Games'),
('Supergiant Games'),
('Team Cherry');

CREATE TABLE lista_generos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO lista_generos (nome) VALUES 
('Ação'), ('Aventura'), ('RPG'), ('FPS'), 
('Estratégia'), ('Esportes'), ('Corrida'), 
('Simulação'), ('Terror'), ('Luta'), ('Plataforma'), ('Outros');

CREATE TABLE lista_midias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO lista_midias (nome) VALUES 
('Físico'), 
('Digital');
-- DROP DATABASE collection_system_dbid

-- SELECT * FROM usuario;