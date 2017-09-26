drop table Instructors;
drop table Courses;
drop table Students;
drop table Records;

create table Instructors(
	instructorUsername varchar(15),
	instructorName varchar(30) NOT NULL,
	primary key(instructorUsername)
);

create table Courses(
	title varchar(30) NOT NULL,
	courseID int check(courseID >= 0)UNIQUE,
	instructorUsername varchar(15),
	courseDay varchar(15) check(courseDay in ('MWF', 'TR')),
	courseTime timestamp NOT NULL,
	courseSize int check(courseSize >= 0),
	primary key(courseID, instructorUsername, courseDay, courseTime),
	foreign key(instructorUsername) references Instructors(instructorUsername)
);

create table Students(
	studentName varchar(30) NOT NULL,
	studentUsername varchar(15),
	deviceID varchar(30) NOT NULL,
	primary key(studentUsername)
);

create table Records(
	studentUsername varchar(15),
	courseID int check(courseID >= 0),
	recordDate date NOT NULL,
	attendance char(1) check(attendance in ('Y', 'N')) NOT NULL,
	primary key(studentUsername, courseID),
	foreign key(studentUsername) references Students(studentUsername),
	foreign key(courseID) references Courses(courseID)
);
