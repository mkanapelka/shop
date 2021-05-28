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
    
    create table link_product_category_characteristic (
       product_category_id int8 not null,
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
        quantity int4,
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
    
    create table user_status (
       user_id int8 not null,
        status varchar(255)
    );

--    exp
        create table user_role (
       user_id int8 not null,
        roles varchar(255)
    );
    
    create table usr (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        name varchar(255),
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
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
    
    alter table if exists link_product_category_characteristic 
       add constraint FK77n980bobf344ffk1gqxyxsfx 
       foreign key (characteristic_id) 
       references characteristic;
    
    alter table if exists link_product_category_characteristic 
       add constraint fk_product_category_to_characteristic 
       foreign key (product_category_id) 
       references product_category;
    
    alter table if exists link_product_order 
       add constraint FKnemlm5jxsdpu3e636lot0iabm 
       foreign key (order_id) 
       references product;
    
    alter table if exists link_product_order 
       add constraint fk_order_to_product 
       foreign key (product_id) 
       references user_order;
    
    alter table if exists link_product_product_category 
       add constraint FKrrpt0k4d2eyjfcga16bkq5299 
       foreign key (product_category_id) 
       references product_category;
    
    alter table if exists link_product_product_category 
       add constraint fk_product_to_product_category 
       foreign key (product_id) 
       references product;
    
    alter table if exists user_order 
       add constraint FKaqljrb4vcwujwu1k9fkd2a5jx 
       foreign key (user_id) 
       references usr;
    
    alter table if exists user_status 
       add constraint FKet59o5t9q6lghggkaxlu2c593 
       foreign key (user_id) 
       references usr;

--exp
    alter table if exists user_role
       add constraint FKet59o5t9q6lghggkaxlu2c593
       foreign key (user_id)
       references usr;