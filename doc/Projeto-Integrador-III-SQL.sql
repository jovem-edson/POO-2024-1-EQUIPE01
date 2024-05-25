DROP DATABASE senac;
CREATE DATABASE senac;
use senac;

-- Tabela principal
CREATE TABLE midia (
    idMidia INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    classIndicativa VARCHAR(50) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    qtdEstoque INT NOT NULL,
	capa VARCHAR(255) NOT NULL,
    tipo ENUM('Jogo', 'Filme', 'Album'),
    UNIQUE (idMidia, tipo) -- Garante que o ID é único em conjunto com o tipo
);

-- Tabela para jogos
CREATE TABLE jogo (
    idJogo INT PRIMARY KEY,
    isOnline BOOLEAN NOT NULL,
    FOREIGN KEY (idJogo) REFERENCES midia(idMidia)
);

-- Tabela para filmes
CREATE TABLE filme (
    idFilme INT PRIMARY KEY,
	diretor VARCHAR(50) NOT NULL,
    duracao TIME NOT NULL,
    elenco TEXT NOT NULL,
    FOREIGN KEY (idFilme) REFERENCES midia(idMidia)
);

-- Tabela para álbuns
CREATE TABLE album (
    idAlbum INT PRIMARY KEY,
	anoLancamento INT NOT NULL,
    artista VARCHAR(100),
	musica TEXT NOT NULL,
    FOREIGN KEY (idAlbum) REFERENCES midia(idMidia)
);


-- Inserindo um registro de jogo
INSERT INTO midia (nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo) VALUES ('The Witcher 3', 1.90, "18 Anos", "RPG", 4, "urlCapa", 'Jogo');
SET @last_id = LAST_INSERT_ID();
INSERT INTO jogo (idJogo, isOnline) VALUES (@last_id, true);

-- Consultando a tabela de jogos
/*
SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, j.isOnline, m.tipo
FROM midia m
INNER JOIN jogo j ON m.idMidia = j.idJogo;
*/

-- Inserindo um registro de filme
INSERT INTO midia (nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo)
VALUES ('Título do Filme', 10.99, 'Livre', 'Ação', 100, 'caminho/para/imagem.jpg', 'Filme');

SET @last_id = LAST_INSERT_ID();

INSERT INTO filme (idFilme, diretor, duracao, elenco)
VALUES (@last_id, 'Nome do Diretor', '02:30:00', 'Ator 1, Atriz 1, Ator 2');

-- Consultando a tabela de filmes

SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, f.diretor, f.duracao, f.elenco, m.tipo
FROM midia m
INNER JOIN filme f ON m.idMidia = f.idFilme;


-- Inserindo um registro de álbum
INSERT INTO midia (nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo)
VALUES ('Título do Álbum', 15.99, 'Livre', 'Pop', 50, 'caminho/para/imagem.jpg', 'Album');

SET @last_id = LAST_INSERT_ID();

INSERT INTO album (idAlbum, anoLancamento, artista, musicas)
VALUES (@last_id, 2024, 'Nome do Artista', 'Música 1, Música 2, Música 3');

-- Consultando a tabela de albuns

SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, a.anoLancamento, a.artista, a.musica, m.tipo
FROM midia m
INNER JOIN album a ON m.idMidia = a.idAlbum;


-- Consultando todas as midias
SELECT * FROM midia;
SELECT * FROM album;

DELETE FROM album WHERE idMidia = 3;

CREATE TABLE usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
cpf VARCHAR(15) NOT NULL,
	email VARCHAR(100) NOT NULL,
    	senha VARCHAR(20) NOT NULL,
    tipo ENUM('Vendedor', 'Cliente'),
    UNIQUE (idUsuario, tipo) -- Garante que o ID é único em conjunto com o tipo
);

CREATE TABLE vendedor (
    idVendedor INT PRIMARY KEY,
    FOREIGN KEY (idVendedor) REFERENCES usuario(idUsuario)
);

CREATE TABLE cliente (
    idCliente INT PRIMARY KEY,
    FOREIGN KEY (idCliente) REFERENCES usuario(idUsuario)
);


-- INSERE USUARIO
INSERT INTO usuario (nome, cpf, email, senha, tipo)
VALUES ('Jose Elias', '144.531.229/02', 'joseeelias5589@gmail.com', 'senha1234', 'Vendedor');

SET @last_id = LAST_INSERT_ID();

INSERT INTO vendedor (idVendedor)
VALUES (@last_id);

SELECT * FROM USUARIO;

-- BUSCA USUARIOS
SELECT * FROM usuario WHERE email like 'joseeelias5589@gmail.com' AND senha like 'senha1234';

-- BUSCA VENDEDORES
SELECT u.idUsuario, u.nome, u.cpf, u.email, u.senha, u.tipo
FROM usuario u
INNER JOIN vendedor v ON u.idUsuario = v.idVendedor;

-- BUSCA USUARIOS
SELECT u.idUsuario, u.nome, u.cpf, u.email, u.senha, u.tipo
FROM usuario u
INNER JOIN cliente c ON u.idUsuario = c.idCliente;

SELECT * FROM vendedor;




















