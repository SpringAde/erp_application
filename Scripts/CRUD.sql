/*Title*/

select eno, ename, salary, dno, gender, joindate, title from employee;
select tcode, tname from title;

insert into employee(eno, ename, salary, dno, gender, joindate, title) values
(17012, '황인영', 3100000, 1, 1, now(), 1);

insert into title(tcode, tname) values (6, "이사");
update title set tname="회장" where tcode=6;
delete from title where tcode=6;

select count(*) from title;


/*Department*/
select dcode, dname, floor from department;
select dcode, dname, floor from department where dcode=3;

insert into department(dcode, dname, floor) values (6, '디자인', 8);
update department set dname='회계' where dcode=4;
update department set floor=5 where dname='경영';
delete from  department where dcode=6;



/*Employee*/
select eno, ename, salary, dno, gender, joindate, title from employee;

select eno, ename, salary, dno, gender, joindate, title from employee where eno=17012;

insert into employee(eno, ename, salary, dno, gender, joindate, title) values 
(17012, '황인영', 3500000, 2, 0, now(), 3);

update employee set ename='황총명' where eno=17012;
update employee set title=2 where ename='황총명';
update employee set salary=4000000 where ename='황총명';

delete from  employee where dcode=6;

select * from employee e join department d on e.dno=d.dcode join title t on e.title=t.tcode;
select eno, ename, salary, d.dcode, gender, joindate, t.tcode from employee e inner join department d on e.dno=d.dcode inner join title t on e.title=t.tcode;



select eno, ename, salary, dno, gender, joindate, title from employee;

select max(eno) from employee;

