create database SistemaEstoque
Go 
use SistemaEstoque

Create Table distribuidora (
id		INT		NOT NULl		IDENTITY(2020, 1),
tipoRoupaDist		VARCHAR(30)		NOT NULL,
PRIMARY KEY(id)
)

Create Table filialLoja (
id		INT		NOT NULL		IDENTITY(1000,1),
idDist		INT		NOT NULL
PRIMARY KEY (id)
FOREIGN KEY(idDist) REFERENCES distribuidora(id)
)

Create table estoque (
id		INT		NOT NULL		IDENTITY(10,1),
idFilial		INT		NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (idFilial) REFERENCES filialLoja(id)
)

Create table funcionario (
id		INT		NOT NULL		IDENTITY(100,1),
nome		VARCHAR(100)		NOT NULL,
idEstoque		INT		NOT NULL
PRIMARY KEY (id)
FOREIGN KEY(idEstoque) REFERENCES estoque(id)
)

Create Table roupa (
id		INT		NOT NULL	IDENTITY(1,1),
tipoRoupa		VARCHAR(30)		NOT NULL,
idEstoque		INT		NOT NULL,
marca		VARCHAR(30)		NOT NULL,
tamanho		VARCHAR(3)		NOT NULL,
genero		CHAR(1)		NOT	NULL,
quantidade		INT		NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (idEstoque) REFERENCES estoque(id)
)

Insert into distribuidora(tipoRoupaDist) Values 
('Camisa'), ('Calca'), ('Jaqueta')

Insert into filialLoja(idDist) Values
(2020), (2020), (2022)

Insert into estoque(idFilial) Values
(1000), (1000), (1001)

Insert into funcionario(nome, idEstoque) VALUES
('Joao paulo', 10), ('Lucas', 10), ('Tiago', 11)

Insert into roupa (tipoRoupa, idEstoque, marca, tamanho, genero, quantidade) VALUES
('Camisa', 10, 'Nike', 'G', 'M', 20), ('Camisa', 10, 'Nike', 'P', 'M', 200), ('Calca', 10, 'Nike', 'P', 'F', 120), ('Blusa', 11, 'Adidas', 'P', 'F', 80), ('Outro', 11, 'Adidas', 'M', 'U', 20)


--Consultas SQL

--Total de roupas por gênero em cada distribuidora 
SELECT d.id AS idDistribuidora, r.genero, SUM(r.quantidade) AS totalPorGenero
FROM distribuidora d
INNER JOIN filialLoja f 
ON d.id = f.idDist
INNER JOIN estoque e 
ON f.id = e.idFilial
INNER JOIN roupa r 
ON e.id = r.idEstoque
GROUP BY d.id, r.genero

--Total de funcionários por filial
SELECT f.id AS idFilial, COUNT(func.id) AS totalFuncionarios
FROM filialLoja f
LEFT OUTER JOIN estoque e
ON f.id = e.idFilial
LEFT OUTER JOIN funcionario func
ON e.id = func.idEstoque
GROUP BY f.id

--Total de roupa por tipo separado por distribuidora
SELECT dt.id, r.tipoRoupa, SUM(r.quantidade) as TotalPorTipo
FROM roupa r RIGHT OUTER JOIN estoque stq
ON stq.id = r.idEstoque
RIGHT OUTER JOIN distribuidora dt
ON dt.tipoRoupaDist = r.tipoRoupa
GROUP BY dt.id, r.tipoRoupa

-- Média de quantidade de peças de roupa por estoque
SELECT stq.id AS idEstoque, AVG(r.quantidade) AS mediaQtd
FROM estoque stq INNER JOIN roupa r 
ON stq.id = r.idEstoque
GROUP BY stq.id

--Maior quantidade de roupa por estoque e sua marca
SELECT stq.id AS idEstoque, MAX(r.quantidade) AS maiorQuantidade, r.marca As MarcaMaiorQuantidade
FROM estoque stq INNER JOIN roupa r 
ON stq.id = r.idEstoque
GROUP BY stq.id, r.marca
ORDER BY MarcaMaiorQuantidade DESC