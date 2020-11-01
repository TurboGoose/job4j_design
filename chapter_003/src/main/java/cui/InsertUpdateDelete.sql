create database chairs;
create table chair(
    id serial primary key,
    name varchar(200),
    material text,
    legs int2,
    back bool
);

insert into chair (name, material, legs, back) values ('Stool', 'wood', 4, false);
insert into chair (name, material, legs, back) values ('Retro chair', 'wood', 4, true);
insert into chair (name, material, legs, back) values ('Modern chair', 'aluminum', 1, true);
select * from chair;

update chair set name = 'Noname chair';
select * from chair;

delete from chair;
select * from chair;
