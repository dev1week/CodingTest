-- 코드를 입력하세요

select c.car_id, 
        c.car_type, 
        round(
            (c.daily_fee * 30 * 
             (100 - p.discount_rate) 
            ) / 100, 0) AS fee
from CAR_RENTAL_COMPANY_CAR c 
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p 
on p.car_type = c.car_type
where car_id not in (
    SELECT car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where start_date between '2022-11-01' and '2022-11-30' 
    or end_date between '2022-11-01' and '2022-11-30'
    or (START_DATE < '2022-11-01' AND END_DATE >= '2022-12-01')
)
and (c.car_type = 'SUV' or c.car_type='세단')
and p.duration_type = '30일 이상'
and round(
            (c.daily_fee * 30 * 
             (100 - p.discount_rate) 
            ) / 100, 0) between 500000 and 2000000
order by fee desc, car_type, car_id desc 








