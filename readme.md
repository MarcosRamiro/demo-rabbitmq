# Docker and MySQL

## Como criar usuário e deixar habilitado para conectar de outros hosts

mysql> create user 'spring_user'@'%' identified by 'spring_pass';

mysql> revoke all on db_test.* from 'spring_user'@'%';

mysql> grant select, insert, delete, update on db_test.* to 'spring_user'@'%';


### Fonte:

* https://stackoverflow.com/questions/33827342/how-to-connect-mysql-workbench-to-running-mysql-inside-docker
* https://spring.io/guides/gs/accessing-data-mysql