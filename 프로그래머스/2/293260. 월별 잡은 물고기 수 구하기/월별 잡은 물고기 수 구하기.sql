-- 코드를 작성해주세요

select count(id) as fish_count, month(time) as month 
from FISH_INFO 
group by month
order by month; 