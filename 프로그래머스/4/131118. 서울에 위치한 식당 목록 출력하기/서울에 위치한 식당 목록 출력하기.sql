-- 서울에 위치한 식당)) 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수
-- 리뷰 평균점수는 소수점 세 번째 자리에서 반올림
-- 평균 내림차, 즐찾 내림차
SELECT i.REST_ID, i.REST_NAME, i.FOOD_TYPE, i.FAVORITES, i.ADDRESS,
    round(avg(r.REVIEW_SCORE), 2) as SCORE
from REST_INFO i join REST_REVIEW r on i.REST_ID = r.REST_ID
where i.ADDRESS like '서울%'
group by r.REST_ID
order by SCORE desc, FAVORITES desc;