-- 코드를 입력하세요
SELECT a.ANIMAL_ID, a.NAME
from ANIMAL_OUTS as a
where a.ANIMAL_ID not in (select i.ANIMAL_ID 
                          from ANIMAL_INS as i inner join ANIMAL_OUTS as aa on i.ANIMAL_ID= aa.ANIMAL_ID);