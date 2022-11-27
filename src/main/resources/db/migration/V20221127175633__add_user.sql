delete from task;

create table "user"
(
    id             bigint not null
        primary key,
    email          varchar(255),
    password       varchar(255),
    username       varchar(255) unique not null
);

alter table "user"
    owner to core;

create sequence user_sequence increment by 50;
alter sequence user_sequence owner to core;

alter table task add column created_by_id bigint references "user" (id) not null;