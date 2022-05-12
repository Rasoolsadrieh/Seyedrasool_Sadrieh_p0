-----------------------------------------
-------------C(reate)RUD-----------------
-- NOTE: MAKE SURE YOUR SCHEMA CONTAINS NONE OF THE FOLLOWING TABLE NAMES
	-- elemental_type
	-- abilites
	-- RossBank
-- Create ourselves a schema, un comment below command if you need to create it, 
-- then comment out again and execute script after selecting the appropriate schema in the toolbar above
-- create schema Rossbank;

-- CREATE OUR TABLES & APPLY CONSTRAINTS

-- Added in the Trainer table to match the system

create table bankUser (
	id serial primary key,
	fname varchar(20) not null,
	lname varchar(20) not null,
	email varchar(30) not null unique,
	"password" varchar(25) not null,
	dob varchar(15) not null
);







------------------------------------
-- Uncomment and Only execute if you need to dropp
-- drop table bankUser;
------------------------------------

-- CREATING NEW ENTRIES
-- Insert some bankUsers

insert into bankUser
values
(default, 'Charles', 'Jester', 'cj@mail.com', 'password', '01-01-0001'),
(default, 'Maxwell', 'House', 'mh@mail.com', 'password', '02-20-1900'),
(default, 'Soyoung', 'Lee', 'sl@mail.com', 'password', '06-01-2002');







-- single insert into RossBank
insert into RossBank 
values
(default, 'rattata', '12', '2', 1, 'Scratch', 'Tackle');

-- multi-insert 
insert into RossBank 
values
(default, 'pidgey', '10', '1', 1, 'Tackle', 'Scratch'),
(default, 'charmander', '25', '5', 2, 'Scratch', 'Fire Breath'),
(default, 'squirtle', '30', '3', 3, 'Scratch', 'Water Gun'),
(default, 'pikachu', '15', '10', 4, 'Tackle', 'Shock'),
(default, 'bulbasaur', '22', '6', 5, 'Tackle', 'Vine Whip');

-------------Create End------------------

-----------------------------------------
---------------CR(ead)UD-----------------
select * from RossBank p ;


-- joins

select RossBank.RossBank_name
from RossBank 
join abilities ON RossBank.ability1 = abilities.ability_name;


-- Same exact results as above, but we used the alias instead (RossBank p and ablities a)
-- If something is aliased, you have to use that
select p.RossBank_name, p.ability1, p.atk, a.atk_multiplier, a.dmg_type 
from RossBank p
join abilities a ON p.ability1 = a.ability_name;


-- views

create view RossBank_abilities as
select p.RossBank_name, p.ability1, p.atk, a.atk_multiplier, a.dmg_type 
from RossBank p
join abilities a ON p.ability1 = a.ability_name;

select * from RossBank_abilities;

select * from bankUser;

-------------Read End--------------------

-----------------------------------------
-------------CRU(pdate)D-----------------

-- update bankUser set dob = '06-01-2002' where email = 'sl@mail.com' ;

-------------Update End------------------

-----------------------------------------
-------------CRUD(elete)-----------------



-------------Delete End------------------
