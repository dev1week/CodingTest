-- 코드를 입력하세요
SELECT DR_NAME, DR_ID, MCDP_CD,  DATE_FORMAT(HIRE_YMD, '%Y-%m-%d') as HIRE_YMD
from DOCTOR 
WHERE MCDP_CD = 'CS' or MCDP_CD = 'GS'
order by hire_ymd DESC, DR_NAME ASC