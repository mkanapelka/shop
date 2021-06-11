
INSERT INTO product(id, name, cost, description, quantity, vendor_code) VALUES
(10001, 'борщ',1200,'чёткий и красный',7,'AL-123456789');


INSERT INTO usr(id, email, first_name, is_active, last_name, name) VALUES
(10001, 'ivan@mail.ru','Иванов', true,'Иван','ivan');

INSERT INTO cart (id, quantity_product, total_cost, user_id) VALUES
(10001, 5,1000,10001);