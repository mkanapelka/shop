DELETE
from usr
where email = 'admin@mail.ru';

INSERT INTO usr (created, updated, username, email, first_name, last_name, password, is_active, auth_type) VALUES
(now(),now(),'admin','admin@mail.ru','Adminov','Admin','$2a$04$8dl9dNwcKiSuy/yvdtNRaeQURAGkyAzYuStTiQWLfJL/ejqzkH/UO', true, 'ENTRY');