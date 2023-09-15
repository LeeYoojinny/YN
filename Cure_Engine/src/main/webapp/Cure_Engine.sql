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
car_delete CHAR(1) NOT NULL DEFAULT 'N',
PRIMARY KEY (car_id));

alter table tbl_car drop column car_size;
alter table tbl_car alter column saleQty set default 1;
alter table tbl_car alter column car_like set default 0;
alter table tbl_car add column car_accident_detail VARCHAR(500) after car_content;
alter table tbl_car modify car_image2 NVARCHAR(100);
alter table tbl_car modify car_image1 NVARCHAR(100);
alter table tbl_car add column car_delete CHAR(1) NOT NULL DEFAULT 'N' after sale_YN;


update tbl_car set car_like=0 where car_id='111마1004';
update tbl_car set car_brand='maserati' where car_brand='Maserati' ;
update tbl_car set car_like=(car_like+1) where car_id='107허6655';

update tbl_car set car_color='blue' where car_id='104오1000';
update tbl_car set car_image2='X';
update tbl_car set car_like=(car_like-1) where (car_id='126아8989' or car_id='355어1111')
select * from tbl_car;

select car_id from tbl_car where car_brand='maserati' ;
select * from tbl_car where (car_price >=5000) and (car_price<=10000);
select * from tbl_car where sale_YN='Y' and car_brand='brand1'

delete from tbl_car where car_id='106오3333';

/* 차량검색 Test */
select *
from tbl_car
where (car_brand='benz' or car_brand='tesla') and (car_color='black' or car_color='white') and (car_type like '%SUV%');

select * from tbl_car where car_type like '%SUV%'
select * from tbl_car where (car_brand='tesla' or car_brand='maserati') and  (car_color='black' or car_color='white') and  (car_type like '%SUV%')
select * from tbl_car where (car_brand='benz' or car_brand='tesla' or car_brand='ferrari' or car_brand='maserati') and  (car_color='black' or car_color='white' or car_color='red') and  (car_type like '%SUV%')
-- -----------------------------------------------------
-- tbl_code_master : 차량옵션, 사고내용 등 
-- -----------------------------------------------------
DROP TABLE tbl_code_master;

CREATE TABLE tbl_code_master (
code_category VARCHAR(45) NOT NULL COMMENT 'option,accident_detail',
code_name VARCHAR(45) NOT NULL COMMENT 'option1,option2,option3...',
code_value NVARCHAR(90) NOT NULL COMMENT '선루프,HID헤드램프,전동 트렁크...',
PRIMARY KEY (code_category,code_name)
);

select * from tbl_code_master where code_category like '%option%';
select * from tbl_code_master where code_category='car_accident'

delet from tbl_code_master where code_category='car_accident'

