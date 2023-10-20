-- 나이 정보가 없는 회원이 몇 명
SELECT count(*) as USERS
from USER_INFO
where AGE is null;