Create database Movies;

use Movies;

Create table Peliculas(
	id int primary key,
    descripcion varchar(250),
    yearMovie int
);


Create table Categorias(
	id int primary key auto_increment,
	descripcion varchar(250)
);


Create table peliculascategorias(
	idPelicula int, 
    idCategoria int
	
);


Select *from Peliculas;

Select * from Categorias;


Truncate table Peliculas;
Truncate table Categorias;