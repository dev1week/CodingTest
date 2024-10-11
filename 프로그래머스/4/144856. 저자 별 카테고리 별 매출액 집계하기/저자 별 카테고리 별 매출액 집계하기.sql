-- 코드를 입력하세요
select d.author_id, d.author_name, c.category, c.total_sales
from author d 
join (
    SELECT a.author_id, b.category, sum(b.price*s.sales) as total_sales 
    from BOOK b
    join author a 
    on a.author_id = b.author_id 
    join book_sales s 
    on s.book_id = b.book_id 
    where year(s.sales_date) = 2022 and month(s.sales_date) = 1
    group by b.author_id, b.category
) c
on c.author_id = d.author_id
order by d.author_id, c.category desc
