insert into Instructors values ('Mike Roe', 'mroe');
insert into Instructors values ('Frank Kane', 'fkane');

insert into Courses values ('Software Engineering', 307,'mroe','TR','1-AUG-17 07:30:00','1-AUG-17 08:20:00', 0);
insert into Courses values ('Software Engineering II', 408,'fkane','MWF','1-AUG-17 07:30:00','1-AUG-17 08:20:00', 0);

insert into Students values ('Justin Boudreau', 'boudrej');
insert into Students values ('Joe Smith', 'jsmith1');
insert into Students values ('Billy Bob', 'billbob');
insert into Students values ('Test Test', 'testtest');

insert into Devices values ('boudrej', '12:34:56:78:99:99');
insert into Devices values ('boudrej', '99:99:87:65:43:21');
insert into Devices values ('jsmith1', '7C:04:D0:6B:DC:0C');
insert into Devices values ('billbob', 'AB:CD:EF:GH:IJ:KL');
insert into Devices values ('testtest', 'ZZ:ZZ:ZZ:ZZ:ZZ:ZZ');

insert into Records values ('boudrej', 307, '1-JAN-00', 'N');
insert into Records values ('jsmith1', 307, '1-JAN-00', 'N');
insert into Records values ('billbob', 307, '1-JAN-00', 'N');
insert into Records values ('testtest', 307, '1-JAN-00', 'N');

insert into Records values ('boudrej', 408, '1-JAN-00', 'N');



--add students to records
--insert into Records values ('boudrej', 307, '1-JAN-00', 'N');
--insert into Records values ('boudrej', 307, '1-OCT-17', 'Y');
--insert into Records values ('boudrej', 307, '2-OCT-17', 'Y');
--insert into Records values ('boudrej', 307, '3-OCT-17', 'Y');
--insert into Records values ('boudrej', 307, '4-OCT-17', 'Y');
--insert into Records values ('boudrej', 307, '5-OCT-17', 'Y');
--insert into Records values ('boudrej', 307, '6-OCT-17', 'N');
--insert into Records values ('boudrej', 307, '7-OCT-17', 'N');

--get student record on specific date
--select attendance from Records where studentUsername = 'boudrej' and courseID = 307 and recordDate = '1-JAN-00';

--get students' device IDs
--select deviceID from Devices natural join Students natural join Records where recordDate = '1-JAN-00' and courseID = 307
--OLD--select deviceID from Students natural join Records where recordDate = '1-JAN-00' and courseID = 307

--get roster for a course
--select studentUsername from Students natural join Records where recordDate = '1-JAN-00' and courseID = 307


--update student's attendance on a specific date
--update Records set attendance = 'N' where studentUsername = 'boudrej' and courseID = 307 and recordDate = '3-OCT-17'


--remove a stundet's record from a coures
--delete from Records where studentUsername = 'boudrej' and courseID = 408;

--update student's deviceID
--update Students set deviceID = 'AA:33:AA:22:AA:11' where studentUsername = 'justin5' 



--insert into Records values ('boudrej', 408, '2-OCT-17', 'N');

--problem because must select which device to update, a student may have more than one to update
--update Devices set deviceID = 'AA:33:AA:22:AA:11' where studentUsername = 'boudrej'

