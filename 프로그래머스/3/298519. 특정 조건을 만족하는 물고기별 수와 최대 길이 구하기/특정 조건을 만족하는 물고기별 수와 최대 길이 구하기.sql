-- 코드를 작성해주세요
select count(f1.id) as fish_count, 
        max(
            f1.length 
        ) as max_length, 
        f1.fish_type 
from FISH_INFO f1
join (select fish_type 
      from fish_info 
      group by fish_type 
      having avg(case 
                when length <= 10 then 10 
                else length
            end)>=33
      ) f2
on f2.fish_type = f1.fish_type
group by fish_type
order by fish_type;