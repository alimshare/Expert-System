-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.16 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for sispak_db
CREATE DATABASE IF NOT EXISTS `sispak_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sispak_db`;


-- Dumping structure for table sispak_db.gejala
CREATE TABLE IF NOT EXISTS `gejala` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) DEFAULT NULL,
  `pertanyaan` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- Dumping data for table sispak_db.gejala: ~17 rows (approximately)
/*!40000 ALTER TABLE `gejala` DISABLE KEYS */;
INSERT INTO `gejala` (`id`, `nama`, `pertanyaan`) VALUES
	(1, 'Demam Tinggi', 'Apakah anda mengalami demam tinggi ?'),
	(2, 'Sakit Kepala', 'Apakah anda mengalami sakit kepala ?'),
	(3, 'Mual', 'Apakah anda mengalami mual ?'),
	(4, 'Sakit Perut', 'Apakah anda mengalami sakit perut ?'),
	(5, 'Muntah', 'Apakah anda mengalami muntah ?'),
	(6, 'Nyeri Otot', 'Apakah anda mengalami nyeri otot ?'),
	(7, 'Bercak Merah', 'Apakah anda mengalami bercak-bercak merah ?'),
	(8, 'Diare', 'Apakah anda mengalami diare ?'),
	(9, 'Gusi Berdarah', 'Apakah anda mengalami gusi berdarah ?'),
	(10, 'Pusing', 'Apakah anda mengalami pusing ?'),
	(11, 'Tubuh Lemas', 'Apakah tubuh anda terasa lemas ?'),
	(12, 'Berat Badan Berkurang', 'Apakah berat badan anda berkurang ?'),
	(13, 'Nafsu Makan Menurun', 'Apakah nafsu makan anda menurun ?'),
	(14, 'Perut Panas', 'Apakah perut bagian atas anda terasa panas ?'),
	(15, 'Cepat Kenyang', 'Apakah anda cepat merasa kenyang'),
	(16, 'Kenyang Berkepanjangan', 'Apakah anda sering merasa kenyang yang berkepanjangan ?'),
	(17, 'Kembung', 'Apakah anda merasa kembung pada perut ?'),
	(18, 'Refluks', 'Apakah anda merasa cairan / makanan dari lambung naik ke kerongkongan ?'),
	(19, 'Sendawa', 'Apakah anda sering bersendawa ?');
/*!40000 ALTER TABLE `gejala` ENABLE KEYS */;


-- Dumping structure for table sispak_db.mapping_gejala
CREATE TABLE IF NOT EXISTS `mapping_gejala` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_masalah` int(11) DEFAULT NULL,
  `id_gejala` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Dumping data for table sispak_db.mapping_gejala: ~14 rows (approximately)
/*!40000 ALTER TABLE `mapping_gejala` DISABLE KEYS */;
INSERT INTO `mapping_gejala` (`id`, `id_masalah`, `id_gejala`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 2, 1),
	(5, 2, 2),
	(6, 3, 1),
	(7, 3, 2),
	(8, 3, 3),
	(9, 3, 4),
	(10, 4, 2),
	(11, 4, 3),
	(12, 4, 4),
	(13, 5, 6),
	(14, 5, 5);
/*!40000 ALTER TABLE `mapping_gejala` ENABLE KEYS */;


-- Dumping structure for table sispak_db.masalah
CREATE TABLE IF NOT EXISTS `masalah` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) DEFAULT NULL,
  `penyebab` text,
  `solusi` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table sispak_db.masalah: ~6 rows (approximately)
/*!40000 ALTER TABLE `masalah` DISABLE KEYS */;
INSERT INTO `masalah` (`id`, `nama`, `penyebab`, `solusi`) VALUES
	(1, 'Demam Berdarah', 'Sakit', 'Berobat ke Dokter'),
	(2, 'Tipes', 'Sakit', 'Berobat ke Dokter'),
	(3, 'Mag', 'Sakit', 'Berobat ke Dokter'),
	(4, 'Asma', 'Sakit', 'Berobat ke Dokter'),
	(5, 'Sinusitis', 'Sakit', 'Berobat ke Dokter'),
	(6, 'Hamil', 'Sakit', 'Berobat ke Dokter');
/*!40000 ALTER TABLE `masalah` ENABLE KEYS */;


-- Dumping structure for view sispak_db.view_jumlah_gejala
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `view_jumlah_gejala` (
	`id` INT(11) NOT NULL,
	`nama` VARCHAR(60) NULL COLLATE 'latin1_swedish_ci',
	`penyebab` TEXT NULL COLLATE 'latin1_swedish_ci',
	`solusi` TEXT NULL COLLATE 'latin1_swedish_ci',
	`jumlah_gejala` BIGINT(21) NOT NULL
) ENGINE=MyISAM;


-- Dumping structure for view sispak_db.view_jumlah_gejala
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `view_jumlah_gejala`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `view_jumlah_gejala` AS SELECT masalah.id,masalah.nama,masalah.penyebab, masalah.solusi,count(1) as jumlah_gejala
FROM masalah INNER JOIN mapping_gejala ON masalah.id=mapping_gejala.id_masalah 
GROUP BY id,nama 
ORDER BY jumlah_gejala DESC ;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
