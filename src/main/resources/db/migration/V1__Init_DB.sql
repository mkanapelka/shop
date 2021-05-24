create sequence hibernate_sequence start 1 increment 1;

    
    create table basket (
       id bigserial not null,
        order_number varchar(255),
        quantity_product int4 not null,
        total_cost float8 not null,
        user_id int8,
        primary key (id)
    );
    
    create table characteristic (
       id bigserial not null,
        name varchar(255),
        primary key (id)
    );
    
    create table link_product_order (
       product_id int8 not null,
        order_id int8 not null
    );
    
    create table link_product_rank (
       product_id int8 not null,
        rank_id int8 not null
    );
    
    create table link_rank_characteristic (
       rank_id int8 not null,
        characteristic_id int8 not null
    );
    
    create table product (
       id bigserial not null,
        name varchar(255),
        cost float8,
        description varchar(255),
        quantity int4,
        vendor_code varchar(255),
        primary key (id)
    );

    create table rank (
       id bigserial not null,
        name varchar(255),
        primary key (id)
    );
    
    create table rol (
       id bigserial not null,
        name varchar(255),
        primary key (id)
    );
    
    create table user_order (
       id bigserial not null,
        order_number varchar(255),
        total_cost float8 not null,
        user_id int8,
        primary key (id)
    );
    
    create table user_roles (
       user_id int8 not null,
        role_id int8 not null
    );
    
    create table usr (
       id bigserial not null,
        name varchar(255),
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    );
    
    alter table if exists characteristic 
       add constraint UK_9r0uiwdfk26smpyokptibd84l unique (name);
    
    alter table if exists product 
       add constraint UK_jmivyxk9rmgysrmsqw15lqr5b unique (name);
    
    alter table if exists product 
       add constraint UK_1p3f104fxuka6mielkduscihy unique (vendor_code);
    
    alter table if exists rank 
       add constraint UK_hqp7w6d3btg5deebi9jg1jxkw unique (name);
    
    alter table if exists usr 
       add constraint UK_mkjheedol1oe4evwyjw7ixpot unique (name);
    
    alter table if exists usr 
       add constraint UK_dfui7gxngrgwn9ewee3ogtgym unique (username);
    
    alter table if exists basket 
       add constraint FKhp62cg2m4fosjqwgq7hxtxead 
       foreign key (user_id) 
       references usr;
    
    alter table if exists link_product_order 
       add constraint FK7grnfhst1b3lr48lxoccl221u 
       foreign key (order_id) 
       references user_order;
    
    alter table if exists link_product_order 
       add constraint fk_product_to_order 
       foreign key (product_id) 
       references product;
    
    alter table if exists link_product_rank 
       add constraint FKkeygy3rg9f5rbqmmauvq5o13o 
       foreign key (rank_id) 
       references rank;
    
    alter table if exists link_product_rank 
       add constraint fk_product_to_rank 
       foreign key (product_id) 
       references product;
    
    alter table if exists link_rank_characteristic 
       add constraint FKkk7r889esd7vxk04mpw6hsupa 
       foreign key (characteristic_id) 
       references characteristic;
    
    alter table if exists link_rank_characteristic 
       add constraint fk_rank_to_characteristic 
       foreign key (rank_id) 
       references rank;
    
    alter table if exists user_order 
       add constraint FKaqljrb4vcwujwu1k9fkd2a5jx 
       foreign key (user_id) 
       references usr;
    
    alter table if exists user_roles 
       add constraint FKrhfovtciq1l558cw6udg0h0d3 
       foreign key (role_id) 
       references rol;
    
    alter table if exists user_roles 
       add constraint fk_user_to_role 
       foreign key (user_id) 
       references usr;