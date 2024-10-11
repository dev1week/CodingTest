-- 코드를 입력하세요

## 입양을 간 기록이 있으나 보호소에 들어온 기록이 없는 동물의 ID와 이름 


select o.animal_id, o.name 
from animal_outs o 
left join animal_ins i 
on i.animal_id = o.animal_id 
where i.animal_id is null 
order by animal_id