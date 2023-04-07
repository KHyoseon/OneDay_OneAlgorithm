-- 코드를 입력하세요
SELECT o.ANIMAL_ID, o.NAME
from ANIMAL_OUTS as o
where o.ANIMAL_ID not in (select i.ANIMAL_ID 
                          from ANIMAL_INS as i);