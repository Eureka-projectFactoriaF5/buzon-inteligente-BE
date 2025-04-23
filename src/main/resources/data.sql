INSERT INTO roles (role_name) VALUES ('USER');

INSERT INTO locker_status (locker_status_name)
VALUES
  ('AVAILABLE'),
  ('IN_MAINTENANCE');


INSERT INTO lockers (address, time_limit, locker_status_id)
VALUES
  ('Calle Falsa 123, Bloque A', 86400, 1),
  ('Avenida Siempre Viva 742, Bloque B', 86400, 1);









































INSERT INTO access_code_status (access_code_status_name) VALUES 
('Pendiente'),
('Entrega fallida'),
('Entregado'),
('Recogido'),
('Pospuesto'),
('No recogido'),
('Entregado parcialmente'),
('Recogido parcialmente');