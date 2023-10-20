-- 아직 입양을 못 간 동물 중, 가장 오래 보호소에 있었던 동물
-- 3마리의 이름, 보호 시작일
-- 보호 시작일 순
SELECT i.NAME, i.DATETIME
from ANIMAL_INS i left join ANIMAL_OUTS o on i.ANIMAL_ID = o.ANIMAL_ID
where o.DATETIME is null
order by i.DATETIME
limit 3;