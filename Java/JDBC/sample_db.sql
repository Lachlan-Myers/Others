drop table employee;
create table employee
 (ENO	char(3) primary key,
   ENAME char(18),
   TITLE  char(12));
insert into employee values 
 ('E1', 'J.Doe', 'Elect.Eng.'); 
insert into employee values 
 ('E2', 'M.Smith', 'Syst.Anal.');
insert into employee values 
 ('E3', 'A.Lee', 'Mech.Eng.');
insert into employee values 
 ('E4', 'J.Miller', 'Programmer');
insert into employee values 
 ('E5', 'B.Casey', 'Syst.Anal.');
insert into employee values 
 ('E6', 'L.Chu', 'Elect.Eng.');
insert into employee values 
 ('E7', 'R.Davis', 'Mech.Eng.');
insert into employee values 
 ('E8', 'J.Jones', 'Syst.Anal.');

drop table assignment;
create table assignment
(ENO    char(3),
PNO     char(3),
RESP    char(12),
DUR     number(3));
alter table assignment
	add primary key (ENO, PNO);
insert into assignment values
('E1', 'P1', 'Manager', 12);
insert into assignment values
('E2', 'P1', 'Analyst', 24);
insert into assignment values
('E2', 'P2', 'Analyst', 6);
insert into assignment values
('E3', 'P3', 'Consultant', 10);
insert into assignment values
('E3', 'P4', 'Engineer', 48);
insert into assignment values
('E4', 'P2', 'Programmer', 18);
insert into assignment values
('E5', 'P2', 'Manager', 24);
insert into assignment values
('E6', 'P4', 'Manager', 48);
insert into assignment values
('E7', 'P3', 'Engineer', 36);
insert into assignment values
('E8', 'P3', 'Manager', 40);

drop table project;
create table project
(PNO	char(3) primary key,
PNAME	char(24),
BUDGET	number,
LOC	char(8));
insert into project values
('P1', 'Instrumentation', 150000, 'Montreal');
insert into project values
('P2', 'Database Developt.', 135000, 'New York');
insert into project values
('P3', 'CAD/CAM', 250000, 'New York');
insert into project values
('P4', 'Maintenance', 310000, 'Paris');

drop table pay;
create table pay
(TITLE	char(12) primary key,
SAL	number);
insert into pay values
('Elect.Eng.', 40000);
insert into pay values
('Syst.Anal.', 34000);
insert into pay values
('Mech.Eng.', 27000);
insert into pay values
('Programmer', 24000);

select * from employee;
select * from assignment;
select * from project;
select * from pay;

