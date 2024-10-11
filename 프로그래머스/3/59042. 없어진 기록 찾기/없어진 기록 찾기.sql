-- 코드를 입력하세요

## 입양을 간 기록이 있으나 보호소에 들어온 기록이 없는 동물의 ID와 이름 


select animal_id, name 
from animal_outs
where animal_id not in (select animal_id from animal_ins)
order by animal_id