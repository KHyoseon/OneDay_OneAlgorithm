-- 코드를 입력하세요
SELECT CATEGORY, PRICE as MAX_PRICE, PRODUCT_NAME
from FOOD_PRODUCT as F1
group by CATEGORY, PRICE
having F1.CATEGORY in ('과자', '국', '김치', '식용유') and 
PRICE = (select MAX(PRICE) from FOOD_PRODUCT as F2
               where F2.CATEGORY = F1.CATEGORY)
order by PRICE desc;