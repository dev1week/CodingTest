-- 코드를 작성해주세요

select n.ITEM_ID, n.ITEM_NAME, n.RARITY 
from ITEM_INFO p 
join ITEM_TREE r
    on p.item_id = r.parent_item_id 
join ITEM_INFO n
    on r.item_id = n.item_id 
where p.rarity = 'RARE'
order by n.item_id desc; 