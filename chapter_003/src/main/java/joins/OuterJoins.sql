-- table creation
create table departments (
    id serial primary key,
    name varchar(200)
);

create table employees (
    id serial primary key,
    name varchar(200),
    department_id int references departments(id)
);

-- data filling
insert into departments(name) values('dep1'), ('dep2'), ('dep3');
insert into employees(name, department_id) values ('Emp11', 1), ('Emp12', 1), ('Emp21', 2), ('NoDepEmp', null);

-- selects with different joins
select * from departments d left join employees e on d.id = e.department_id;
select * from departments d right join employees e on d.id = e.department_id;
select * from departments d full join employees e on d.id = e.department_id;

-- select employees without department
select e.name from employees e left join departments d on e.department_id = d.id where d.id is null;

-- the same result with different joins
select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;
