-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.5.8-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- okulveri için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `okulveri` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `okulveri`;

-- tablo yapısı dökülüyor okulveri.dersler
CREATE TABLE IF NOT EXISTS `dersler` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DersAd` varchar(200) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- okulveri.dersler: ~13 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `dersler` DISABLE KEYS */;
INSERT INTO `dersler` (`ID`, `DersAd`) VALUES
	(1, 'Java Programlama'),
	(2, 'Web Programla'),
	(4, 'Kablosuz Haberlesme'),
	(5, 'Yapay Zeka'),
	(6, 'Sayisal Kontrol'),
	(8, 'Gorsel Programlama'),
	(10, 'Fizik'),
	(14, 'Diferansiyel'),
	(18, 'Bulanik Mantik'),
	(21, 'matematik'),
	(22, 'Erp Sistemleri'),
	(24, 'mesleki ingilizce'),
	(26, 'Matematik');
/*!40000 ALTER TABLE `dersler` ENABLE KEYS */;

-- tablo yapısı dökülüyor okulveri.ogrenci
CREATE TABLE IF NOT EXISTS `ogrenci` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tcno` varchar(15) NOT NULL,
  `isim` varchar(55) DEFAULT NULL,
  `type` enum('normalogr','ikinciogr') DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- okulveri.ogrenci: ~10 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ogrenci` DISABLE KEYS */;
INSERT INTO `ogrenci` (`id`, `tcno`, `isim`, `type`, `password`) VALUES
	(1, '5555', 'Nesrin Sular', 'ikinciogr', '5555'),
	(2, '7894', 'Alper Yalcin', 'normalogr', '4789'),
	(3, '8524', 'Elif Vatansever', 'normalogr', '1256'),
	(4, '1234', 'Sema Bulat', 'ikinciogr', '1234'),
	(5, '3535', 'Kubra Sular', 'normalogr', '3547'),
	(16, '1251', 'Seckin Bulgur', 'normalogr', '4578'),
	(18, '4242', 'Cinar Sirik', 'normalogr', '6414'),
	(21, '1454', 'Ece Simsek', 'ikinciogr', '1212'),
	(22, '4562', 'Ali Kurt', 'ikinciogr', '8963'),
	(23, '4874', 'GunayTemur', 'ikinciogr', '4898');
/*!40000 ALTER TABLE `ogrenci` ENABLE KEYS */;

-- tablo yapısı dökülüyor okulveri.ogrenciders
CREATE TABLE IF NOT EXISTS `ogrenciders` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `OgrenciID` int(11) DEFAULT NULL,
  `DerslerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- okulveri.ogrenciders: ~19 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ogrenciders` DISABLE KEYS */;
INSERT INTO `ogrenciders` (`ID`, `OgrenciID`, `DerslerID`) VALUES
	(1, 5, 10),
	(2, 14, 10),
	(3, 9, 1),
	(4, 5, 8),
	(5, 15, 8),
	(6, 5, 4),
	(7, 9, 14),
	(8, 3, 2),
	(9, 5, 2),
	(10, 14, 2),
	(11, 7, 5),
	(12, 5, 1),
	(13, 14, 4),
	(14, 3, 6),
	(15, 4, 6),
	(16, 1, 6),
	(17, 2, 6),
	(18, 16, 6),
	(19, 22, 6);
/*!40000 ALTER TABLE `ogrenciders` ENABLE KEYS */;

-- tablo yapısı dökülüyor okulveri.ogretmen
CREATE TABLE IF NOT EXISTS `ogretmen` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `OgrtAdSoyad` varchar(200) NOT NULL,
  `OgrtTC` varchar(11) NOT NULL,
  `OgrtPass` varchar(8) NOT NULL,
  `Unvan` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- okulveri.ogretmen: ~11 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ogretmen` DISABLE KEYS */;
INSERT INTO `ogretmen` (`ID`, `OgrtAdSoyad`, `OgrtTC`, `OgrtPass`, `Unvan`) VALUES
	(12, 'Fadime Demircan', '5555', '5555', 'Profesor'),
	(13, 'Ali Calhan', '2525', '2525', 'Ogretim Gorevlisi'),
	(19, 'Alper Halil Yalcin', '2525', '2525', 'Profesor'),
	(22, 'Leyla Hoca', '8888', '8888', 'Profesor'),
	(23, 'Kubra Sular', '4444', '4444', 'Profesor'),
	(24, 'Mehmet Deniz', '5856', '5856', 'Ogretim Gorevlisi'),
	(25, 'Askim Sema', '1212', '1212', 'Profesor'),
	(27, 'Nesrin Sular', '1111', '1111', 'Profesor'),
	(30, 'Burcu Yilmaz', '7854', '6666', 'Docent'),
	(31, 'Taylan Hardal', '2525', '1907', 'Docent'),
	(33, 'Ayse Boz', '4545', '4545', 'Docent');
/*!40000 ALTER TABLE `ogretmen` ENABLE KEYS */;

-- tablo yapısı dökülüyor okulveri.ogretmenders
CREATE TABLE IF NOT EXISTS `ogretmenders` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `OgretmenID` int(10) DEFAULT NULL,
  `DerslerID` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- okulveri.ogretmenders: ~35 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ogretmenders` DISABLE KEYS */;
INSERT INTO `ogretmenders` (`ID`, `OgretmenID`, `DerslerID`) VALUES
	(2, 13, 4),
	(3, 12, 1),
	(4, 15, 2),
	(5, 19, 5),
	(6, 19, 4),
	(7, 15, 6),
	(8, 13, 2),
	(9, 12, 2),
	(10, 13, 5),
	(11, 20, 7),
	(12, 15, 4),
	(13, 13, 6),
	(14, 12, 6),
	(15, 13, 1),
	(16, 19, 9),
	(17, 22, 9),
	(18, 19, 8),
	(19, 22, 5),
	(20, 12, 5),
	(21, 25, 11),
	(22, 24, 11),
	(23, 24, 12),
	(24, 23, 12),
	(25, 12, 13),
	(26, 13, 13),
	(27, 19, 14),
	(28, 27, 14),
	(29, 19, 10),
	(30, 28, 10),
	(31, 30, 10),
	(32, 12, 12),
	(33, 24, 18),
	(34, 25, 14),
	(35, 24, 26),
	(36, 22, 26);
/*!40000 ALTER TABLE `ogretmenders` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
