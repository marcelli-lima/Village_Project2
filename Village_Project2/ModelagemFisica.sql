CREATE TABLE village (
  id SERIAL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  surname VARCHAR(60) NOT NULL,
  cpf VARCHAR(15) NOT NULL UNIQUE,
  birthday_date DATE NOT NULL,
  income NUMERIC(5,2)
);

CREATE TABLE inhabitant (
    id SERIAL PRIMARY KEY,
    village_id BIGINT NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE habitantes_role (
   habitante_id INT NOT NULL,
   role_id INT NOT NULL,
   CONSTRAINT user_role_table_user_fk
       FOREIGN KEY (inhabitant_id)
           REFERENCES "inhabitant" (id),
   CONSTRAINT user_role_table_role_fk
       FOREIGN KEY (role_id)
           REFERENCES role (id)
);

INSERT INTO village (name, surname, cpf, birthday_date, income) VALUES('Marcelli', 'Lima', '111.111.111-11', '1999-02-03', 500);
INSERT INTO village (name, surname, cpf, birthday_date, income) VALUES('Marcelino', 'Filho', '222.222.222-22', '1968-04-28', 600);
INSERT INTO village (name, surname, cpf, birthday_date, income) VALUES('Rosangela', 'Lima', '333.333.333-33', '1973-01-26', 700);

INSERT INTO inhabitant (village_id, email, password) VALUES(1, 'marcelli@gmail.com', '12345678');
INSERT INTO inhabitant (village_id, email, password) VALUES(2, 'marcelino@gmail.com', '87654321');
INSERT INTO inhabitant (village_id, email, password) VALUES(3, 'rosangela@gmail.com', '12344321');

INSERT INTO role (name) VALUES('ADMIN');
INSERT INTO role (name) VALUES('USER');

INSERT INTO habitantes_role (inhabitant_id, role_id) VALUES(1, 1);
INSERT INTO habitantes_role (inhabitant_id, role_id) VALUES(2, 2);
INSERT INTO habitantes_role (inhabitant_id, role_id) VALUES(3, 2);