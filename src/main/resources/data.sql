INSERT INTO roles (role_name) 
VALUES 
  ('USER'), ('ADMIN');

INSERT INTO locker_status (locker_status_name)
VALUES
  ('AVAILABLE'),
  ('IN_MAINTENANCE');


INSERT INTO lockers (address, time_limit, locker_status_id)
VALUES
  ('Calle Falsa 123, Bloque A', 86400, 1),
  ('Avenida Siempre Viva 742, Bloque B', 86400, 1);

INSERT INTO mailbox_status (mailbox_status_name) 
VALUES
  ('FREE'),
  ('OCCUPIED');

INSERT INTO mailbox_sizes (size_name)
VALUES
  ('SMALL'),
  ('MEDIUM'),
  ('LARGE');
  ('X-LARGE');

INSERT INTO mailboxes (mailbox_size_id, locker_id, mailbox_status_id, mailbox_number)
VALUES
  (2, 1, 1, 101),
  (3, 2, 1, 202);

INSERT INTO users (user_DNI, user_name, user_surname, user_email, user_password, role_id, locker_id)
VALUES
  ('12345678A', 'Juan', 'Pérez', 'juan@example.com', 'pass123', 1, 1),
  ('87654321B', 'Ana', 'López', 'ana@example.com', 'pass456', 1, 2);

INSERT INTO profiles (user_id, credential)
VALUES
  (1, '12345678'),
  (2, '12345678');

INSERT INTO access_codes (access_code, profile_id, access_code_name, update_on, is_locked, access_code_status_id)
VALUES
  ('ABC123', 1, 'Código Juan', NOW(), false, 1),
  ('XYZ789', 2, 'Código Ana', NOW(), true, 1);

INSERT INTO parcels (access_code_id, mailbox_id, delivery_date, alarm_date, deadline_date)
VALUES
  (1, 1, NOW(), NOW() + INTERVAL 1 DAY, NOW() + INTERVAL 3 DAY),
  (2, 2, NOW(), NOW() + INTERVAL 1 DAY, NOW() + INTERVAL 3 DAY);