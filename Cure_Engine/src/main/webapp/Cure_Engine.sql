-- -----------------------------------------------------
-- tbl_car : 중고차 상품
-- -----------------------------------------------------
CREATE TABLE tbl_car (
dealer_id CHAR(7) NOT NULL,
car_id VARCHAR(10) NOT NULL COMMENT '차량번호 (예)123허4567',
car_brand VARCHAR(45) NOT NULL COMMENT '제조사',
car_name VARCHAR(100) NOT NULL COMMENT '자동차명+트림',
car_color VARCHAR(45) NOT NULL,
car_price INT NOT NULL,
car_capacity INT NOT NULL COMMENT '배기량(cc)',
car_fuel VARCHAR(45) NOT NULL COMMENT '가솔린,디젤,하이브리드,전기,수소',
car_transmission VARCHAR(45) NOT NULL COMMENT '오토, 수동',
car_type VARCHAR(45) NOT NULL COMMENT '경차, 세단, SUV',
/*car_size VARCHAR(45) NOT NULL COMMENT '경형,소형,중형,대형',*/
car_accident CHAR(1) NULL DEFAULT 'N' COMMENT '사고차량(Y) 무사고(N)',
car_year INT NOT NULL COMMENT '연식',
car_distance INT NOT NULL COMMENT '주행거리',
car_image1 NVARCHAR(100) NOT NULL,
car_image2 NVARCHAR(100),
car_image3 NVARCHAR(100),
car_image4 NVARCHAR(100),
car_image5 NVARCHAR(100),
saleQty INT NOT NULL DEFAULT 1,
car_content VARCHAR(500) NULL COMMENT '간단한 차량설명 : 옵션 체크박스로 처리',
car_accident_detail VARCHAR(500),
car_like INT NULL COMMENT '관심',
sale_YN CHAR(1) NOT NULL DEFAULT 'Y',
PRIMARY KEY (car_id));

alter table tbl_car drop column car_size;
alter table tbl_car alter column saleQty set default 1;
alter table tbl_car alter column car_like set default 0;
alter table tbl_car add column car_accident_detail VARCHAR(500) after car_content;
alter table tbl_car modify car_image2 NVARCHAR(100);
alter table tbl_car modify car_image1 NVARCHAR(100);

update tbl_car set car_like=0 where car_id='111마1004';
update tbl_car set car_brand='maserati' where car_brand='Maserati' ;
update tbl_car set car_like=(car_like+1) where car_id='107허6655';

update tbl_car set car_color='blue' where car_id='104오1000';
update tbl_car set car_image5='X';

select * from tbl_car;

select car_id from tbl_car where car_brand='maserati' ;
select * from tbl_car where (car_price >=5000) and (car_price<=10000);

/* 차량검색 Test */
select *
from tbl_car
where (car_brand='benz' or car_brand='tesla') and (car_color='black' or car_color='white') and (car_type like '%SUV%');

select * from tbl_car where car_type like '%SUV%'
select * from tbl_car where (car_brand='tesla' or car_brand='maserati') and  (car_color='black' or car_color='white') and  (car_type like '%SUV%')
select * from tbl_car where (car_brand='benz' or car_brand='tesla' or car_brand='ferrari' or car_brand='maserati') and  (car_color='black' or car_color='white' or car_color='red') and  (car_type like '%SUV%')
-- -----------------------------------------------------
-- tbl_user : 사용자, 딜러, 관리자
-- -----------------------------------------------------
drop table tbl_user;

CREATE TABLE tbl_user (
user_category VARCHAR(45) NOT NULL COMMENT 'admin, dealer, customer',
user_id VARCHAR(45) NOT NULL,
user_pw VARCHAR(256) NOT NULL,
user_name NVARCHAR(20) NOT NULL,
user_birth CHAR(10) NOT NULL COMMENT '생년월일 : 입력형식 YYYY-MM-DD',
user_gender char(1) not null comment 'M:남성 , F:여성',
user_phone VARCHAR(11) NOT NULL COMMENT '휴대폰번호 : (-)없이 숫자만',
user_email VARCHAR(50) NOT NULL,
user_zipcode INT NOT NULL,
user_address1 VARCHAR(100) NOT NULL,
user_address2 VARCHAR(60) NULL,
user_joindate TIMESTAMP NOT NULL DEFAULT now(),
use_YN CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '탈퇴유무',
user_expiredate TIMESTAMP NULL,
PRIMARY KEY (user_id));

