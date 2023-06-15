insert into user_details(id,birth_date,name) values (10001,current_date(),'jp1');
insert into user_details(id,birth_date,name) values (10002,current_date(),'jp2');
insert into user_details(id,birth_date,name) values (10003,current_date(),'jp3');
insert into post(id,description,user_id) values (20001,'testjp',10001);
insert into post(id,description,user_id) values (20002,'testjp2',10001);
insert into post(id,description,user_id) values (20003,'testjp3',10002);