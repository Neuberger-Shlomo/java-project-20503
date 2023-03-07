create table "Constrains"
(
    constraint_id  integer generated always as identity
        constraint constraint_id
            primary key,
    type_id        integer not null,
    uid            integer not null,
    data           integer not null,
    permanent_flag boolean not null,
    week_number    integer not null,
    start_date     timestamp not null,
    end_date       timestamp not null
);

alter table "Constrains"
    add constraint "Constrains_User_uid_fk"
        foreign key (uid) references "User";

alter table "Constrains"
    add constraint "Constrains_Constraint_type_type_id_fk"
        foreign key (type_id) references "Constraint_type";

alter table "Constrains"
alter column data type varchar using data::varchar;

alter table "Constrains"
    alter column data set not null;
alter table "Constrains"
    drop column start_date;

alter table "Constrains"
    drop column end_date;
alter table "Constrains"
    add start_date timestamp not null;

alter table "Constrains"
    add end_date timestamp not null;





