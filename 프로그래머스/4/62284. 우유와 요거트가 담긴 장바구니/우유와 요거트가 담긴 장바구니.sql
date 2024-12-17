-- 코드를 입력하세요
SELECT CART_ID from (
        select * from CART_PRODUCTS
        where NAME='Yogurt' or NAME='Milk'
    ) as YM
group by CART_ID
having count(DISTINCT NAME) = 2
order by 1;