    create table user_role (
       user_id int8 not null,
        roles varchar(255)
    );

    alter table if exists user_role
       add constraint FKfpm8swft53ulq2hl11yplpr5
       foreign key (user_id)
       references usr;

insert into user_role(user_id, roles) values
(1,'USER'),
(2,'ADMIN');