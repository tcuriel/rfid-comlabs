-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 26, 2011 at 12:43 AM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `absenpti`
--

-- --------------------------------------------------------

--
-- Table structure for table `absen`
--

CREATE TABLE IF NOT EXISTS `absen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nim` int(11) DEFAULT NULL,
  `idkelas` int(11) DEFAULT NULL,
  `pertemuan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `absen`
--

INSERT INTO `absen` (`id`, `nim`, `idkelas`, `pertemuan`) VALUES
(1, 16210001, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE IF NOT EXISTS `kelas` (
  `id` int(11) NOT NULL,
  `fakultas` varchar(45) DEFAULT NULL,
  `shift` varchar(45) DEFAULT NULL,
  `kelas` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelas`
--


-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `nim` int(11) NOT NULL,
  `idkelas` int(11) DEFAULT NULL,
  PRIMARY KEY (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `idkelas`) VALUES
(16210001, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pertemuan`
--

CREATE TABLE IF NOT EXISTS `pertemuan` (
  `idkelas` int(11) NOT NULL,
  `pertemuan` int(11) NOT NULL,
  `mulai` datetime DEFAULT NULL,
  `akhir` datetime DEFAULT NULL,
  PRIMARY KEY (`idkelas`,`pertemuan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pertemuan`
--

INSERT INTO `pertemuan` (`idkelas`, `pertemuan`, `mulai`, `akhir`) VALUES
(1, 1, '2011-07-26 01:58:40', '2011-07-27 23:35:55'),
(2, 2, '2011-07-26 01:58:40', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
