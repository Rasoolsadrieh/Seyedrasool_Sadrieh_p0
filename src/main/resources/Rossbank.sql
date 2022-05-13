create table Bank_User (
	email varchar(20) primary key,
	first_name varchar(20) not null,
	last_name varchar(20) not NULL,
	"password" varchar(20) not NULL
);
create table account (
	account_ID int primary key,
	email varchar(20) not NULL,
	account_name varchar(20) not null,
	balance int DEFAULT 0
);

insert into "rossbank".users
values
(4 ,'Charles', 'Jester', 'cj@ddsdmail.com', 'password', '01-01-0001'),
(5,'abbas', 'akbari', 'abbas@hhg.com', 'password', '01-01-0001'),

insert into "rossbank".users
values
(2 ,'Ross', 'Sadrieh', 'ross@gmail.com', 'password', '01-01-0001'),
select * from rossbank.users ;


