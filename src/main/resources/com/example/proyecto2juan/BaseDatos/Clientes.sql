DROP DATABASE IF EXISTS Clientes;
CREATE DATABASE Clientes;
Use Clientes;


CREATE TABLE Usuarios(
Id int unsigned auto_increment primary key,
NombreUsuario varchar(30),
Contraseña varchar(64),
Correo varchar(30)


);
INSERT INTO Usuarios  VALUES
(1,"Juan Perez","bb86eee39ea8e390890e64b13249414449b2d8e8e64a851609a6fd87a1feb424","juanperez@gmail.com"),
(2,"Maria Gonzalez","0c1d6237bef3125b63dc3ca41d23b3d73419c23a7ad260e681361d96fa4a0720","mariagonzalez@gmail.com"),
(3,"Carlos Rodriguez","66c5e3a9b27a28adaf3b28bc64c4c0fa6ef75daf7f8f2de2326ffed223ce02b6","carlosrodriguez@gmail.com"),
(4,"Ana Lopez","c8e3ac42e686793e93b1980e3e95b6d6052bfaadb50a829e28ce8c8af49dba74","analopez@gmail.com"),
(5,"Luis Martinez","0c8580e0531cd3f95a47dd87017fd8cfafce082ca5a56a48423f303760475e31","luismartinez@gmail.com"),
(6,"Sofia Hernandez","865655d0f4582d89c082c3fec8d0e4f25eb11ba5580336eff5ffcfb67f095b1e","sofiahernandez@gmail.com"),
(7,"Pedro Sanchez","e9e5c06f45a15bda9905f0b9b88aea3711b17e68c1fc2d74007a751d5b65885b","pedrosanchez@gmail.com");