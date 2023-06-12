--- Mysql 

CREATE DATABASE manage;

CREATE TABLE  manage.customer
(
    customer_id  INT PRIMARY KEY AUTO_INCREMENT,
    first_name  varchar(60) NOT NULL,
    middle_name varchar(60),
    last_name   varchar(60) NOT NULL,
    suffix      varchar(6),
    email       varchar(60) NOT NULL UNIQUE,
    phone       varchar(15) NOT NULL
);
insert into  manage.customer
(
    customer_id ,
    first_name,
    middle_name,
    last_name   ,
    suffix     ,
    email  ,
    phone 
) values 
(1, 'Ryacho',    'Louis',     'MEN', 'Mr',  'ryacho@gmail.com',    '0112233445'),
(2, 'Akka',   'Charlotte', 'MEN', 'Mme', 'akka@gmail.com',   '0112233446'),
(3, 'Mrzk', 'Gaspard',   'MEN', 'Mr',  'mrzk@gmail.com', '0112233447');

-------------------------------------------------------------------------------------------

--- postgresql


CREATE DATABASE manage
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;


\c manage;

CREATE TABLE  customer
(
    customer_id  serial PRIMARY KEY,
    first_name  varchar(60) NOT NULL,
    middle_name varchar(60),
    last_name   varchar(60) NOT NULL,
    suffix      varchar(6),
    email       varchar(60) NOT NULL UNIQUE,
    phone       varchar(15) NOT NULL
);

insert into  customer
(
    customer_id ,
    first_name,
    middle_name,
    last_name   ,
    suffix     ,
    email  ,
    phone 
) values 
(1, 'Ryacho',    'Louis',     'MEN', 'Mr',  'ryacho@gmail.com',    '0112233445'),
(2, 'Akka',   'Charlotte', 'MEN', 'Mme', 'akka@gmail.com',   '0112233446'),
(3, 'Mrzk', 'Gaspard',   'MEN', 'Mr',  'mrzk@gmail.com', '0112233447');


