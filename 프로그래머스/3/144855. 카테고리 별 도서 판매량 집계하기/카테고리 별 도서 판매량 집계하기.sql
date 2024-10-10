-- 코드를 입력하세요
SELECT b.category, sum(s.sales) as total_sales
from book b 
join book_sales s 
on s.book_id=b.book_id
where year(s.SALES_DATE)=2022 and month(s.SALES_DATE) = 1
group by b.category
order by category; 