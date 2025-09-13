DROP DATABASE IF EXISTS DBProyectoSpringBoot;
CREATE DATABASE DBProyectoSpringBoot;
USE DBProyectoSpringBoot;


CREATE TABLE users (
	id int not null AUTO_INCREMENT, 
    first_name varchar(15) not null,
    last_name varchar(200) not null,
    email varchar(150) not null,
    primary key pk_id(id)
);

select * from users;
