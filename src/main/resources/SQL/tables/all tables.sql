create table roles
(
    role_id    integer generated always as identity
        constraint "Roles_pk"
            primary key,
    role_name  varchar not null ,
    role_level integer not null
);

create table profile
(
    email        varchar not null,
    first_name   varchar not null,
    last_name    varchar not null,
    phone_number varchar not null,
    pid          integer generated always as identity
        constraint "Profile_pk"
            primary key
);

create table available_shifts
(
    week_number    integer not null,
    day_number     integer not null,
    start_hour     varchar not null,
    end_hour       varchar not null,
    employee_count integer not null,
    manager_count  integer not null,
    shifts_id      integer generated always as identity
        constraint "Available_shifts_pk"
            primary key
);

create table constraint_type
(
    type_id                integer generated always as identity
        constraint "Constraint_type_pk"
            primary key,
    constraint_level       integer not null,
    constraint_description varchar not null
);


create table users
(
    username varchar not null,
    uid      integer generated always as identity
        constraint "User_pk"
            primary key,
    password varchar not null,
    pid      integer not null
        constraint "User_Profile_pid_fk"
            references profile,
    role_id  integer not null
        constraint "User_Roles_role_id_fk"
            references roles
);

create table constrains
(
    constraint_id  integer generated always as identity
        constraint constraint_id
            primary key,
    type_id        integer not null
        constraint "Constrains_Constraint_type_type_id_fk"
            references constraint_type,
    uid            integer not null
        constraint "Constrains_User_uid_fk"
            references users,
    data           integer not null,
    permanent_flag boolean not null,
    week_number    integer not null,
    start_date     integer not null,
    end_date       integer not null
);

create table shifts_requests
(
    "Request_id" integer generated always as identity
        constraint "Shifts_requests_pk"
            primary key,
    shift_id     integer   not null
        constraint "Shifts_requests_Available_shifts_shifts_id_fk"
            references available_shifts,
    uid          integer   not null
        constraint "Shifts_requests_User_uid_fk"
            references users,
    time_stamp   timestamp not null
);

create table schedule (
                            schedule_id integer primary key generated always as identity,
                            week_number integer not null,
                            request_id integer not null,
                            foreign key (request_id) references shifts_requests
);