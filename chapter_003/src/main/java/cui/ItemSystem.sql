-- database creation
create database item_system;

-- database structure creation
create table rule (
    id serial primary key
);

create table role (
    id serial primary key
);

create table role_rule (
    id serial primary key,
    role_id int references role(id),
    rule_id int references rule(id)
);

create table "user" (
    id serial primary key,
    role_id int references role(id)
);

create table state (
    id serial primary key
);

create table category (
    id serial primary key
);

create table item (
    id serial primary key,
    user_id int references "user"(id),
    state_id int references state(id),
    category_id int references category(id)
);

create table comment (
    id serial primary key,
    item_id int references item(id)
);

create table attachment (
    id serial primary key,
    item_id int references item(id)
);
