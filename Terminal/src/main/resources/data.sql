DROP TABLE IF EXISTS Terminal;
CREATE TABLE Terminal (
  terminal_id INT PRIMARY KEY,
  sequence_num INT,
  status VARCHAR(25)  
);
 
INSERT INTO Terminal (terminal_id,status) VALUES
  (1001,'active'),
  (1002,'active'),
  (1003,'active'),
  (1004,'active'),
  (1005,'active');