-- Table for users
CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Table for authorities
CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

-- Unique index for authorities
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

-- Insert sample users (passwords should be encoded in production)
INSERT INTO users (username, password, enabled) VALUES
  ('user', '{noop}user', true),
  ('admin', '2b$12$e2KevoKygr30U6IzVwdkVOjGF57BRIhsx8AxPLAIvz5lg3SyyRZ6O', true);

-- Insert sample authorities
INSERT INTO authorities (username, authority) VALUES
  ('user', 'ROLE_USER'),
  ('admin', 'ROLE_ADMIN'),
  ('admin', 'ROLE_USER');

  -- update sample user password
-- UPDATE users SET password = '{noop}user' WHERE username = 'user';
