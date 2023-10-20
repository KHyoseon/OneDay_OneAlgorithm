-- 코드를 입력하세요
SELECT BOOK_ID, AUTHOR_NAME, date_format(PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from BOOK b left join AUTHOR a on b.AUTHOR_ID = a.AUTHOR_ID
where b.CATEGORY = '경제'
order by 3;