    ALTER TABLE user_order
ADD address varchar(2000) not null;
ALTER TABLE user_order
ADD delivery_method varchar(50) not null;
ALTER TABLE user_order
ADD payment_method varchar(50) not null;