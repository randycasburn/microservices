drop table MARKET_ORDERS if exists;
create table MARKET_ORDERS(ID bigint(20) NOT NULL AUTO_INCREMENT, CURRENCY varchar NOT NULL, AMOUNT double, SIDE varchar NOT NULL);