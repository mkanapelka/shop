create sequence hibernate_sequence start 1 increment 1;


    create table cart (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        order_number varchar(255),
        quantity_product int4 not null,
        total_cost int4 not null,
        user_id int8,
        primary key (id)
    );


    create table characteristic (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        name varchar(255),
        primary key (id)
    );


    create table link_cart_product (
       cart_id int8 not null,
        product_id int8 not null
    );


    create table link_product_characteristic (
       product_id int8 not null,
        characteristic_id int8 not null
    );


    create table link_product_order (
       product_id int8 not null,
        order_id int8 not null
    );


    create table link_product_product_category (
       product_id int8 not null,
        product_category_id int8 not null
    );

    create table product (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        name varchar(255),
        cost int4 not null,
        description varchar(255),
        quantity int4 not null,
        vendor_code varchar(255),
        primary key (id)
    );

    create table product_category (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        name varchar(255),
        primary key (id)
    );

    create table user_order (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        order_number varchar(255),
        total_cost int4 not null,
        user_id int8,
        primary key (id)
    );

    create table user_role (
       user_id int8 not null,
        roles varchar(255)
    );

    create table usr (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        email varchar(255),
        first_name varchar(255),
        is_active boolean,
        last_name varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );

    alter table if exists characteristic
       add constraint characteristic_name_unique unique (name);

    alter table if exists product
       add constraint product_name_unique unique (name);

    alter table if exists product
       add constraint product_vendor_code_unique unique (vendor_code);

    alter table if exists product_category
       add constraint product_category_name_unique unique (name);

    alter table if exists usr
       add constraint usr_name_unique unique (name);

    alter table if exists cart
       add constraint fk_user_to_cart
       foreign key (user_id)
       references usr;

    alter table if exists link_cart_product
       add constraint fk_product_to_cart
       foreign key (product_id)
       references product;

    alter table if exists link_cart_product
       add constraint fk_cart_to_product
       foreign key (cart_id)
       references cart;

    alter table if exists link_product_characteristic
       add constraint fk_characteristic_to_product
       foreign key (characteristic_id)
       references characteristic;

    alter table if exists link_product_characteristic
       add constraint fk_product_to_characteristic
       foreign key (product_id)
       references product;

    alter table if exists link_product_order
       add constraint fk_product_to_order
       foreign key (order_id)
       references product;

    alter table if exists link_product_order
       add constraint fk_order_to_product
       foreign key (product_id)
       references user_order;

    alter table if exists link_product_product_category
       add constraint fk_product_category_to_product
       foreign key (product_category_id)
       references product_category;

    alter table if exists link_product_product_category
       add constraint fk_product_to_product_category
       foreign key (product_id)
       references product;

    alter table if exists user_order
       add constraint fk_user_to_order
       foreign key (user_id)
       references usr;

    alter table if exists user_role
       add constraint fk_user_to_role
       foreign key (user_id)
       references usr;