-- 코드를 작성해주세요
select count(i.id) as fish_count, n.fish_name as fish_name
from FISH_NAME_INFO n
join FISH_INFO i 
on i.fish_type = n.fish_type 
group by fish_name 
order by fish_count desc; 
