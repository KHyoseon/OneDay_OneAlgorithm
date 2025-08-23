-- 코드를 입력하세요
SELECT DATE_FORMAT(DATETIME, '%H') as HOUR, count(*) as 'COUNT'
from ANIMAL_OUTS
group by HOUR
having 9<=HOUR and HOUR<20
order by 1;