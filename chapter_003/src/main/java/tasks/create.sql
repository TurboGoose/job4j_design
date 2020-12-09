-- #1
create table person (
    id serial primary key,
    name varchar(100)
);

create table meeting (
    id serial primary key,
    name varchar(100)
);

create table meeting_person (
    person_id int references person(id),
    meeting_id int references meeting(id),
    confirmed boolean default false,
    primary key (person_id, meeting_id)
);
