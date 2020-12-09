-- #2
select name meeting, count(*) persons
from meeting left join meeting_person mp on meeting.id = mp.meeting_id
where "confirmed"
group by name;

-- #3
select name empty_meetings
from meeting left join meeting_person mp on meeting.id = mp.meeting_id
where person_id is null;
