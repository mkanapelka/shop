alter table IF EXISTS product_in_cart
    drop COLUMN name;

alter table IF EXISTS product_in_cart
    drop COLUMN product_id;

alter table IF EXISTS product_in_cart
    drop COLUMN cost;

alter table IF EXISTS product_in_cart
    add product_id int8;

alter table if exists product_in_cart
    add constraint fk_product_product_in_cart
        foreign key (product_id)
            references product;


alter table IF EXISTS product_in_order
    drop COLUMN name;

alter table IF EXISTS product_in_order
    drop COLUMN product_id;

    alter table IF EXISTS product_in_order
    drop COLUMN cost;

    alter table IF EXISTS product_in_order
    add product_id int8;

alter table if exists product_in_order
    add constraint fk_product_product_in_order
        foreign key (product_id)
            references product;