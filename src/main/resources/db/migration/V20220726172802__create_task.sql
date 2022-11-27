create sequence task_sequence
    increment by 50;

alter sequence task_sequence owner to core;

create table task
(
    id         bigint not null
        primary key,
    content    varchar(255),
    created_at timestamp,
    name       varchar(255),
    updated_at timestamp
);

alter table task
    owner to core;

