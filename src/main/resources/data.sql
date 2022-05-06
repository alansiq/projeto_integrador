INSERT INTO salesman (full_name) VALUES ('Rafael'), ('Fernando'), ('Vinicius'), ('Alan'), ('Kleber'), ('Romário'), ('Ronaldo');

INSERT INTO buyer (full_name) VALUES ('Mauri'), ('Kenyo');

INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Banana', 10, 0, 15, 'FRESH', 1);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Maca', 15, 0, 15,'FRESH',1 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Laranja', 25, 10, 20,'FRESH',1 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Melancia', 50, 15, 25,'FRESH',1 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Carne', 25, -15, 5,'FROZEN_FOOD',2 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Pizza', 45, -10, 0,'FROZEN_FOOD',2 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Filé de Peito', 30, -10, 5,'FROZEN_FOOD',2 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Filé Mignon', 30, -20, 5,'FROZEN_FOOD',2 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Iogurte', 6, 7, 15,'REFRIGERATED',3);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Requeijão', 10, 7, 15,'REFRIGERATED',3);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Danone', 3, 5, 15,'REFRIGERATED',3);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Refrigerante', 3, 5, 15,'REFRIGERATED',3);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Banana', 10, 0, 15, 'FRESH', 4);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Maca', 15, 0, 15,'FRESH',4 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Laranja', 25, 10, 20,'FRESH',4 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Melancia', 50, 15, 25,'FRESH',4 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Carne', 25, -15, 5,'FROZEN_FOOD',5 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Pizza', 45, -10, 0,'FROZEN_FOOD',5 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Filé de Peito', 30, -10, 5,'FROZEN_FOOD',5 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Filé Mignon', 30, -20, 5,'FROZEN_FOOD',5 );
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Iogurte', 6, 7, 15,'REFRIGERATED',6);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Requeijão', 10, 7, 15,'REFRIGERATED',6);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Danone', 3, 5, 15,'REFRIGERATED',6);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Refrigerante', 10, 5, 15,'REFRIGERATED',6);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Mozzarela', 100, 10, 15,'FRESH',7);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Presunto', 100, 10, 15,'FRESH',7);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Peito de Peru', 100, 10, 15,'FRESH',7);
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Mortadela', 100, 10, 15,'FRESH',7);

INSERT INTO warehouse (name, logradouro, numero, complemento, cep, city, estado) VALUES ('Osasco', 'Rua Osasco', '1550', 'S/N', '0990-0123', 'Osasco', 'São Paulo');
INSERT INTO warehouse (name, logradouro, numero, complemento, cep, city, estado) VALUES ('Bahia', 'Rua Bahia', '5478', 'S/N', '5326-9076', 'Bahia', 'Bahia');

INSERT INTO section (capacity, category, current_temperature, warehouse_id) VALUES (500, 'FRESH', 5, 1);
INSERT INTO section (capacity, category, current_temperature, warehouse_id) VALUES (500, 'FROZEN_FOOD', -21, 1);
INSERT INTO section (capacity, category, current_temperature, warehouse_id) VALUES (500, 'REFRIGERATED', 0, 1);

INSERT INTO section (capacity, category, current_temperature, warehouse_id) VALUES (500, 'FRESH', 5, 2);
INSERT INTO section (capacity, category, current_temperature, warehouse_id) VALUES (500, 'FROZEN_FOOD', -21, 2);
INSERT INTO section (capacity, category, current_temperature, warehouse_id) VALUES (500, 'REFRIGERATED', 0, 2);

INSERT INTO representative (full_name, section_id) VALUES ('JOSE', 1);
INSERT INTO representative (full_name, section_id) VALUES ('MARI', 2);
INSERT INTO representative (full_name, section_id) VALUES ('VITOR', 3);

INSERT INTO representative (full_name, section_id) VALUES ('HUGO', 4);
INSERT INTO representative (full_name, section_id) VALUES ('FLAVIO', 5);
INSERT INTO representative (full_name, section_id) VALUES ('RAFA', 6);

INSERT INTO batch_stock (initial_quantity, current_quantity, price, due_date, manufacturing_date, manufacturing_time, product_id, section_id) VALUES ( 400, 400, 10, '2022/01/15','2021/01/10' ,'1999-01-08 23:00:00', 1, 2);
INSERT INTO batch_stock (initial_quantity, current_quantity, price, due_date, manufacturing_date, manufacturing_time, product_id, section_id) VALUES (1000, 1000, 20, '2022/01/16', '2022/01/13' ,'1999-01-08 14:30:00', 1, 1);
INSERT INTO batch_stock (initial_quantity, current_quantity, price, due_date, manufacturing_date, manufacturing_time, product_id, section_id) VALUES (2000, 2000, 30, '2022/05/15', '2022/05/10', '1999-01-08 14:30:00', 1, 1);
INSERT INTO batch_stock (initial_quantity, current_quantity, price, due_date, manufacturing_date, manufacturing_time, product_id, section_id) VALUES (250, 250, 40, '2022/05/21', '2022/05/10', '1999-01-08 17:45:00', 1, 4);
INSERT INTO batch_stock (initial_quantity, current_quantity, price, due_date, manufacturing_date, manufacturing_time, product_id, section_id) VALUES (750, 250, 50, '2022/07/03', '2022/07/01', '1999-01-08 14:35:00', 1, 4);
