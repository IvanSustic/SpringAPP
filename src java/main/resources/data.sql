INSERT INTO
    vozila(max_Passengers,gear_Box,air_Conditioning,doors,fuel,
           last_Inspection,next_Inspection,mileage,registration,chassis_Number)
values (4,'Automatik',true,4,'Dizel','2002-4-21','2025-4-21',2000,'ZG123-WK','41234123412341243');

INSERT INTO
    vozila(max_Passengers,gear_Box,air_Conditioning,doors,fuel,
           last_Inspection,next_Inspection,mileage,registration,chassis_Number)
values (5,'Automatik',true,4,'Benzin','2002-4-21','2025-4-21',2000,'ZG532-AJ','51234123412341243');

INSERT INTO
    vozila(max_Passengers,gear_Box,air_Conditioning,doors,fuel,
           last_Inspection,next_Inspection,mileage,registration,chassis_Number)
values (4,'Automatik',true,4,'Dizel','2002-4-21','2025-4-21',2000,'RI123-AS','61234123412341243');


insert into USERS(username, password,first_name,last_name) values('user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','Ivo','Ivić'); --password
insert into USERS(username, password,first_name,last_name) values('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','Pero','Perić'); --password
insert into USERS(username, password,first_name,last_name) values('read_only', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','Marko','Markić'); --password
insert into USERS(username, password,first_name,last_name) values('dummy', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','Jura','Jurić'); --password

insert into roles(name) values('USER');
insert into roles(name) values('ADMIN');
insert into roles(name) values('READ_ONLY');
insert into roles(name) values('DUMMY');



insert into users_roles(users_id, roles_id) values(1, 1);
insert into users_roles(users_id, roles_id) values(2, 2);
insert into users_roles(users_id, roles_id) values(3, 3);
insert into users_roles(users_id, roles_id) values(4, 4);

INSERT INTO
    reviews(naslov,tekst,ocjena,user_id)
values ('Opel astra','Najbolji auto',5,1);

INSERT INTO
    reviews(naslov,tekst,ocjena,user_id)
values ('Golf','Dobar auto',4,2);

INSERT INTO
    reviews(naslov,tekst,ocjena,user_id)
values ('Porsche','Ok auto',3,1);
