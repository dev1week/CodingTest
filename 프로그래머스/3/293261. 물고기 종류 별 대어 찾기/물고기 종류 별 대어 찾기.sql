-- 코드를 작성해주세요

select i.id, n.fish_name, i.length
from fish_info i 
join fish_name_info n 
    on i.fish_type = n.fish_type
join (select fish_type, max(length) as max_length
    from fish_info
    group by fish_type) s 
    on i.fish_type = s.fish_type and i.length = s.max_length; 