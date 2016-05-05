create database duomeiti  default  character set utf8;

set global       interactive_timeout=31536000;
 show global variables like '%timeout';
 
 grant all privileges on *.*  to root@'%'  identified by  'root';
 
 202.118.67.200:8888