create table "Constraint_type"
(
    type_id                integer generated always as identity
        constraint "Constraint_type_pk"
            primary key,
    constraint_level       integer not null,
    constraint_description varchar not null
);
alter table Constraint_type
      alter column constraint_level set not null default 1
      alter column constraint_description set not null default 'No description';

