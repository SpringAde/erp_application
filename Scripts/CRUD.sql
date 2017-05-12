insert into employee(eno, ename, salary, dno, gender, joindate, title) values 
(17012, '유대리', 3100000, 1, 1, now(), 1);

select eno, ename, salary, dno, gender, joindate, title from employee;

select tcode, tname from title;

insert into title(tcode, tname) values (6, "이사");

update title set tname="회장" where tcode=6;

delete from title where tcode=6;

select count(*) from title;


