create database mydatabase;
create user 'myuser'@'%' identified by 'password';
grant all on mydatabase.* to 'myuser'@'%';