ALTER TABLE tbl_user
MODIFY COLUMN user_expiredate TIMESTAMP NULL;

update tbl_user set user_email='lljinny@naver.com' where user_id='test12345';

select * from tbl_user;

/* 관리자 id:admin , pw:adm!Q2w#E4r */
insert into tbl_user(user_category,user_id,user_pw,user_name,user_birth,user_gender,user_phone,user_zipcode,user_address1,user_address2,user_email) 
values('admin','admin','BB8EF2DE6CF9EB9357AB2599BFE4B3BA7100EF50DB31F90086567663BD0FC1C9','관리자','1970-01-01','M','01000000000',42612,'대구 달서구 달구벌대로251안길 15','601호','admin@admin.com');

/* 딜러 id:d230001 , pw:dl123456 */
insert into tbl_user(user_category,user_id,user_pw,user_name,user_birth,user_gender,user_phone,user_zipcode,user_address1,user_address2,user_email) 
values('dealer','d230001','3BC8ACF3556C6A5625520883F6479326F575D76C2129BB938990D9C8488CF22C','제임스','1980-07-01','M','01011223344',42612,'대구 달서구 달구벌대로251안길 15','601호','james@cureengine.com');

/* 딜러 id:d230002 , pw:dl123456 */
insert into tbl_user(user_category,user_id,user_pw,user_name,user_birth,user_gender,user_phone,user_zipcode,user_address1,user_address2,user_email) 
values('dealer','d230002','3BC8ACF3556C6A5625520883F6479326F575D76C2129BB938990D9C8488CF22C','앤드류','1990-03-26','M','01099998765',42612,'대구 달서구 달구벌대로251안길 15','601호','andew@cureengine.com');

/* 딜러 id:d230003 , pw:dl123456 */
insert into tbl_user(user_category,user_id,user_pw,user_name,user_birth,user_gender,user_phone,user_zipcode,user_address1,user_address2,user_email) 
values('dealer','d230003','3BC8ACF3556C6A5625520883F6479326F575D76C2129BB938990D9C8488CF22C','다니엘','1987-11-10','M','01012345666',42612,'대구 달서구 달구벌대로251안길 15','601호','daniel@cureengine.com');
-- -----------------------------------------------------
-- tbl_wishlist : 관심상품
-- -----------------------------------------------------
CREATE TABLE tbl_wishlist (
car_id VARCHAR(10) NOT NULL,
car_image VARCHAR(45) NULL,
car_price INT NULL,
user_id VARCHAR(45) NOT NULL,
PRIMARY KEY (user_id, car_id),

CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),
CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES tbl_car (car_id));


select * from tbl_wishlist;

select * from tbl_wishlist where user_id='test7778';

-- -----------------------------------------------------
-- tbl_reservation : 시승예약
-- -----------------------------------------------------
/*resernum 문자열+숫자 자동증가를 위한 임시 테이블*/
create table tpm_resernum (
	resernum int not null auto_increment primary key
);

CREATE TABLE tbl_reservation (
resernum VARCHAR(8) NOT NULL DEFAULT '0',
car_id VARCHAR(10) NOT NULL,
user_id VARCHAR(45) NOT NULL,
dealer_id CHAR(7) NOT NULL,
rev_date DATE NOT NULL,
rev_time VARCHAR(45) NOT NULL,
approve_YN CHAR(1) NOT NULL DEFAULT 'N' COMMENT '예약승인(Y)/거절(N)',
PRIMARY KEY (resernum),
CONSTRAINT fk_rsv_car_id FOREIGN KEY (car_id) REFERENCES tbl_car (car_id),
CONSTRAINT fk_rsv_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id));

/* resernum 문자열+숫자 자동증가를 위한 trigger (trigger는 워크벤치로 생성) */
DELIMITER $$
CREATE TRIGGER testdb.tbl_reservation_resernum
BEFORE INSERT ON testdb.tbl_reservation
FOR EACH ROW
BEGIN
  INSERT INTO testdb.tpm_resernum VALUES (NULL);
  SET NEW.resernum = CONCAT('RES', LPAD(LAST_INSERT_ID(), 5, '0'));
END$$
DELIMITER ;

select * from tbl_reservation;

-- -----------------------------------------------------
-- tbl_deliveryfee : 탁송료
-- -----------------------------------------------------
CREATE TABLE tbl_deliveryfee (
region NVARCHAR(30) NOT NULL,
fee INT NOT NULL,
PRIMARY KEY (region));

