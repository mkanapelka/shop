    create sequence hibernate_sequence start 1 increment 1;

    create table characteristic (
       id serial8 not null,
        name varchar(255),
        primary key (id)
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
       id serial8 not null,
        name varchar(255),
        cost float8,
        description varchar(255),
        quantity int4,
        vendor_code varchar(255),
        primary key (id)
    );


    create table rank (
       id serial8 not null,
        name varchar(255),
        primary key (id)
    );


    alter table if exists characteristic
       add constraint UK_9r0uiwdfk26smpyokptibd84l unique (name);


    alter table if exists product
       add constraint UK_jmivyxk9rmgysrmsqw15lqr5b unique (name);


    alter table if exists rank
       add constraint UK_hqp7w6d3btg5deebi9jg1jxkw unique (name);


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