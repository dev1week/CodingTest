-- 코드를 입력하세요
select m.member_name, r.review_text, date_format(r.review_date, '%Y-%m-%d') as review_date
from member_profile m
join rest_review r
on r.member_id = m.member_id
where m.member_id = (
    select member_id
    from rest_review
    group by member_id 
    order by count(review_id) desc
    limit 1
)
order by r.review_date, review_text ;

-- 리뷰를 가장 많이 작성한 회원의 리뷰 조회 

-- 리뷰를 가장 많이 작성한 회원의 Id를 조회한다. 