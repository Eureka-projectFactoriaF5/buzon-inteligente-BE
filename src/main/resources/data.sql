-- Limpieza de tablas en orden inverso
DELETE FROM parcels;
DELETE FROM access_codes;
DELETE FROM access_code_status;
DELETE FROM profiles;
DELETE FROM users;
DELETE FROM mailboxes;
DELETE FROM mailbox_sizes;
DELETE FROM mailbox_status;
DELETE FROM lockers;
DELETE FROM locker_status;
DELETE FROM roles;

-- 1. Roles
INSERT INTO roles (role_name) VALUES ('USER');

-- 2. Locker Status
INSERT INTO locker_status (locker_status_name)
VALUES ('AVAILABLE'), ('IN_MAINTENANCE');

-- 3. Lockers
INSERT INTO lockers (address, time_limit, locker_status_id)
VALUES
  ('Calle Falsa 123, Bloque A', 86400, 1),
  ('Avenida Siempre Viva 742, Bloque B', 86400, 1);

-- 4. Mailbox Status
INSERT INTO mailbox_status (mailbox_status_name)
VALUES ('FREE'), ('OCCUPIED');

-- 5. Mailbox Sizes
INSERT INTO mailbox_sizes (size_name, capacity)
VALUES
  ('SMALL', 10),
  ('MEDIUM', 20),
  ('LARGE', 30),
  ('X-LARGE', 40);

-- 6. Mailboxes
INSERT INTO mailboxes (mailbox_size_id, locker_id, mailbox_status_id, mailbox_number)
VALUES
  (2, 1, 1, 101),
  (3, 2, 1, 202);

-- 7. Users
INSERT INTO users (user_DNI, user_name, user_surname, user_email, user_password, role_id, locker_id)
VALUES
  ('12345678A', 'Bob', 'Esponja', 'bobesponja@example.com', '$2a$10$.MG/XelATSTEQ5t.3pxLAewFBsRrxpO40Fmxab.QmccQqktT5fAky', 1, 1),
  ('87654321B', 'Freddie', 'Mercury', 'freddiemercury@example.com', '$2a$10$qTiooOtaXZ/MC8RCPpnyVeSaxpA9MgBn9BSEl5Vkd4NBBFvHVyUOm', 1, 2);

-- 8. Profiles
INSERT INTO profiles (id, user_id, permanent_credential)
VALUES
  (1, 1, 'CRED-BOB01'),
  (2, 2, 'CRED-FREDDIE01');

-- 9. Access Code Status
INSERT INTO access_code_status (access_code_status_name)
VALUES
  ('Pendiente'),
  ('Entrega fallida'),
  ('Entregado'),
  ('Recogido'),
  ('Pospuesto'),
  ('No recogido'),
  ('Entregado parcialmente'),
  ('Recogido parcialmente');

-- 10. Access Codes


-- 11. Parcels
