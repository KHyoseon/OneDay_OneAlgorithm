-- 코드를 입력하세요
SELECT year(o.SALES_DATE) as YEAR, month(o.SALES_DATE) as MONTH,
count(distinct(o.USER_ID)) as PUCHASED_USERS,
round(count(distinct(o.USER_ID)) / (select count(*) from USER_INFO where year(JOINED) = 2021), 1) as PUCHASED_RATIO
from ONLINE_SALE o join USER_INFO u on o.USER_ID = u.USER_ID
where year(u.JOINED) = 2021
GROUP BY 1, 2
ORDER BY 1, 2;