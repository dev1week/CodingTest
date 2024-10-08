-- 코드를 작성해주세요

SELECT id,
       CASE
           WHEN a.s_rank * 100 / total_count <= 25 THEN 'CRITICAL'
           WHEN a.s_rank * 100 / total_count <= 50 THEN 'HIGH'
           WHEN a.s_rank * 100 / total_count <= 75 THEN 'MEDIUM'
           ELSE 'LOW'
           END AS colony_name
FROM (SELECT id,
             rank() over (ORDER BY SIZE_OF_COLONY DESC) AS s_rank, 
             COUNT(*) over () AS total_count
      FROM ecoli_data) a
ORDER BY 1