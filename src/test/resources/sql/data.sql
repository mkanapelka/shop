
INSERT INTO product(id, name, cost, description, quantity, vendor_code, sub_product_category_id, status) VALUES
(10001, 'борщ',1200,'чёткий и красный',7,'AL-123456789',1,'AVAILABLE');

INSERT INTO usr (id, email, first_name, is_active, last_name, password, auth_type, username) VALUES
(10001, 'ivan@mail.ru','Иванов', true,'Иван','ivan','ENTRY','ivan');

insert into user_role(user_id, roles) values
(10001,'USER');

INSERT INTO cart (id, quantity_product, total_cost, user_id) VALUES
(10001, 5,6000,10001);

INSERT INTO product_in_cart(id, quantity, cart_id, product_id) VALUES
(10001, 5, 10001, 10001);

insert into product_category (id, name, thumbnail, is_active)
values (10001, 'первое','Pervoe', true);

insert into sub_product_category(id, name, product_category_id)
values (10001, 'борщи', 10001),
       (10002, 'супы', 10001);

insert into characteristic (id, name)
values (10001, 'оттенок'),
       (10002, 'чёткость');

insert into link_subcategory_characteristic (subcategory_id, characteristic_id)
values (10001, 10001),
       (10001, 10002),
       (10002, 10001),
       (10002, 10002);