insert into tbl_deliveryfee values ('서울특별시',300000);
insert into tbl_deliveryfee values ('경기도',240000);
insert into tbl_deliveryfee values ('강원도',350000);
insert into tbl_deliveryfee values ('충청북도',200000);
insert into tbl_deliveryfee values ('충청남도',250000);
insert into tbl_deliveryfee values ('대전광역시',160000);
insert into tbl_deliveryfee values ('부산광역시',150000);
insert into tbl_deliveryfee values ('울산광역시',120000);
insert into tbl_deliveryfee values ('대구광역시',50000);
insert into tbl_deliveryfee values ('광주광역시',250000);
insert into tbl_deliveryfee values ('경상북도',130000);
insert into tbl_deliveryfee values ('경상남도',100000);
insert into tbl_deliveryfee values ('전라북도',200000);
insert into tbl_deliveryfee values ('전라남도',260000);
insert into tbl_deliveryfee values ('제주도',500000);

-- -----------------------------------------------------
-- tbl_coupon : 쿠폰
-- -----------------------------------------------------
CREATE TABLE tbl_coupon (
coupon_id CHAR(10) NOT NULL COMMENT '로직에서처리 10자리 난수 생성',
user_id VARCHAR(45) NOT NULL,
discount_rate INT NOT NULL,
coupon_expiredate DATETIME DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (coupon_id),
CONSTRAINT fk_cp_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id));

select * from tbl_coupon;
-- -----------------------------------------------------
-- tbl_order : 주문하기
-- -----------------------------------------------------
drop table tbl_order;

/*ordernum 문자열+숫자 자동증가를 위한 임시 테이블*/
create table tpm_ordernum (
	ordernum int not null auto_increment primary key
);

CREATE TABLE tbl_order (
ordernum VARCHAR(8) NOT NULL DEFAULT '0',
car_id VARCHAR(10) NOT NULL,
user_id VARCHAR(45) NOT NULL,
coupon_id CHAR(10) NULL,
order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
car_price INT NOT NULL,
region NVARCHAR(30) NOT NULL,
deliveryfee INT NOT NULL,
user_address1 VARCHAR(100) NOT NULL,
user_address2 VARCHAR(60) NULL,
user_phone VARCHAR(11) NOT NULL,
payment INT NOT NULL COMMENT '결제방법 1:현금 2:카드',
order_approve_YN CHAR(1) NOT NULL DEFAULT 'N' COMMENT '주문승인',
PRIMARY KEY (ordernum),

CONSTRAINT fk_od_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),
CONSTRAINT fk_od_coupon_id FOREIGN KEY (coupon_id) REFERENCES tbl_coupon (coupon_id));

/* ordernum 문자열+숫자 자동증가를 위한 trigger (trigger는 워크벤치로 생성) */
DELIMITER $$
CREATE TRIGGER testdb.tbl_order_ordernum
BEFORE INSERT ON testdb.tbl_order
FOR EACH ROW
BEGIN
  INSERT INTO testdb.tpm_ordernum VALUES (NULL);
  SET NEW.ordernum = CONCAT('ORD', LPAD(LAST_INSERT_ID(), 5, '0'));
END$$
DELIMITER ;

select * from tbl_order;

-- -----------------------------------------------------
-- tbl_qna : 질문게시판
-- -----------------------------------------------------
DROP TABLE tbl_qna;
/* qna_num 문자열+숫자 자동증가를 위한 임시 테이블*/
create table tpm_qnanum (
	qna_num int not null auto_increment primary key
);

CREATE TABLE tbl_qna (
qna_num VARCHAR(8) NOT NULL DEFAULT '0',
user_id VARCHAR(45) NOT NULL,
car_id VARCHAR(10) NOT NULL,
qna_pw VARCHAR(45) NULL,
qna_title VARCHAR(100) NOT NULL,
qna_content VARCHAR(1000) NOT NULL,
qna_file NVARCHAR(20) NULL,
qna_date DATETIME DEFAULT CURRENT_TIMESTAMP,
secret_YN CHAR(1) NOT NULL,
qna_hit INT NOT NULL DEFAULT 0,
PRIMARY KEY (qna_num),
CONSTRAINT fk_qna_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),
CONSTRAINT fk_qna_car_id FOREIGN KEY (car_id) REFERENCES tbl_car (car_id));

