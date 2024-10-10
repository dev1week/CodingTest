SELECT r1.food_type, r1.rest_id, r1.rest_name, r1.FAVORITES
from rest_info r1
join (
    select food_type, max(favorites) as f
    from rest_info
    group by food_type 
) r2 
on r2.food_type = r1.food_type and r2.f  = r1.favorites
order by food_type desc;