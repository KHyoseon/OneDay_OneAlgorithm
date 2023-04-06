-- 코드를 입력하세요
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from REST_INFO as R1
group by FOOD_TYPE, FAVORITES
having FAVORITES = (select MAX(FAVORITES)
                    from REST_INFO as R2
                    where R2.FOOD_TYPE = R1.FOOD_TYPE)
order by 1 desc;