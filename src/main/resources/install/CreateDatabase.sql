create database lems_database; -- Creates the new database
create user 'lems'@'%' identified by 'qvT>PZEyE*6:@;'; -- Creates the user
grant all on lems_database.* to 'lems'@'%'; -- Gives all privileges to the new user on the newly created database