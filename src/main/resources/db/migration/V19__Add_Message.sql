create table message (
       id bigserial not null,
        created timestamp,
        updated timestamp,
        from_address varchar(255),
        text varchar(1250),
        subject varchar(255),
        primary key (id)
    );