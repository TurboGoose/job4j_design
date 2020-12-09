insert into meeting(name) values ('meeting 1'), ('meeting 2'), ('meeting 3'), ('meeting 4');
insert into person(name) values ('person1'), ('person2'), ('person3'), ('person4');
insert into meeting_person(person_id, meeting_id, confirmed) values (1, 1, true), (1, 2, false), (2, 2, true), (3, 3, false);
