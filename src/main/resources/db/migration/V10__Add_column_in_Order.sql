    ALTER TABLE user_order
ADD address varchar(2000) not null,
ADD delivery_method varchar(50) not null,
ADD payment_method varchar(50) not null;