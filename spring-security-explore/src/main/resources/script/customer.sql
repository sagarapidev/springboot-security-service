-- Customer table creation script
CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    hash_pwd VARCHAR(255) NOT NULL
);

-- Insert sample customers
INSERT INTO customer (name, email, role, hash_pwd) VALUES
  ('user', 'user@example.com', 'USER', '$2a$12$nSNWgqoDi1w8fGFu5dD21.MisJclifxp8XorBIEYdoVLqEGcwQi4.'),
  ('admin', 'admin@example.com', 'ADMIN', '$2a$12$5kVi3V9f5lpcvR7HhUhoCuXOF5Z9Klwzhh2wRp359KjVrjC8u.yW.');
