drop table Records;
drop table Courses;
drop table Instructors;
drop table Devices;
drop table Students;



create table Instructors(
	instructorName varchar(30) NOT NULL,
	instructorUsername varchar(15),
	primary key(instructorUsername)
);

create table Courses(
	title varchar(50) NOT NULL,
	courseID int check(courseID >= 0) UNIQUE,
	instructorUsername varchar(15),
	courseDay varchar(15) check(courseDay in ('MWF', 'TR')),
	courseStartTime timestamp NOT NULL,
	courseEndTime timestamp NOT NULL,
	courseSize int check(courseSize >= 0),
	primary key(courseID, instructorUsername, courseDay, courseStartTime),
	foreign key(instructorUsername) references Instructors(instructorUsername)
);

create table Students(
	studentName varchar(30) NOT NULL,
	studentUsername varchar(15),
	primary key(studentUsername)
);

create table Devices(
	studentUsername varchar(15) NOT NULL,
	deviceID varchar(30),
	primary key(deviceID),
	foreign key(studentUsername) references Students(studentUsername)
);

create table Records(
	studentUsername varchar(15),
	courseID int check(courseID >= 0),
	recordDate date NOT NULL,
	attendance char(1) check(attendance in ('Y', 'N')) NOT NULL,
	primary key(studentUsername, courseID, recordDate),
	foreign key(studentUsername) references Students(studentUsername),
	foreign key(courseID) references Courses(courseID)
);
