--  보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회
-- 보호 시작일이 빠른 순
SELECT i.ANIMAL_ID, i.NAME
from ANIMAL_INS i left join ANIMAL_OUTS o on i.ANIMAL_ID = o.ANIMAL_ID
where i.DATETIME > o.DATETIME
order by i.DATETIME;