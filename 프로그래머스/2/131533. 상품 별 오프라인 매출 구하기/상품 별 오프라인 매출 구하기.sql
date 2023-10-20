-- 상품코드 별 매출액(판매가 * 판매량) 합계를 출력
-- 매출액 dsc, 상품코드 ase
SELECT p.PRODUCT_CODE, sum(p.PRICE * o.SALES_AMOUNT) as SALES
from OFFLINE_SALE o inner join PRODUCT p on o.PRODUCT_ID = p.PRODUCT_ID
group by p.PRODUCT_CODE
order by 2 desc, 1;