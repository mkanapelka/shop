INSERT INTO usr (created, updated, name, email, first_name, last_name, password) VALUES
(now(),now(),'user','user@mail.ru','Userov','User','$2y$12$W9/klPl394ZZ.Z9ajFvKX.Ts1IG9L3VMv/mtZ7MAORpVZWyCA38ny'),
(now(),now(),'admin','admin@mail.ru','Adminov','Admin','$2y$12$JwEVhVJPSS/mnIVqhSE68OzDfyJHzyiBOTlw8749.uFex4vNfQwSO');

INSERT INTO user_role (user_id, roles) VALUES
(1,'USER'),
(2,'ADMIN');

INSERT INTO user_status (user_id, status) VALUES
(1,'ACTIVE'),
(2,'ACTIVE');