-- 코드를 작성해주세요

select distinct d.id, d.email, d.first_name, d.last_name 
from developers d 
join skillcodes s
on s.code & d.skill_code 
where s.category = 'Front End'
order by d.id;


-- front end 스킬을 가진 개발자 정보 조회 
-- 조건에 맞는 개발자의 ID, 이메일, 이름, 성 조회 
-- ID 기준 오름차순 정렬 