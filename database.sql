-- script do Banco de Dados PraticaJDBC para o projeto JDBC
CREATE DATABASE IF NOT EXISTS PraticaJDBC;
USE PraticaJDBC;

-- 1. Removendo as tabelas se já existirem
DROP TABLE IF EXISTS seller;
DROP TABLE IF EXISTS department;

-- 2. Criando a tabela department
CREATE TABLE department (
	Id int(11) NOT NULL AUTO_INCREMENT, 		 -- Identificador do departamento
	Name varchar(60) DEFAULT NULL,				 -- Nome do departamento	
	PRIMARY KEY (Id) 							 -- Chave primária
);

-- 3. Criando a tabela seller
CREATE TABLE seller (
    Id int(11) NOT NULL AUTO_INCREMENT, 		 -- Identificador do vendedor
    Name varchar(60) DEFAULT NULL,				 -- Nome do vendedor
    Email varchar(100) DEFAULT NULL,			 -- Email do vendedor
    BirthDate date DEFAULT NULL,				 -- Data de nascimento do vendedor
    BaseSalary double DEFAULT NULL,				 -- Salário base do vendedor
    DepartmentId int(11) DEFAULT NULL,			 -- Identificador do departamento (chave estrangeira)
    PRIMARY KEY (Id),							 -- Chave primária
    FOREIGN KEY (DepartmentId) REFERENCES department(Id)  -- Chave estrangeira referenciando a tabela department
);

-- 4. Inserindo dados na tabela department
INSERT INTO department (Name) VALUES 
(1, 'Computers'),
(2, 'Electronics'),
(3, 'Fashion'),
(4, 'Books')
(5, 'Sports')
(6, 'Toys')
(7, 'Furniture')
(8, 'Groceries')
(9, 'Health')
(10, 'Automotive')
(11, 'Garden-Supplies')
(12, 'Music-Instruments');

-- 5. Inserindo dados na tabela seller
INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES  
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2),
  -- NOVOS VENDEDORES ABAIXO:
  ('Anna White', 'anna@gmail.com', '2001-05-10 00:00:00', 2800, 6),    -- Sports
  ('John Wick', 'john@gmail.com', '1985-08-20 00:00:00', 5500, 10),   -- Automotive
  ('Sarah Connor', 'sarah@gmail.com', '1990-02-15 00:00:00', 3200, 7), -- Tools
  ('Peter Parker', 'spidey@gmail.com', '2002-06-01 00:00:00', 1500, 12); -- Games