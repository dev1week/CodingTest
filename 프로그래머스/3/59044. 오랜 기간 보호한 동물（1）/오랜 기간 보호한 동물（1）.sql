-- 코드를 입력하세요
SELECT i.name, i.datetime 
from animal_ins i 
left join animal_outs o 
on i.animal_id = o.animal_id 
where o.animal_id is null 
order by i.datetime
limit 3; 
-- 입양을 못간 동물 
-- 가장 오래 보호소에 있었던 동물 
-- 보호 시작일 순 조회