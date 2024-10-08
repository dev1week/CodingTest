-- 코드를 작성해주세요

select count(c.id) as fish_count
from fish_info c 
join fish_name_info i 
on i.fish_type = c.fish_type
where i.fish_name='BASS' or i.fish_name='SNAPPER';