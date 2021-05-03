-- MySQL Script generated by MySQL Workbench
-- Fri Jun 12 07:42:21 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema detai3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema detai3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `detai3` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `detai3` ;

-- -----------------------------------------------------
-- Table `detai3`.`thue_ca_si`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`thue_ca_si` (
  `id_thue_ca_si` INT NOT NULL AUTO_INCREMENT,
  `ten_dv_thue_ca_si` VARCHAR(45) NOT NULL,
  `gia_thue` DECIMAL(10,0) NOT NULL,
  `ten_ca_si` VARCHAR(45) NOT NULL,
  `so_luong_bai` INT NOT NULL,
  PRIMARY KEY (`id_thue_ca_si`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`sanh_cuoi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`sanh_cuoi` (
  `id_sanh` CHAR(4) NOT NULL,
  `ten_sanh` VARCHAR(45) NOT NULL,
  `vi_tri` INT NOT NULL,
  `suc_chua` INT NOT NULL,
  `gia_sang_ngay_thuong` DECIMAL(10,0) NOT NULL,
  `gia_chieu_ngay_thuong` DECIMAL(10,0) NOT NULL,
  `gia_toi_ngay_thuong` DECIMAL(10,0) NOT NULL,
  `gia_sang_cuoi_tuan` DECIMAL(10,0) NOT NULL,
  `gia_chieu_cuoi_tuan` DECIMAL(10,0) NOT NULL,
  `gia_toi_cuoi_tuan` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_sanh`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`hoa_don`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`hoa_don` (
  `id_hoa_don` INT NOT NULL,
  `id_sanh` CHAR(4) NOT NULL,
  `ten_buoi_tiec` VARCHAR(45) NOT NULL,
  `ten_sanh` VARCHAR(45) NOT NULL,
  `thoi_diem` VARCHAR(45) NOT NULL,
  `gia_sanh` DECIMAL(10,0) NOT NULL,
  `ngay_thue` DATE NOT NULL,
  `tong_tien` DECIMAL(15,0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_hoa_don`),
  INDEX `fk_sanhid` (`id_sanh` ASC) VISIBLE,
  CONSTRAINT `fk_sanhid`
    FOREIGN KEY (`id_sanh`)
    REFERENCES `detai3`.`sanh_cuoi` (`id_sanh`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`hd_ca_si`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`hd_ca_si` (
  `id_hd` INT NOT NULL,
  `id_ca_si` INT NOT NULL,
  `ten_dv_cs` VARCHAR(45) NOT NULL,
  `gia_thue` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_hd`, `id_ca_si`),
  INDEX `fk_casi_idx` (`id_ca_si` ASC) VISIBLE,
  CONSTRAINT `fk_casi`
    FOREIGN KEY (`id_ca_si`)
    REFERENCES `detai3`.`thue_ca_si` (`id_thue_ca_si`),
  CONSTRAINT `fk_hoadon`
    FOREIGN KEY (`id_hd`)
    REFERENCES `detai3`.`hoa_don` (`id_hoa_don`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`karaoke`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`karaoke` (
  `id_karaoke` INT NOT NULL AUTO_INCREMENT,
  `ten_karaoke` VARCHAR(45) NOT NULL,
  `gia_karaoke` DECIMAL(10,0) NOT NULL,
  `thoi_gian` TIME NOT NULL,
  PRIMARY KEY (`id_karaoke`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`hd_kara`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`hd_kara` (
  `id_hd` INT NOT NULL,
  `id_kara` INT NOT NULL,
  `ten_kara` VARCHAR(45) NOT NULL,
  `gia_kara` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_hd`, `id_kara`),
  INDEX `fk_kara_idx` (`id_kara` ASC) VISIBLE,
  CONSTRAINT `fk_hoadoncs`
    FOREIGN KEY (`id_hd`)
    REFERENCES `detai3`.`hoa_don` (`id_hoa_don`),
  CONSTRAINT `fk_kara`
    FOREIGN KEY (`id_kara`)
    REFERENCES `detai3`.`karaoke` (`id_karaoke`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`thuc_an`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`thuc_an` (
  `id_thuc_an` INT NOT NULL AUTO_INCREMENT,
  `ten_thuc_an` VARCHAR(45) NOT NULL,
  `gia_thuc_an` DECIMAL(10,0) NOT NULL,
  `an_chay` BIT(1) NOT NULL,
  PRIMARY KEY (`id_thuc_an`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`hd_thuc_an`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`hd_thuc_an` (
  `id_hd` INT NOT NULL,
  `id_thuc_an` INT NOT NULL,
  `ten_thuc_an` VARCHAR(45) NOT NULL,
  `gia_thuc_an` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_hd`, `id_thuc_an`),
  INDEX `fk_thuc_an_idx` (`id_thuc_an` ASC) VISIBLE,
  CONSTRAINT `fk_thuc_an`
    FOREIGN KEY (`id_thuc_an`)
    REFERENCES `detai3`.`thuc_an` (`id_thuc_an`),
  CONSTRAINT `kf_hoadonan`
    FOREIGN KEY (`id_hd`)
    REFERENCES `detai3`.`hoa_don` (`id_hoa_don`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`thuc_uong`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`thuc_uong` (
  `id_thuc_uong` INT NOT NULL AUTO_INCREMENT,
  `ten_thuc_uong` VARCHAR(45) NOT NULL,
  `gia_thuc_uong` DECIMAL(10,0) NOT NULL,
  `hsx` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_thuc_uong`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`hd_thuc_uong`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`hd_thuc_uong` (
  `id_hd` INT NOT NULL,
  `id_thuc_uong` INT NOT NULL,
  `ten_thuc_uong` VARCHAR(45) NOT NULL,
  `gia_thuc_uong` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_hd`, `id_thuc_uong`),
  INDEX `fk_uong_idx` (`id_thuc_uong` ASC) VISIBLE,
  CONSTRAINT `fk_hoadon_uong`
    FOREIGN KEY (`id_hd`)
    REFERENCES `detai3`.`hoa_don` (`id_hoa_don`),
  CONSTRAINT `fk_uong`
    FOREIGN KEY (`id_thuc_uong`)
    REFERENCES `detai3`.`thuc_uong` (`id_thuc_uong`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`trang_tri_phoi_canh`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`trang_tri_phoi_canh` (
  `id_trang_tri_phoi_canh` INT NOT NULL AUTO_INCREMENT,
  `ten_phoi_canh` VARCHAR(45) NOT NULL,
  `gia_phoi_canh` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_trang_tri_phoi_canh`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `detai3`.`hd_tt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detai3`.`hd_tt` (
  `id_hd` INT NOT NULL,
  `id_tt` INT NOT NULL,
  `ten_tt` VARCHAR(45) NOT NULL,
  `gia_tt` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_hd`, `id_tt`),
  INDEX `fk_tt_idx` (`id_tt` ASC) VISIBLE,
  CONSTRAINT `fk_hdttt`
    FOREIGN KEY (`id_hd`)
    REFERENCES `detai3`.`hoa_don` (`id_hoa_don`),
  CONSTRAINT `fk_tt`
    FOREIGN KEY (`id_tt`)
    REFERENCES `detai3`.`trang_tri_phoi_canh` (`id_trang_tri_phoi_canh`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

USE `detai3` ;

-- -----------------------------------------------------
-- procedure sumBill
-- -----------------------------------------------------

DELIMITER $$
USE `detai3`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sumBill`(in idhd int)
BEGIN
	declare idSanh char(4);
    declare giaSanh decimal;
	declare sumMenu decimal;
    declare sumDV decimal;
    declare sumTong decimal;
    declare soBan int;
	declare sumAn decimal;
    declare sumUong decimal;
    declare sumKara decimal;
    declare sumCS decimal;
    declare sumTT decimal;
    
    set idSanh=(select id_sanh from hoa_don where id_hoa_don = idhd);
    
    set giaSanh = (select gia_sanh from hoa_don where id_hoa_don = idhd);

    
	set sumAn=	(select sum(gia_thuc_an) from hd_thuc_an where id_hd=idhd);
    IF sumAn  is null THEN
        SET sumAn =0;
    END IF;
	set sumUong =(select sum(gia_thuc_uong) from hd_thuc_uong where id_hd=idhd);
    IF sumUong  is null THEN
        SET sumUong =0;
    END IF;
    set sumKara =(select sum(gia_kara) from hd_kara where id_hd=idhd);    
	IF sumKara  is null THEN
        SET sumKara =0;
    END IF;
    set sumCS =(select sum(gia_thue) from hd_ca_si where id_hd=idhd);
    IF sumCS is null THEN
        SET sumCS =0;
    END IF;
    set sumTT =(select sum(gia_tt) from hd_tt where id_hd=idhd);
    IF sumTT  is null THEN
        SET sumTT =0;
    END IF;
    set soBan =(select suc_chua from sanh_cuoi where id_sanh=idSanh);
    set sumTong = giaSanh+(sumAn+sumUong)*soBan + sumKara+sumCS+sumTT;
    
    UPDATE hoa_don SET tong_tien = sumTong WHERE id_hoa_don = idhd;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sumQuy
-- -----------------------------------------------------

DELIMITER $$
USE `detai3`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sumQuy`(in nam int, quy int, out sum decimal)
BEGIN
	select sum(tong_tien) into sum
    from hoa_don 
    where quarter(ngay_thue) =quy and year(ngay_thue)=nam;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sumThang
-- -----------------------------------------------------

DELIMITER $$
USE `detai3`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sumThang`(in nam int, thang int, out sum decimal)
BEGIN
	select sum(tong_tien) into sum
    from hoa_don 
    where month(ngay_thue)= thang and year(ngay_thue)=nam;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;