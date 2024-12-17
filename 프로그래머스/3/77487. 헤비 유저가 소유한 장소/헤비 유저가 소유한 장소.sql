-- 코드를 입력하세요
SELECT * from PLACES
where HOST_ID in (
        SELECT HOST_ID from PLACES
        group by HOST_ID
        having count(*) >= 2
    )
order by 1 asc;