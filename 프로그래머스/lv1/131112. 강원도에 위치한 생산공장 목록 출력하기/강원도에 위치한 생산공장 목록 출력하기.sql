-- 코드를 입력하세요
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS from FOOD_FACTORY where substring(address, 1, instr(address, ' ')-1)='강원도' order by 1;