    create table user_stat (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        user_id bigint,
        product_id bigint,
        quantity_views integer,
        date_views date,
        primary key (id)
    );