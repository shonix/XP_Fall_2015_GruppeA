delete from mydb.user;
delete from mydb.status;
delete from mydb.leaves;
delete from mydb.gaeStat;
insert into mydb.user(name, password, IP)
values 
('Markus', 'Admin1', '192.168.0.1'),
('Peter', 'Admin2', '192.168.0.2'),
('Jannik', 'Admin3', '192.168.0.3');
insert into mydb.status(userID)
values 
(1),
(2),
(3);
insert into mydb.leaves
values 
(1, 4, 0, 9),
(2, 5, 0, 9),
(3, 6, 0, 9);
insert into mydb.gameStat
values
(1, 'Sid Meier\'s Civ V', 2, 3, 4, 5),
(2, 'Kerbal Space Program', 2, 3, 4, 5),
(3, 'Chess', 2, 3, 4, 5);