-- 코드를 작성해주세요
select s1.score + s2.score as score,e.emp_no, e.emp_name, e.position, e.email 
from hr_employees e 
join (select emp_no, HALF_YEAR, score from HR_GRADE where HALF_YEAR	=1) s1
on s1.emp_no = e.emp_no
join (select emp_no, HALF_YEAR, score from HR_GRADE where HALF_YEAR	=2) s2
on s2.emp_no = e.emp_no
order by score desc
limit 1; 