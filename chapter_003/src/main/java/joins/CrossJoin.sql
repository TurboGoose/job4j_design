-- table creation
create table teens (
   id serial primary key,
   name varchar(200),
   gender boolean       -- true -> male;  false -> female
);

-- data filling
insert into teens(name, gender) values ('m1', true), ('m2', true), ('f1', false), ('f2', false);

-- select all different pairs
select t1.name males, t2.name females from teens t1 cross join teens t2
where t1.gender and not t2.gender;
