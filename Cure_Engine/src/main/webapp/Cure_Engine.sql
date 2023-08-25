-- MySQL Script generated by MySQL Workbench
-- Fri Aug 25 15:08:59 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

drop database `mydb`; 

-- -----------------------------------------------------
-- Table `mydb`.`tbl_car`
-- -----------------------------------------------------
CREATE TABLE tbl_car (
  `dealer_id` CHAR(7) NOT NULL,
  `car_id` VARCHAR(10) NOT NULL COMMENT '차량번호 (예)123허4567',
  `car_brand` VARCHAR(45) NOT NULL COMMENT '제조사',
  `car_name` VARCHAR(100) NOT NULL COMMENT '자동차명+트림',
  `car_color` VARCHAR(45) NOT NULL,
  `car_price` INT NOT NULL,
  `car_capacity` INT NOT NULL COMMENT '배기량(cc)',
  `car_fuel` VARCHAR(45) NOT NULL COMMENT '가솔린,디젤,하이브리드,전기,수소',
  `car_transmission` VARCHAR(45) NOT NULL COMMENT '오토, 수동',
  `car_type` VARCHAR(45) NOT NULL COMMENT '경차, 세단, SUV',
  `car_size` VARCHAR(45) NOT NULL COMMENT '경형,소형,중형,대형',
  `car_year` INT NOT NULL COMMENT '연식',
  `car_accident` CHAR(1) NULL DEFAULT 'N' COMMENT '사고차량(Y) 무사고(N)',
  `car_distance` INT NOT NULL COMMENT '주행거리',
  `car_image` VARCHAR(45) NOT NULL,
  `saleQty` INT NOT NULL,
  `car_content` VARCHAR(500) NULL COMMENT '간단한 차량설명:사고이력, 옵션 등 체크박스로 처리',
  `like` INT NULL COMMENT '관심',
  `sale_YN` CHAR(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`car_id`))
ENGINE = InnoDB;

select * from `tbl_car`;
-- -----------------------------------------------------
-- Table `mydb`.`tbl_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_user` (
  `user_category` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `user_pw` VARCHAR(256) NOT NULL,
  `user_name` NVARCHAR(20) NOT NULL,
  `user_birth` CHAR(10) NOT NULL COMMENT '생년월일 : 입력형식 YYYY-MM-DD',
  `user_phone` VARCHAR(11) NOT NULL COMMENT '휴대폰번호 : (-)없이 숫자만',
  `user_zipcode` INT NOT NULL,
  `user_address1` VARCHAR(100) NOT NULL,
  `user_address2` VARCHAR(60) NULL,
  `user_email` VARCHAR(50) NOT NULL,
  `user_joindate` TIMESTAMP NOT NULL DEFAULT now(),
  `use_YN` CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '탈퇴유무',
  `user_expiredate` TIMESTAMP NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_wishlist` (
  `car_id` VARCHAR(10) NOT NULL,
  `car_image` VARCHAR(45) NULL,
  `car_price` INT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  INDEX `FK_user_id_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`, `car_id`),
  INDEX `fk_tbl_wishlist_tbl_car1_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `mydb`.`tbl_car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_reservation` (
  `reser_num` INT NOT NULL,
  `car_id` VARCHAR(10) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `dealer_id` CHAR(7) NOT NULL,
  `rev_date` DATE NOT NULL,
  `rev_time` VARCHAR(45) NOT NULL,
  `approve_YN` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '예약승인(Y)/거절(N)',
  PRIMARY KEY (`reser_num`),
  INDEX `fk_car_id_idx` (`car_id` ASC) VISIBLE,
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `mydb`.`tbl_car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_deliveryfee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_deliveryfee` (
  `region` NVARCHAR(30) NOT NULL,
  `fee` INT NOT NULL,
  PRIMARY KEY (`region`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_coupon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_coupon` (
  `coupon_id` CHAR(10) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `discount_rate` INT NOT NULL,
  PRIMARY KEY (`coupon_id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_order` (
  `ordernum` INT NOT NULL,
  `car_id` VARCHAR(10) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `coupon_id` CHAR(10) NULL,
  `order_date` DATE NOT NULL,
  `car_price` INT NOT NULL,
  `region` NVARCHAR(30) NOT NULL,
  `deliveryfee` INT NOT NULL,
  `user_address1` VARCHAR(100) NOT NULL,
  `user_address2` VARCHAR(60) NULL,
  `user_phone` VARCHAR(11) NOT NULL,
  `payment` INT NOT NULL COMMENT '결제방법 1:현금 2:카드',
  `order_approve_YN` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '주문승인',
  PRIMARY KEY (`car_id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `ordernum_UNIQUE` (`ordernum` ASC) VISIBLE,
  INDEX `fk_region_idx` (`region` ASC) VISIBLE,
  INDEX `fk_coupon_id_idx` (`coupon_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_region`
    FOREIGN KEY (`region`)
    REFERENCES `mydb`.`tbl_deliveryfee` (`region`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_coupon_id`
    FOREIGN KEY (`coupon_id`)
    REFERENCES `mydb`.`tbl_coupon` (`coupon_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_qna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_qna` (
  `qna_num` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `car_id` CHAR(7) NOT NULL,
  `qna_pw` VARCHAR(45) NULL,
  `qna_title` VARCHAR(100) NOT NULL,
  `qna_content` VARCHAR(1000) NOT NULL,
  `qna_file` VARCHAR(100) NULL,
  `qna_date` DATE NOT NULL,
  `secret_YN` CHAR(1) NOT NULL,
  `qna_hit` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`qna_num`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_car_id_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `mydb`.`tbl_car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_notice` (
  `notice_num` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `notice_title` VARCHAR(100) NOT NULL,
  `notice_content` VARCHAR(1000) NOT NULL,
  `notice_file` VARCHAR(100) NULL,
  `notice_date` DATE NOT NULL,
  `notice_hit` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`notice_num`),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_review` (
  `review_num` INT NOT NULL,
  `car_id` VARCHAR(10) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `ordernum` INT NOT NULL,
  `review_pw` VARCHAR(45) NOT NULL,
  `review_title` VARCHAR(100) NOT NULL,
  `review_content` VARCHAR(1000) NOT NULL,
  `review_file1` VARCHAR(100) NULL,
  `review_file2` VARCHAR(100) NULL,
  `review_file3` VARCHAR(100) NULL,
  `review_date` DATE NOT NULL,
  `review_hit` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`review_num`, `user_id`),
  INDEX `fk_car_id_idx` (`car_id` ASC) VISIBLE,
  INDEX `fk_ordernum_idx` (`ordernum` ASC) VISIBLE,
  CONSTRAINT `fk_car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `mydb`.`tbl_car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordernum`
    FOREIGN KEY (`ordernum`)
    REFERENCES `mydb`.`tbl_order` (`ordernum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbl_payment` (
  `ordernum` INT NOT NULL,
  `pay_by` INT NOT NULL,
  `pay_method` VARCHAR(45) NOT NULL,
  `pay_price` INT NOT NULL,
  `pay_depositor_name` VARCHAR(45) NULL COMMENT '현금결제일때 입금자명',
  `pay_creditcard_num` INT(16) NULL COMMENT '카드결제시 카드번호 16자리\n',
  `pay_creditcard_cvc` INT(3) NULL COMMENT '카드결제시 cvc 번호 3자리\n',
  INDEX `fk_ordernum_idx` (`ordernum` ASC) VISIBLE,
  CONSTRAINT `fk_ordernum`
    FOREIGN KEY (`ordernum`)
    REFERENCES `mydb`.`tbl_order` (`ordernum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
