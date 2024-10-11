-- 코드를 입력하세요
SELECT i.animal_id, i.animal_type, i.name 
from animal_ins i
join (select animal_id, sex_upon_outcome
        from animal_outs 
        where sex_upon_outcome like '%Spayed%' or sex_upon_outcome like '%Neutered%')
        o
on i.animal_id = o.animal_id 
where i.sex_upon_intake like '%Intact%'
order by i.animal_id

-- 보호소 들어올 당시에는 중성화 x 
-- 보호소 나갈 때는 중성화됨 


-- 아이디 순 
