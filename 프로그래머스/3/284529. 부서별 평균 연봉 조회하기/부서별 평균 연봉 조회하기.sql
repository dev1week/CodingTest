-- 코드를 작성해주세요
select d.dept_id, d.DEPT_NAME_EN, round(avg(e.sal),0) as AVG_SAL
from HR_DEPARTMENT d
join HR_EMPLOYEES e 
on d.dept_id = e.dept_id 
group by d.dept_id
order by AVG_SAL desc