DROP DATABASE IF EXISTS Clientes;
CREATE DATABASE Clientes;
Use Clientes;


CREATE TABLE Usuarios(
Id int unsigned auto_increment primary key,
Nombre varchar(30),
Contraseña varchar(64),
Correo varchar(30));

CREATE TABLE Productos(
Id int unsigned auto_increment primary key,
Nombre varchar(30),
Precio Double
);
INSERT INTO Usuarios  VALUES
(1,"Juan Perez","630e8b9d4b526f224f721c299f586309b6da475a4c536fa66a48d8d0b2db7ff4","juanperez@gmail.com"),
(2,"Maria Gonzalez","41262418c85bff0e53567a387f7a7a8cc62fcd17a3d0c8d341d069edbd8fdab2","mariagonzalez@gmail.com"),
(3,"Carlos Rodriguez","66c5e3a9b27a28adaf3b28bc64c4c0fa6ef75daf7f8f2de2326ffed223ce02b6","carlosrodriguez@gmail.com"),
(4,"Ana Lopez","2a4c8b680ffccfdcc09ab0ebe35dd41d1ad3fc2395cdf023fc04671204911821","analopez@gmail.com"),
(5,"Luis Martinez","c94a21dc9372bbc8d0172deb5ad400826995af10f965fdc8f59d1353d6194e11","luismartinez@gmail.com"),
(6,"Sofia Hernandez","5755d090bcf3a2e861d33395bd1d17c758a80c27a5eb6c58c22b4d075b96270d","sofiahernandez@gmail.com"),
(7,"Pedro Sanchez","93a08e68cded9dc91173ce162c5f2afe896750a49fa08be8eaf14d082cd2b085","pedrosanchez@gmail.com"),
(8,"1","6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b","1");

INSERT INTO Productos VALUES
(1,"EFT Arena Standard Edition","37.00"),
(2,"EFT Standard Edition","52.00"),
(3,"EFT Left Behind Edition","86.00"),
(4,"EFT Prepare for Escape Edition","115.00"),
(5,"EFT Unheard Edition","250.00");