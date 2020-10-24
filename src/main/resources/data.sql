
--This is script is the seed of the application
--is executed when the RestApi start.
insert into role (id, name) 1, 'ADMIN');
insert into role (id, name) 2, 'USER');

--inserting user of type ADMIN  --password is adminUser
insert into userapp(id,username,password) 
select nextval(1,'adminUser','$2a$10$My3yDzChxDQLVFIIRsp5leJAy3MC6/5doJ93IZL5saO2IAUB/jquK'); 
--inserting user of type USER --password is customerUserr 
insert into userapp(id,username,password) 
select nextval(2,'user','$2a$10$4myBvPzw.5.y.8FBZ/5JLOYozDIpZBlCoAaAjKgX5xuGVcVmbZ9xK'); 

--insert user roles relation
insert into user_roles(user_id,role_id)  
(1,1); 
 insert into user_roles(user_id,role_id)  
(1,2);
insert into user_roles(user_id,role_id)  
(2,2); 


