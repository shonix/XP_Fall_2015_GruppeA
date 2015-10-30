insert into mydb.user(name, password, IP)
values 
('Markus', 'admin123', '192.168.0.1'),
('PeterJ', 'admin123', '192.168.0.2'),
('Jannik', 'admin123', '192.168.0.3'),
('PeterR', 'admin123', '192.168.0.4'),
('Emiiil', 'admin123', '192.168.0.5'),
('Daaaan', 'admin123', '192.168.0.6'),
('Nikiii', 'admin123', '192.168.0.7'),
('Elinor', 'admin123', '192.168.0.8'),
('Marcus', 'admin123', '192.168.0.9');
insert into mydb.status(userID)
values 
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9);
insert into mydb.leaves
values 
(1, 4, 0, 9),
(2, 5, 0, 9),
(3, 6, 0, 9),
(4, 7, 0, 9),
(5, 8, 0, 9),
(6, 9, 0, 9),
(7, 10, 0, 9),
(8, 11, 0, 9),
(9, 12, 0, 9);
insert into mydb.gameStat
values
(1, 'Sid Meier\'s Civ V', 2, 3, 4, 5),
(2, 'Kerbal Space Program', 2, 3, 4, 5),
(3, 'Chess', 2, 3, 4, 5),
(4, 'Chess', 2, 3, 4, 5),
(5, 'Chess', 2, 3, 4, 5),
(6, 'Chess', 2, 3, 4, 5),
(7, 'Chess', 2, 3, 4, 5),
(8, 'Chess', 2, 3, 4, 5),
(9, 'Chess', 2, 3, 4, 5);