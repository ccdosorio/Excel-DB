CREATE DATABASE db_movies;

USE db_movies;

CREATE TABLE pelicula (
	codigo_pelicula INT PRIMARY KEY,
	descripcion VARCHAR(250),
	anio INT
);

CREATE TABLE genero(
	codigo_genero INT AUTO_INCREMENT PRIMARY KEY,
	descripcion VARCHAR(250)
);

CREATE TABLE pelicula_genero(
	codigo_pelicula INT,
	codigo_genero INT,
	PRIMARY KEY (codigo_pelicula,codigo_genero),
	KEY `FK_pelicula_genero` (codigo_genero),
	CONSTRAINT `FK_pelicula_genero` FOREIGN KEY (codigo_genero) REFERENCES genero (codigo_genero),
	CONSTRAINT `FK_pelicula_genero_pelicula` FOREIGN KEY (codigo_pelicula) REFERENCES pelicula (codigo_pelicula)

)