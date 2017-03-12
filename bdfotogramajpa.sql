DROP DATABASE IF EXISTS bdfotogramajpa;
CREATE DATABASE bdfotogramajpa;
USE bdfotogramajpa;

CREATE TABLE fotogramas (
  idFotograma int(11) NOT NULL,
  archivo varchar(45) NOT NULL,
  titPelicula varchar(255) NOT NULL,
  anyoEstreno int(11) NOT NULL,
  director varchar(128) NOT NULL,
  genero varchar(50) DEFAULT NULL,
  PRIMARY KEY (idFotograma)
);

  CREATE TABLE concurso (
  login varchar(12) NOT NULL,
  numGlobalAciertos int(11) DEFAULT 0,
  numGlobalFotOfrecidos int(11) DEFAULT 0,
  porAciertosGlobal int(11) DEFAULT 0,
  PRIMARY KEY (login)
);

CREATE TABLE generos (
  genero varchar(30) NOT NULL,
  PRIMARY KEY (genero)
);

CREATE TABLE ranking (
  login varchar(12) NOT NULL,
  puntos int(11) DEFAULT NULL,
  PRIMARY KEY (login)
);

CREATE TABLE usuarios (
  login varchar(12) NOT NULL,
  clave varchar(12) NOT NULL,
  PRIMARY KEY (login)
);

CREATE TABLE fotacertados (
  login varchar(12) NOT NULL,
  idFotograma int(11) NOT NULL,
  acertado boolean NOT NULL default false,
  PRIMARY KEY (login, idFotograma)
);

INSERT INTO fotogramas VALUES 
(0,'chacal.jpg','Chacal',1973,'Fred Zinnemann','Policiaco'),
(1,'ciudadano.jpg','Un ciudadano ejemplar',2009,'F. Gary Gray','Intriga'),
(2,'diablo.jpg','Diablo',2003,'F. Gary Gray','Accion'),
(3,'romperstomper.jpg','Romper stomper',1992,'Geoffrey Wright','Accion'),
(4,'invicto3.jpg','Invicto 3',2010,'Isaac Florentine','Accion'),
(5,'scout.jpg','El ultimo boy scout',1991,'Tony Scott','Accion'),
(6,'tirador.jpg','El tirador',2007,'Antoine Fuqua','Thriller'),
(7,'vecinos.jpg','Vecinos invasores',2006,'Tim Johnson','Comedia'),
(8,'england.jpg','This is England',2006,'Shane Meadows','Drama'),
(9,'gladiator.jpg','Gladiator',2000,'Ridley Scott','Accion');

INSERT INTO generos VALUES 
('Accion'),
('Comedia'),
('Drama'),
('Intriga'),
('Policiaca'),
('Thriller');

INSERT INTO ranking VALUES 
('juan',2),
('ana',5),
('usuario',1),
('german',9),
('bubu',8),
('yo',15),
('pablo',20),
('david',8),
('clara',7),
('antonio',7),
('admin',25),
('eduardo',10);

INSERT INTO usuarios VALUES 
('bubu','bubu'),
('frolik','frolik'),
('rox','rox'),
('usuario','usuario');

INSERT INTO fotacertados (login,idFotograma,acertado) VALUES
('bubu',0,false),
('bubu',1,false),
('bubu',2,true),
('bubu',3,false),
('bubu',4,false),
('bubu',5,true),
('bubu',6,false),
('bubu',7,false),
('bubu',8,true),
('bubu',9,false)
;
