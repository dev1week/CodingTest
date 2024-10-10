-- 코드를 작성해주세요
select route, 
        concat(round(sum(d_between_dist), 1), 'km') as total_distance, 
        concat(round(avg(d_between_dist),2), 'km') as AVERAGE_DISTANCE
from subway_distance
group by route
order by route desc; 