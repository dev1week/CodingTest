-- 코드를 작성해주세요

select round(avg(t.l),2) as AVERAGE_LENGTH
from (select ifnull(length, 10) as l from fish_info) as t;