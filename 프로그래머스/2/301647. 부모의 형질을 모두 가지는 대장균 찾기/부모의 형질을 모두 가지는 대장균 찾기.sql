-- 코드를 작성해주세요
select c.id, c.genotype, p.genotype as parent_genotype 
from ecoli_data p
join ecoli_data c 
on c.parent_id = p.id 
where (c.genotype & p.genotype) =p.genotype
order by c.id asc; 