insert into tbl_code_master values('car_option_outIn','outIn1','선루프');
insert into tbl_code_master values('car_option_outIn','outIn2','HID헤드램프');
insert into tbl_code_master values('car_option_outIn','outIn3','전동 트렁크');
insert into tbl_code_master values('car_option_outIn','outIn4','전동 사이드미러');
insert into tbl_code_master values('car_option_outIn','outIn5','알루미늄휠');
insert into tbl_code_master values('car_option_outIn','outIn6','열선핸들');
insert into tbl_code_master values('car_option_outIn','outIn7','하이패스');
insert into tbl_code_master values('car_option_safety','safety1','에어백(운전석,동승석)');
insert into tbl_code_master values('car_option_safety','safety2','ABS');
insert into tbl_code_master values('car_option_safety','safety3','차선이탈경보');
insert into tbl_code_master values('car_option_safety','safety4','후방카메라');
insert into tbl_code_master values('car_option_safety','safety5','전방센서');
insert into tbl_code_master values('car_option_safety','safety6','측면센서');
insert into tbl_code_master values('car_option_safety','safety7','후방센서');
insert into tbl_code_master values('car_option_convi','convi1','크루즈컨트롤');
insert into tbl_code_master values('car_option_convi','convi2','전자식 주차브레이크');
insert into tbl_code_master values('car_option_convi','convi3','HUD');
insert into tbl_code_master values('car_option_convi','convi4','스마트키');
insert into tbl_code_master values('car_option_convi','convi5','내비게이션');
insert into tbl_code_master values('car_option_convi','convi6','자동에어컨');
insert into tbl_code_master values('car_option_convi','convi7','블루투스');
insert into tbl_code_master values('car_option_seat','seat1','가죽시트');
insert into tbl_code_master values('car_option_seat','seat2','전동시트');
insert into tbl_code_master values('car_option_seat','seat3','열선시트');
insert into tbl_code_master values('car_option_seat','seat4','통풍시트');
insert into tbl_code_master values('car_option_seat','seat5','메모리시트');
insert into tbl_code_master values('car_accident_dv','division1','내차피해');
insert into tbl_code_master values('car_accident_dv','division2','타차가해');
insert into tbl_code_master values('car_accident_sp','special1','전손');
insert into tbl_code_master values('car_accident_sp','special2','침수(전손, 분손)');
insert into tbl_code_master values('car_accident_sp','special3','도난');
insert into tbl_code_master values('car_accident_rp','repair1','전방');
insert into tbl_code_master values('car_accident_rp','repair2','후방');
insert into tbl_code_master values('car_accident_rp','repair3','도어(운전석측)');
insert into tbl_code_master values('car_accident_rp','repair4','도어(조수석측)');
insert into tbl_code_master values('car_accident_rp','repair5','루프');
insert into tbl_code_master values('car_accident_rp','repair6','플로어');
insert into tbl_code_master values('car_accident_rp','repair7','휠하우스');
insert into tbl_code_master values('car_type','regular1','경차');
insert into tbl_code_master values('car_type','regular2','소/준중형');
insert into tbl_code_master values('car_type','regular3','중형');
insert into tbl_code_master values('car_type','regular4','대형');
insert into tbl_code_master values('car_type','sports1','스포츠카');
insert into tbl_code_master values('car_type','SUV1','소형SUV');
insert into tbl_code_master values('car_type','SUV2','중형SUV');
insert into tbl_code_master values('car_type','SUV3','대형SUV');
insert into tbl_code_master values('car_type','RV1','소형RV');
insert into tbl_code_master values('car_type','RV2','대형RV');
insert into tbl_code_master values('car_transmission','tm1','오토');
insert into tbl_code_master values('car_transmission','tm2','수동');
insert into tbl_code_master values('car_fuel','fuel1','가솔린');
insert into tbl_code_master values('car_fuel','fuel2','디젤');
insert into tbl_code_master values('car_fuel','fuel3','하이브리드');
insert into tbl_code_master values('car_fuel','fuel4','전기');
insert into tbl_code_master values('car_fuel','fuel5','수소');
insert into tbl_code_master values('car_color','color1','검정색');
insert into tbl_code_master values('car_color','color2','흰색');
insert into tbl_code_master values('car_color','color3','회색');
insert into tbl_code_master values('car_color','color4','빨간색');
insert into tbl_code_master values('car_color','color5','파란색');
insert into tbl_code_master values('car_color','color6','노란색');
insert into tbl_code_master values('car_color','color7','기타');
insert into tbl_code_master values('car_brand','brand1','벤츠');
insert into tbl_code_master values('car_brand','brand2','테슬라');
insert into tbl_code_master values('car_brand','brand3','페라리');
insert into tbl_code_master values('car_brand','brand4','BMW');
insert into tbl_code_master values('car_brand','brand5','아우디');
insert into tbl_code_master values('car_brand','brand6','마세라티');
insert into tbl_code_master values('car_brand','brand7','벤틀리');
insert into tbl_code_master values('car_brand','brand8','캐딜락');
insert into tbl_code_master values('qna_subject','q1','차량 관련 문의');
insert into tbl_code_master values('qna_subject','q2','탁송 관련 문의');
insert into tbl_code_master values('qna_subject','q3','딜러 관련 문의');
insert into tbl_code_master values('qna_subject','q4','서비스 관련 문의');
insert into tbl_code_master values('qna_subject','q5','기타 문의');
insert into tbl_code_master values('user_name','admin','관리자');
insert into tbl_code_master values('user_name','d230001','제임스');
insert into tbl_code_master values('user_name','d230002','앤드류');
insert into tbl_code_master values('user_name','d230003','다니엘');

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
select * from tbl_user where user_category='dealer' and use_YN='N' limit 0,5
update tbl_user set user_joindate=now() where user_id='d230004';

