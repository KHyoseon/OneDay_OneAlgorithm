-- 코드를 입력하세요
-- 보호소에 들어올 당시에는 중성화되지 않았지만, 보호소를 나갈 당시에는 중성화된 동물
-- 아이디와 생물 종, 이름
-- dkdlel tns
SELECT distinct(ai.ANIMAL_ID), ai.ANIMAL_TYPE, ai.NAME
from ANIMAL_INS ai left outer join ANIMAL_OUTS ao on ai.ANIMAL_ID = ao.ANIMAL_ID
where ai.SEX_UPON_INTAKE like 'Intact%' and ao.SEX_UPON_OUTCOME not like 'Intact%'
order by 1;