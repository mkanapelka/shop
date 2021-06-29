create table order_stat (
       id bigserial not null,
        user_id bigint,
        quantity_orders integer,
        total_cost integer,
        date_views date,
        primary key (id)
    );