select * from tbl_user;
select max(user_id) from tbl_user where user_category='dealer';
select count(*) from tbl_user where user_category='dealer' and use_YN='N'
select * from tbl_user where user_category='customer' and use_YN='Y' order by user_joindate


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
car_delete CHAR(1) NOT NULL DEFAULT 'N',
PRIMARY KEY (user_id, car_id),

CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),
CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES tbl_car (car_id));


select * from tbl_wishlist;

select * from tbl_wishlist where user_id='test7778';

delete from tbl_wishlist where (car_id='111사7894' or car_id='355어1111') and user_id='test11111';
delete from tbl_wishlist where user_id='test11111' and (car_id='41로9999' or car_id='123허6666')
alter table tbl_wishlist add column car_delete CHAR(1) NOT NULL DEFAULT 'N' after user_id;



select distinct w.car_id
from tbl_wishlist w inner join tbl_car c
on w.car_id = c.car_id
where car_like > 0 and (w.car_id='123허6666' or w.car_id='71가9797')
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

alter table tbl_reservation modify column approve_YN CHAR(1) NOT NULL DEFAULT 'W';
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
user_zipcode INT NOT NULL,
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

alter table tbl_order add column user_zipcode INT NOT NULL after deliveryfee;


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
qna_viewNum int not NULL,
qna_replyNum int not NULL DEFAULT 0,
user_id VARCHAR(45) NOT NULL,
car_id VARCHAR(10),
qna_pw VARCHAR(45) NULL,
qna_title VARCHAR(100) NOT NULL,
qna_content VARCHAR(1000) NOT NULL,
qna_file NVARCHAR(20) NULL,
qna_file_origin NVARCHAR(20) NULL,
qna_date DATETIME DEFAULT CURRENT_TIMESTAMP,
secret_YN CHAR(1) NOT NULL,
reply_YN CHAR(1) NOT NULL DEFAULT 'N',
qna_hit INT NOT NULL DEFAULT 0,
PRIMARY KEY (qna_num),
CONSTRAINT fk_qna_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),

select * from tbl_qna;

alter table tbl_qna modify column car_id VARCHAR(10);
alter table tbl_qna add column qna_file_origin NVARCHAR(20) NULL after qna_file;
alter table tbl_qna add column qna_viewNum int not NULL after qna_num;
alter table tbl_qna add column qna_replyNum int not NULL DEFAULT 0 after qna_viewNum;
alter table tbl_qna add column reply_YN CHAR(1) NOT NULL DEFAULT 'N' after secret_YN;

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

select count(*) from tbl_qna where qna_num='QNA00001' and qna_pw='1234';
update tbl_qna 

select count(*), from tbl_qna where qna_title like '%문의%' or qna_content like '%문의%'


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
notice_file_origin VARCHAR(100) NULL,
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

alter table tbl_notice add column notice_file_origin VARCHAR(100) NULL after notice_file;


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
pay_creditcard_name VARCHAR(45) NULL COMMENT '카드회사이름',
pay_creditcard_num INT(16) NULL COMMENT '카드결제시 카드번호 16자리',
pay_creditcard_cvc INT(3) NULL COMMENT '카드결제시 cvc 번호 3자리',
CONSTRAINT fk_pay_ordernum FOREIGN KEY (ordernum) REFERENCES tbl_order (ordernum));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

alter table tbl_payment modify pay_creditcard_name VARCHAR(45) NULL;
