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

    create table image (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        big_image_array bytea,
        primary key (id)
    );

    create table link_cart_product (
       cart_id int8 not null,
        product_id int8 not null
    );

    create table link_product_order (
       product_id int8 not null,
        order_id int8 not null
    );

    create table link_subcategory_characteristic (
       subcategory_id int8 not null,
        characteristic_id int8 not null
    );

    create table product (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        cost int4,
        description varchar(255),
        name varchar(255),
        quantity int4,
        thumbnail_id int8,
        vendor_code varchar(255),
        sub_product_category_id int8,
        primary key (id)
    );

    create table product_category (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        name varchar(255),
        thumbnail varchar(255),
        primary key (id)
    );

    create table sub_product_category (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        name varchar(255),
        product_category_id int8,
        primary key (id)
    );

    create table user_order (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        order_number varchar(255),
        quantity_product int4 not null,
        total_cost int4 not null,
        user_id int8,
        primary key (id)
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
       add constraint UK_9r0uiwdfk26smpyokptibd84l unique (name);

    alter table if exists product
       add constraint UK_jmivyxk9rmgysrmsqw15lqr5b unique (name);

    alter table if exists product
       add constraint UK_1p3f104fxuka6mielkduscihy unique (vendor_code);

    alter table if exists product_category
       add constraint UK_9qvug0bmpkmxkkx33q51m7do7 unique (name);

    alter table if exists sub_product_category
       add constraint UK_7299o0g7qt1r0i13rjn024x6q unique (name);

    alter table if exists usr
       add constraint UK_mkjheedol1oe4evwyjw7ixpot unique (name);

    alter table if exists cart
       add constraint FKc9objqhvjc84nmsxvwk64dajp
       foreign key (user_id)
       references usr;

    alter table if exists link_cart_product
       add constraint FKj2tk3rf5vldn4ov6pl83x1w2h
       foreign key (product_id)
       references product;

    alter table if exists link_cart_product
       add constraint fk_cart_to_product
       foreign key (cart_id)
       references cart;

    alter table if exists link_product_order
       add constraint FKnemlm5jxsdpu3e636lot0iabm
       foreign key (order_id)
       references product;

    alter table if exists link_product_order
       add constraint fk_order_to_product
       foreign key (product_id)
       references user_order;

    alter table if exists link_subcategory_characteristic
       add constraint FKm9cvpfip0otkfr3m5v604qaj3
       foreign key (characteristic_id)
       references characteristic;

    alter table if exists link_subcategory_characteristic
       add constraint fk_subcategory_to_characteristic
       foreign key (subcategory_id)
       references sub_product_category;

    alter table if exists product
       add constraint FKkiexmtd3km769lu17gbwtma7x
       foreign key (sub_product_category_id)
       references sub_product_category;

    alter table if exists sub_product_category
       add constraint FKaikfqflma7smbhvvju0v7q1bu
       foreign key (product_category_id)
       references product_category;

    alter table if exists user_order
       add constraint FKaqljrb4vcwujwu1k9fkd2a5jx
       foreign key (user_id)
       references usr;
