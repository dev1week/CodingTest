-- 코드를 입력하세요
SELECT f.flavor
from first_half f 
join (select sum(total_order) as total_order, flavor from july group by flavor) j
on j.flavor = f.flavor 
order by j.total_order + f.total_order desc
limit 3;

-- 7월 아이스크림 총 주문량 + 상반기 아이스크림 총 주문량 
-- 위 값이 가장 큰 순서대로 상위 3개 