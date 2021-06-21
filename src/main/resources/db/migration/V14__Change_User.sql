alter table usr
DROP COLUMN name;

alter table usr
    add username varchar(255);

INSERT INTO usr (created, updated, username, email, first_name, last_name, password, is_active, auth_type) VALUES
(now(),now(),'user','user@mail.ru','Userov','User','$2y$12$W9/klPl394ZZ.Z9ajFvKX.Ts1IG9L3VMv/mtZ7MAORpVZWyCA38ny', true, 'ENTRY'),
(now(),now(),'admin','admin@mail.ru','Adminov','Admin','$2y$12$JwEVhVJPSS/mnIVqhSE68OzDfyJHzyiBOTlw8749.uFex4vNfQwSO', true, 'ENTRY');

insert into user_role(user_id, roles) values
(1,'USER'),
(2,'ADMIN');