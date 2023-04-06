-- 코드를 입력하세요
SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(date_of_birth, '%Y-%m-%d') as 'DATE_OF_BIRTH'
from member_profile
where GENDER='W' and TLNO is not null and Month(date_of_birth) = '3'
order by member_id asc;