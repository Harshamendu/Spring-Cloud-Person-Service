DROP TABLE IF EXISTS Person;
 
CREATE TABLE Person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  person_id INTEGER(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  subscription_type VARCHAR(250) NOT NULL,
  age VARCHAR(250) DEFAULT NULL
);
 
--INSERT INTO Person (person_id,first_name, last_name,subscription_type,age) VALUES
--  (1,'Aliko', 'Dangote','Subscriber', 25),
--  (2,'Bill', 'Gates', 'Guest',45);
--  ('kill', 'Ramos', 4),
--  ('Folrunsho', 'Alakija', 5);