/* qna_num 문자열+숫자 자동증가를 위한 trigger (trigger는 워크벤치로 생성) */
DELIMITER $$
CREATE TRIGGER testdb.tbl_qna_qnanum
BEFORE INSERT ON testdb.tbl_qna
FOR EACH ROW
BEGIN
  INSERT INTO testdb.tpm_qnanum VALUES (NULL);
  SET NEW.qna_num = CONCAT('QNA', LPAD(LAST_INSERT_ID(), 5, '0'));
END$$
DELIMITER ;

select * from tbl_qna;

-- -----------------------------------------------------
-- tbl_notice : 공지사항
-- -----------------------------------------------------
DROP TABLE tbl_notice;
/* notice_num 문자열+숫자 자동증가를 위한 임시 테이블*/
create table tpm_noticenum (
	notice_num int not null auto_increment primary key
);


CREATE TABLE tbl_notice (
notice_num VARCHAR(8) NOT NULL DEFAULT '0',
user_id VARCHAR(45) NOT NULL,
notice_title VARCHAR(100) NOT NULL,
notice_content VARCHAR(1000) NOT NULL,
notice_file VARCHAR(100) NULL,
notice_date DATETIME DEFAULT CURRENT_TIMESTAMP,
notice_hit INT NOT NULL DEFAULT 0,
PRIMARY KEY (notice_num),
CONSTRAINT fk_ntc_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id));

/* notice_num 문자열+숫자 자동증가를 위한 trigger (trigger는 워크벤치로 생성) */
DELIMITER $$
CREATE TRIGGER testdb.tbl_qna_noticenum
BEFORE INSERT ON testdb.tbl_notice
FOR EACH ROW
BEGIN
  INSERT INTO testdb.tpm_noticenum VALUES (NULL);
  SET NEW.notice_num = CONCAT('NTC', LPAD(LAST_INSERT_ID(), 5, '0'));
END$$
DELIMITER ;

select * from tbl_notice;

-- -----------------------------------------------------
-- tbl_review : 리뷰게시판
-- -----------------------------------------------------
/* review_num 문자열+숫자 자동증가를 위한 임시 테이블*/
create table tpm_reviewnum (
	review_num int not null auto_increment primary key
);

CREATE TABLE tbl_review (
review_num VARCHAR(8) NOT NULL DEFAULT '0',
car_id VARCHAR(10) NOT NULL,
user_id VARCHAR(45) NOT NULL,
ordernum VARCHAR(8) NOT NULL,
review_pw VARCHAR(45) NOT NULL,
review_title VARCHAR(100) NOT NULL,
review_content VARCHAR(1000) NOT NULL,
review_file1 VARCHAR(100) NULL,
review_file2 VARCHAR(100) NULL,
review_file3 VARCHAR(100) NULL,
review_date DATETIME DEFAULT CURRENT_TIMESTAMP,
review_hit INT NOT NULL DEFAULT 0,
PRIMARY KEY (review_num),
CONSTRAINT fk_rv_car_id FOREIGN KEY (car_id) REFERENCES tbl_car (car_id),
CONSTRAINT fk_rv_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),
CONSTRAINT fk_rv_ordernum FOREIGN KEY (ordernum) REFERENCES tbl_order (ordernum));

/* review_num 문자열+숫자 자동증가를 위한 trigger (trigger는 워크벤치로 생성) */
DELIMITER $$
CREATE TRIGGER testdb.tbl_review_reviewnum
BEFORE INSERT ON testdb.tbl_review
FOR EACH ROW
BEGIN
  INSERT INTO testdb.tpm_reviewnum VALUES (NULL);
  SET NEW.review_num = CONCAT('REV', LPAD(LAST_INSERT_ID(), 5, '0'));
END$$
DELIMITER ;

select * from tbl_review;

-- -----------------------------------------------------
-- tbl_payment : 결재방법
-- -----------------------------------------------------
CREATE TABLE tbl_payment (
ordernum VARCHAR(8) NOT NULL,
pay_by INT NOT NULL COMMENT '1:계좌이체(현금) 2:신용카드',
pay_price INT NOT NULL,
pay_depositor_name VARCHAR(45) NULL COMMENT '현금결제일때 입금자명',
pay_creditcard_name INT(16) NULL COMMENT '카드회사이름',
pay_creditcard_num INT(16) NULL COMMENT '카드결제시 카드번호 16자리',
pay_creditcard_cvc INT(3) NULL COMMENT '카드결제시 cvc 번호 3자리',
CONSTRAINT fk_pay_ordernum FOREIGN KEY (ordernum) REFERENCES tbl_order (ordernum));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
