-

create table "RossBank".users (
	id serial primary key,
	fname varchar(20) not null,
	lname varchar(20) not null,
	email varchar(30) not null unique,
	"password" varchar(25) not null,
	dob varchar(15) not null
);

insert into "RossBank".users
values
(default ,'Charles', 'Jester', 'cj@ddsdmail.com', 'password', '01-01-0001'),
(default ,'abbas', 'akbari', 'abbas@hhg.com', 'password', '01-01-0001'),


select * from "RossBank".users ;


