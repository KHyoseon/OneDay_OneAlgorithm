-- 생물 종, 이름, 성별 및 중성화 여부
-- 아이디 순
-- Null -> No name
SELECT ANIMAL_TYPE, if(NAME is null, 'No name', NAME) as NAME, SEX_UPON_INTAKE
from ANIMAL_INS
order by ANIMAL_ID;