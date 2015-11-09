create database duomeiti  default  character set utf8;

set global       interactive_timeout=31536000;
 show global variables like '%timeout';
 
 grant all privileges on *.* to root@'%' identified by 'root'; # 授权给所有用户