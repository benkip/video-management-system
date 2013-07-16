-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 16, 2013 at 06:27 AM
-- Server version: 5.5.20
-- PHP Version: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `videostarlet`
--

-- --------------------------------------------------------

--
-- Table structure for table `producer`
--

CREATE TABLE IF NOT EXISTS `producer` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Dumping data for table `producer`
--

INSERT INTO `producer` (`prod_id`, `prod_name`) VALUES
(5, 'ziwa arts'),
(6, 'Kiwaya production'),
(7, 'Discovery media services'),
(9, 'Wang Teko Rach'),
(10, 'Serengiti Pictures'),
(11, 'Dexterity media'),
(12, 'Kikwetu productions'),
(13, 'Ace studio'),
(14, 'Chondo production'),
(15, 'Nduti onestop films'),
(16, 'Mangoma Players Production'),
(17, 'Kanyaga waya production'),
(18, 'Rubian films'),
(19, 'Cezmiq cast'),
(20, 'Wakambuni Production'),
(21, 'Wa Esther Film Productions'),
(22, 'B.Y.F.A.G Productions'),
(23, 'Sharp media Production'),
(24, 'Machangi production masters'),
(25, 'Joema Films'),
(26, 'Musyoka production'),
(27, 'Muthangari production'),
(28, 'JKN Productions'),
(29, 'Bob & Dob'),
(30, 'Nimu Production'),
(31, 'K-rO Media Solutions'),
(32, 'Efatha Promotions'),
(33, 'Kapoa Media'),
(34, 'Wide Concepts Africa'),
(35, 'Cathy films'),
(36, 'No producer'),
(37, 'papa shirandula'),
(38, 'mother in law'),
(39, 'tabasamu'),
(40, 'pranks go bad'),
(41, 'wambua'),
(42, 'Random kenya'),
(43, 'Documentary'),
(44, 'Naswa'),
(45, 'Nyangingo productions'),
(46, 'triple em films ');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `sup_id` int(11) NOT NULL AUTO_INCREMENT,
  `sup_name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`sup_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`sup_id`, `sup_name`) VALUES
(1, 'balozi '),
(2, 'Nduti'),
(3, 'machangi'),
(4, 'starletcentre'),
(5, 'papa shirandula'),
(6, 'mother in law'),
(7, 'Tabasamu'),
(8, 'pranks go bad'),
(9, 'kulahappy'),
(10, 'Random kenya'),
(11, 'Documentary'),
(12, 'Naswa');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `usr_id` int(250) NOT NULL AUTO_INCREMENT,
  `usr_name` varchar(200) NOT NULL DEFAULT '',
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`usr_id`,`usr_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE IF NOT EXISTS `video` (
  `vid_id` int(11) NOT NULL AUTO_INCREMENT,
  `vid_name` varchar(250) NOT NULL DEFAULT '',
  `date_received` date DEFAULT NULL,
  `date_published` date DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `sup_id` int(11) NOT NULL,
  `cat_id` int(11) NOT NULL,
  `prod_id` int(11) NOT NULL,
  `status` enum('Ripped','Unripped','Editted','Published') NOT NULL,
  PRIMARY KEY (`vid_id`,`vid_name`),
  KEY `sup_id` (`sup_id`),
  KEY `prod_id` (`prod_id`),
  KEY `cat_id` (`cat_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=423 ;

--
-- Dumping data for table `video`
--

INSERT INTO `video` (`vid_id`, `vid_name`, `date_received`, `date_published`, `description`, `sup_id`, `cat_id`, `prod_id`, `status`) VALUES
(7, 'Wrong Number', '2013-01-28', '2013-01-28', 'site', 1, 2, 7, 'Published'),
(11, 'Extra Ordinary', '2013-01-28', '2013-01-31', 'site', 1, 2, 7, 'Published'),
(14, 'Onjaore A', '2013-01-28', '2013-01-31', 'site', 1, 5, 9, 'Published'),
(15, 'Onjaore B', '2013-01-28', '2013-01-31', 'site', 1, 5, 9, 'Published'),
(16, '7th Commandment A', '2012-12-03', '2012-12-06', 'site', 1, 1, 10, 'Published'),
(17, '7th Commandment B', '2012-12-03', '2012-12-03', 'site', 1, 1, 10, 'Published'),
(20, '24th Anniversary A', '2012-12-03', '2013-01-31', 'site', 1, 1, 11, 'Published'),
(21, '24th Anniversary B', '2012-12-03', '2013-01-31', 'site', 1, 1, 11, 'Published'),
(22, '24th Anniversary C', '2012-12-03', '2013-01-31', 'site', 1, 1, 11, 'Published'),
(26, 'Deceptions', '2012-12-03', '2013-01-31', 'site', 1, 1, 12, 'Published'),
(33, 'Who''s Your Daddy', '2012-12-03', '2012-12-03', 'site', 1, 1, 13, 'Published'),
(34, 'Kyondo_melo A', '2012-12-03', '2013-01-31', 'site', 1, 4, 14, 'Published'),
(35, 'Kyondo_melo B', '2012-12-03', '2013-01-31', 'site', 1, 4, 14, 'Published'),
(37, 'Torn Veil A', '2012-12-03', '2013-01-31', 'site', 1, 1, 11, 'Published'),
(44, 'Against my will A', '2012-12-04', '2013-01-31', 'site', 2, 1, 15, 'Published'),
(45, 'Against my will B', '2012-12-04', '2013-01-31', 'site', 2, 1, 15, 'Published'),
(46, 'Ahadi ya mamilioni', '2012-12-04', '2013-02-04', 'site', 1, 2, 7, 'Published'),
(47, 'Divine Intervention', '2012-12-04', '2013-01-31', 'site', 1, 1, 12, 'Published'),
(48, 'Bomba Bomba fools', '2012-12-03', '2012-12-10', 'site', 1, 2, 16, 'Published'),
(49, 'Anastasia', '2013-01-28', '2013-01-28', '', 1, 2, 6, 'Published'),
(51, 'Senoir pastor A', '2012-12-10', '2013-01-31', 'site', 1, 1, 12, 'Published'),
(52, 'Senoir pastor B', '2012-12-10', '2013-01-31', 'site', 1, 1, 12, 'Published'),
(53, 'Gaidi', '2012-12-10', '2013-02-02', 'site', 1, 2, 18, 'Published'),
(57, 'Tumaini A', '2012-12-10', '2013-01-31', 'site', 1, 2, 20, 'Published'),
(58, 'Tumaini B', '2012-12-10', '2013-01-31', 'site', 1, 2, 20, 'Published'),
(60, 'Wendo wa mbeca', '2012-12-03', '2013-01-31', 'site', 1, 3, 21, 'Published'),
(61, 'Ngingo ndikilaa mutwe', '2012-12-03', '2013-01-31', 'site', 1, 4, 21, 'Published'),
(62, 'neshuya series 2', '2012-12-03', '2013-01-31', 'site', 1, 6, 17, 'Published'),
(63, 'Giagatika 1', '2013-01-08', '2013-01-21', 'site', 1, 3, 21, 'Published'),
(64, 'Giagatika 2', '2012-12-03', '2013-01-31', 'site', 1, 3, 21, 'Published'),
(65, 'Giagatika 3', '2012-12-03', '2013-01-31', 'site', 1, 3, 21, 'Published'),
(66, 'Giagatika 4', '2012-12-03', '2013-01-31', 'site', 1, 3, 21, 'Published'),
(67, 'Bwana Ochola', '2012-12-04', '2013-02-06', '', 1, 2, 22, 'Published'),
(71, 'Spinners', '2012-12-04', '2012-12-04', '', 1, 1, 13, 'Published'),
(72, 'Tumaini C', '2012-12-10', '2012-12-11', 'site', 1, 2, 20, 'Published'),
(73, 'Sound mind', '2012-12-04', '2012-12-04', 'site', 1, 1, 13, 'Published'),
(74, 'mwisho wa majivuno A', '2012-12-04', '2013-02-06', 'site', 1, 2, 23, 'Published'),
(75, 'mwisho wa majivuno B', '2012-12-04', '2013-02-06', 'site', 1, 2, 23, 'Published'),
(76, 'Neshuiya series 1', '2012-12-03', '2013-01-31', 'site', 1, 6, 17, 'Published'),
(77, 'mundurume ni mugambo 1', '2012-12-18', '2012-12-18', 'site', 3, 3, 24, 'Published'),
(79, 'yagwithurira 17', '2012-12-18', '2012-12-18', 'site', 3, 3, 24, 'Published'),
(80, 'conficts in love', '2013-01-23', '2013-02-02', 'site', 1, 1, 25, 'Published'),
(81, 'kimondolo', '2013-01-16', '2013-01-31', 'site', 1, 4, 26, 'Published'),
(82, 'Baada ya dhiki A', '2013-01-22', '2013-01-31', 'site', 1, 2, 27, 'Published'),
(83, 'Gathoni', '2013-01-22', '2013-01-31', 'site', 2, 3, 28, 'Published'),
(84, 'baada ya dhiki B', '2013-01-22', '2013-01-31', 'site', 1, 2, 27, 'Published'),
(86, 'menye menye kihenjo', '2013-01-22', '2013-01-31', 'site', 2, 3, 15, 'Published'),
(89, 'Udaku baado upo', '2013-01-22', '2013-01-23', '', 2, 2, 29, 'Published'),
(91, 'Kirimu Kiohiga', '2013-01-21', '2013-01-31', 'site', 2, 3, 30, 'Published'),
(92, 'Talanta', '2013-01-28', '2013-02-07', 'site', 1, 2, 31, 'Published'),
(93, 'Penzi kichaa A(1)', '2013-01-22', '2013-01-31', 'site', 1, 2, 32, 'Published'),
(94, 'Penzi kichaa A(2)', '2013-01-22', '2013-01-31', 'site', 1, 2, 32, 'Published'),
(95, 'Penzi kichaa A(3)', '2013-01-22', '2013-01-31', 'site', 1, 2, 32, 'Published'),
(96, 'Penzi tata B(1)', '2013-01-22', '2013-01-31', 'site', 1, 2, 32, 'Published'),
(97, 'Penzi tata B(2)', '2013-01-22', '2013-01-31', 'site', 1, 2, 32, 'Published'),
(98, 'Penzi tata B(3)', '2013-01-22', '2013-01-31', 'site', 1, 2, 32, 'Published'),
(99, 'maithori ma ruo 1', '2013-01-22', '2013-01-22', 'site', 4, 3, 33, 'Published'),
(100, 'maithori ma ruo 2', '2013-01-22', '2013-01-22', 'site', 4, 3, 33, 'Published'),
(101, 'muthia wa maithori 1', '2013-01-22', '2013-01-22', 'site', 4, 3, 33, 'Published'),
(102, 'muthia wa maithori 2', '2013-01-22', '2013-01-22', 'site', 4, 3, 33, 'Published'),
(103, 'Iya Embasna 1', '2013-01-22', '2013-02-07', 'site', 4, 5, 34, 'Published'),
(105, 'Subiri mungu 1', '2013-01-22', '2013-01-22', 'site', 1, 2, 35, 'Published'),
(106, 'Subiri mungu 2', '2013-01-22', '2013-01-22', 'site', 1, 2, 35, 'Published'),
(107, 'Subiri mungu 3', '2013-01-22', '2013-01-22', 'site', 1, 2, 35, 'Published'),
(108, 'Subiri Mungu 4', '2013-01-22', '2013-01-22', 'site', 1, 2, 35, 'Published'),
(109, 'Agudho in Kisumu', '2013-01-28', '2013-02-02', 'site', 4, 5, 5, 'Published'),
(111, 'Stained wembe', '2013-01-21', '2013-01-21', 'site', 1, 1, 36, 'Published'),
(112, 'wheel chair', '2013-01-21', '2013-01-21', '', 2, 1, 36, 'Published'),
(113, 'kahiu kohiga', '2013-01-21', '2013-01-21', '', 2, 3, 36, 'Published'),
(114, 'best of kihoto', '2013-01-21', '2013-01-31', 'site', 2, 3, 36, 'Published'),
(115, 'Village cassanova', '2013-01-21', '2013-02-01', '', 2, 1, 36, 'Published'),
(116, 'kapkiwok', '2013-01-21', '2013-01-21', '', 1, 7, 36, 'Ripped'),
(117, 'mundurume ni mugambo 2', '2013-01-21', '2013-01-21', 'site', 3, 3, 24, 'Published'),
(118, 'Torn Veil B', '2012-12-03', '2013-01-31', 'site', 1, 1, 11, 'Published'),
(119, 'Umbani wa thina', '2013-01-31', '2013-01-31', 'site', 1, 3, 21, 'Published'),
(120, 'papa shirandula Ep 1', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(121, 'papa shirandula Ep 2', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(122, 'papa shirandula Ep 3', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(123, 'papa shirandula Ep 4', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(124, 'papa shirandula Ep 5', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(125, 'papa shirandula Ep 6', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(126, 'papa shirandula Ep 7', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(127, 'papa shirandula Ep 8', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(128, 'papa shirandula Ep 9', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(129, 'papa shirandula Ep 10', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(130, 'papa shirandula Ep 11', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(131, 'Papa shirandula promo', '2012-12-04', '2013-02-06', 'site', 5, 8, 37, 'Published'),
(132, 'Wambua Ep 1', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(133, 'Wambua Ep 2', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(134, 'Wambua Ep 3', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(135, 'Wambua Ep 4', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(136, 'Wambua Ep 5', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(137, 'Wambua Ep 6', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(138, 'Wambua Ep 7', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(139, 'Wambua Ep 8', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(140, 'Wambua Ep 9', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(141, 'Wambua Ep 10', '2013-02-05', '2013-02-06', '', 9, 12, 41, 'Published'),
(142, 'Tabasamu Ep 1', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(143, 'Tabasamu Ep 2', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(144, 'Tabasamu Ep 3', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(145, 'Tabasamu Ep 4', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(146, 'Tabasamu Ep 5', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(147, 'Tabasamu Ep 6', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(148, 'Tabasamu Ep 7', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(149, 'Tabasamu Ep 8', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(150, 'Tabasamu Ep 9', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(151, 'Tabasamu Ep 10', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(152, 'Tabasamu Ep 11', '2013-02-05', '2013-02-06', 'site', 7, 10, 39, 'Published'),
(153, 'Ultimate price', '2013-02-05', '2013-02-06', '', 2, 1, 15, 'Published'),
(154, 'mother in law Ep 1', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(155, 'mother in law Ep 2', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(156, 'mother in law Ep 3', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(157, 'mother in law Ep 4', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(158, 'mother in law Ep 5', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(159, 'papa shirandula Ep 12', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(160, 'papa shirandula Ep 13', '2013-02-04', '2013-02-05', 'site', 5, 8, 37, 'Published'),
(161, 'kenya secret Ep 1', '2013-02-05', '2013-02-06', 'site', 11, 14, 43, 'Published'),
(162, 'kenya secret Ep 2', '2013-02-05', '2013-02-06', 'site', 11, 14, 43, 'Published'),
(163, 'kenya secret Ep 3', '2013-02-05', '2013-02-06', 'site', 11, 14, 43, 'Published'),
(164, 'kenya secret Ep 4', '2013-02-05', '2013-02-06', 'site', 11, 14, 43, 'Published'),
(165, 'kenya secret Ep 5', '2013-02-05', '2013-02-06', 'site', 11, 14, 43, 'Published'),
(166, 'kenya secret Ep 6', '2013-02-05', '2013-02-06', 'site', 11, 14, 43, 'Published'),
(167, 'Eye witness', '2013-02-05', '2013-02-06', 'site', 10, 13, 42, 'Published'),
(168, 'Blanket & wine 1', '2013-02-05', '2013-02-06', 'site', 10, 13, 42, 'Published'),
(169, 'Raila na mpira', '2013-02-05', '2013-02-06', 'site', 10, 13, 42, 'Published'),
(170, 'Raila and cow', '2013-02-05', '2013-02-06', 'site', 10, 13, 42, 'Published'),
(171, 'Naswa: skeleton crossing ', '2013-02-08', '2013-02-09', 'site', 10, 13, 42, 'Published'),
(172, 'Naswa the syringe', '2013-02-08', '2013-02-09', 'site', 10, 13, 42, 'Published'),
(173, 'Naswa power saw', '2013-02-08', '2013-02-09', 'site', 10, 13, 42, 'Published'),
(174, 'Naswa burning car', '2013-02-08', '2013-02-09', 'site', 10, 13, 42, 'Published'),
(175, 'Naswa Expensive Goods', '2013-02-08', '2013-02-09', '', 12, 15, 44, 'Published'),
(176, 'Naswa coffin in matatu', '2013-02-08', '2013-02-09', 'site', 12, 15, 44, 'Published'),
(177, 'Naswa Fainting', '2013-02-08', '2013-02-09', '', 12, 15, 44, 'Published'),
(178, 'Naswa jacket', '2013-02-08', '2013-02-09', 'site', 12, 15, 44, 'Published'),
(179, 'mother in law Ep 6', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(180, 'mother in law Ep 7', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(181, 'mother in law Ep 8', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(182, 'mother in law Ep 9', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(183, 'mother in law Ep 10', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(184, 'mother in law Ep 11', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(185, 'mother in law Ep 12', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(186, 'mother in law Ep 13', '2013-02-05', '2013-02-07', 'site', 6, 9, 38, 'Published'),
(187, 'Naswa phone disturbance', '2013-02-05', '2013-02-07', '', 12, 15, 44, 'Published'),
(188, 'Compulsory wedding', '2013-02-05', '2013-02-07', 'site', 12, 15, 44, 'Published'),
(189, 'Iya Embasna 2', '2013-02-12', '2013-02-13', 'site', 4, 5, 34, 'Published'),
(190, 'ping pek part 1', '2013-02-12', '2013-02-14', 'site', 1, 5, 45, 'Published'),
(191, 'pengele 1', '2013-02-11', '2013-02-13', '', 1, 5, 46, 'Published'),
(192, 'Neshuya series 3', '2013-02-11', '2013-02-13', 'site', 1, 6, 17, 'Published'),
(193, 'Tabasamu Ep 2', '2013-01-21', '2013-02-01', 'site', 7, 10, 39, 'Published'),
(194, 'Dala yaye', '2013-01-28', '2013-01-28', 'site', 4, 5, 36, 'Published'),
(195, 'Undesired (wang''umbi)', '2013-01-28', '2013-01-28', '', 4, 1, 36, 'Published'),
(196, 'Omundu manyika 1', '2013-01-28', '2013-01-28', '', 4, 6, 36, 'Published'),
(197, 'mr. oludhe', '2013-01-28', '2013-01-28', '', 4, 1, 36, 'Published'),
(202, 'Arms of tenderness', '2013-02-12', '2013-02-13', 'site', 1, 3, 36, 'Published'),
(203, 'Gachungwa', '2013-02-12', '2013-02-13', '', 1, 3, 36, 'Published'),
(204, 'master muheshimiwa', '2013-02-12', '2013-02-13', 'site', 1, 3, 36, 'Published'),
(205, 'Ngombe', '2013-02-12', '2013-02-13', 'site', 1, 3, 36, 'Published'),
(206, 'Airu akari', '2013-02-12', '2013-02-13', 'site', 1, 3, 36, 'Published'),
(207, 'Kyondo_melo C', '2013-02-12', '2013-02-13', 'site', 1, 4, 14, 'Published'),
(208, 'Mzee kijana', '2013-02-12', '2013-02-13', 'site', 1, 3, 36, 'Published'),
(209, 'Tukutendereza', '2013-02-12', '2013-02-13', '', 1, 2, 36, 'Published'),
(211, 'Oyuke 1', '2013-02-12', '2013-02-13', 'site', 4, 5, 36, 'Published'),
(212, 'Oyuke 2', '2013-02-12', '2013-02-13', 'site', 4, 5, 36, 'Published'),
(213, 'Naliaka', '2013-02-19', '2013-02-19', 'site', 4, 1, 36, 'Published'),
(214, 'kibrit', '2013-02-19', '2013-02-19', 'site', 4, 5, 36, 'Published'),
(215, 'mako oketho', '2013-02-19', '2013-02-19', 'site', 4, 5, 36, 'Published'),
(216, 'Kalausi', '2013-02-19', '2013-02-19', 'site', 4, 5, 36, 'Ripped'),
(217, 'Backlash 1', '2013-02-20', '2013-02-20', 'site', 1, 1, 36, 'Published'),
(218, 'Dhi E jikon', '2013-02-20', '2013-02-20', '', 1, 5, 46, 'Published'),
(219, 'Mwaitu mwasyindu', '2013-02-20', '2013-02-20', '', 1, 4, 36, 'Published'),
(220, 'Tausi', '2013-02-20', '2013-02-20', '', 1, 2, 36, 'Published'),
(221, 'Jipe moyo', '2013-01-28', '2013-01-28', '', 1, 2, 36, 'Published'),
(222, 'judgment is coming', '2013-01-28', '2013-01-28', '', 1, 7, 36, 'Ripped'),
(223, 'Apple juice free sample', '2013-01-28', '2013-01-28', '', 12, 15, 44, 'Published'),
(224, 'Three mask men', '2013-01-28', '2013-01-28', '', 12, 15, 44, 'Published'),
(225, 'Leg swip', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(226, 'mask men from a car', '2013-01-28', '2013-01-28', '', 12, 15, 44, 'Published'),
(227, 'peace demonstration', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(228, 'monkeny reading paper', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(229, 'Mother and child', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(230, 'Vampire fainted', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(231, 'snake under box', '2013-01-28', '2013-01-28', '', 12, 15, 44, 'Published'),
(232, 'followers', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(233, 'baby cry', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(234, 'compulsory vaccine', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(235, 'danger lie down', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(236, 'vomiting lizard', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(237, 'chicken doc', '2013-01-28', '2013-01-28', '', 12, 15, 44, 'Published'),
(238, 'aids test', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(239, 'Young boy seducing ladies', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(240, 'Urinating on strangers', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(241, 'smoke money', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(242, 'Light weight', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(243, 'handsome guy pek', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(244, 'Grabbing a cake', '2013-01-28', '2013-01-28', 'site', 12, 15, 44, 'Published'),
(245, 'Fat guy sitting between', '2013-01-28', '2013-01-28', '', 12, 15, 44, 'Published'),
(246, 'police girlfriend', '2012-12-18', '2012-12-18', 'site', 12, 15, 44, 'Published'),
(247, 'mask', '2012-12-18', '2012-12-18', '', 12, 15, 44, 'Published'),
(248, 'statue', '2012-12-18', '2012-12-18', '', 12, 15, 44, 'Published'),
(249, 'ninja men', '2012-12-18', '2012-12-18', '', 12, 15, 44, 'Published'),
(250, 'vampire teeth', '2012-12-18', '2012-12-18', 'site', 12, 15, 44, 'Published'),
(251, 'muciari ni muciari', '2013-02-22', '2013-02-25', '', 3, 3, 5, 'Published'),
(252, 'Mucii ni ndogo', '2013-02-22', '2013-02-25', '', 3, 3, 5, 'Published'),
(253, 'Utari ni muigire', '2013-02-22', '2013-02-25', '', 3, 3, 5, 'Published'),
(254, 'fitina', '2013-02-22', '2013-02-25', '', 3, 3, 5, 'Published'),
(255, 'Mwana ni wau', '2013-02-22', '2013-02-25', '', 3, 3, 5, 'Published'),
(256, 'soul mates A', '2013-03-06', '2013-03-07', '', 1, 1, 36, 'Published'),
(257, 'soul mates B', '2013-03-06', '2013-03-07', '', 1, 1, 36, 'Published'),
(258, 'Malkia Tele 1', '2013-03-06', '2013-03-07', 'site', 1, 2, 36, 'Published'),
(259, 'Malkia tele 2', '2013-03-06', '2013-03-07', 'site', 1, 2, 36, 'Published'),
(260, 'Risky affair', '2013-03-06', '2013-03-07', '', 1, 1, 36, 'Published'),
(261, 'where love has gone', '2013-03-06', '2013-03-07', '', 1, 1, 36, 'Published'),
(262, 'Missed call', '2013-03-06', '2013-03-07', '', 1, 1, 36, 'Published'),
(263, 'Sam sweety', '2013-02-12', '2013-02-13', '', 1, 2, 36, 'Published'),
(264, 'Ben Osiha', '2013-03-06', '2013-03-07', 'site', 1, 5, 5, 'Published'),
(265, 'Omundu manyika 2', '2013-03-11', '2013-03-11', '', 1, 6, 36, 'Published'),
(266, 'sakina', '2013-03-06', '2013-03-07', 'site', 1, 2, 36, 'Published'),
(269, 'Life mtaani', '2012-12-18', '2012-12-18', 'site', 1, 2, 36, 'Published'),
(270, 'mama noise', '2012-12-18', '2012-12-18', 'site', 1, 2, 36, 'Published'),
(271, 'deserted', '2012-12-18', '2012-12-18', '', 1, 1, 36, 'Published'),
(272, 'Time', '2012-12-18', '2012-12-18', '', 1, 1, 36, 'Published'),
(275, 'Destiny A', '2012-12-18', '2012-12-18', '', 1, 1, 36, 'Published'),
(276, 'Destiny B', '2012-12-18', '2012-12-18', '', 1, 1, 36, 'Published'),
(278, 'Missing simpson', '2013-03-12', '2013-03-13', 'site', 1, 1, 36, 'Published'),
(280, 'Double standard', '2013-03-12', '2013-03-13', '', 1, 2, 36, 'Published'),
(281, 'Blanket & wine 2', '2013-02-05', '2013-02-06', 'site', 10, 13, 42, 'Published'),
(282, 'Toto millionare', '2013-03-26', '2013-03-26', '', 1, 2, 36, 'Published'),
(283, 'Usiku mwanga', '2013-03-26', '2013-03-26', '', 1, 2, 36, 'Published'),
(284, 'Man behind the tree', '2013-03-26', '2013-03-26', 'site', 12, 15, 44, 'Published'),
(285, 'bare back', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(286, 'Blind main electrocuted', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(287, 'Beating Bear', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(288, 'Electrician Electrocuted', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(289, 'Drinking Paraffin', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(290, 'Kissing a Doll', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(291, 'Mirror', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(292, 'Hanging Clothes', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(293, 'Happy Birthday', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(294, 'Hen tied in the leg', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(295, 'Hen tied in the leg', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(296, 'Stealing a car Tyre', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(297, 'Stealing From a person who has fainted', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(298, 'Selling Vegetables', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(299, 'Snake Bite', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(300, 'Jump starting a Person', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(301, 'Guest Opening', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(302, 'Blind Lady', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(303, 'Blind Thief', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(304, 'Chokora Dance', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(305, 'Lover''s 2', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(306, 'Mirror Lady', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(307, 'Police Boot', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(308, 'Wedding Fight', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(309, 'Wedding Taxi', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(310, 'Wine in the street', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(311, 'Board Ugali', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(312, 'Board Wheel Chair', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(313, 'Bucket', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(314, 'Coins', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(315, 'Golf Corridor', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(316, 'Military', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(317, 'Monkey Selling Bananas', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(318, 'Pastors fighting over Offering', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(319, 'Snake in the Back', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(320, 'Vampire Patient', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(321, 'Tyre', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(322, 'Snake on Bench', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(323, 'Romance', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(324, 'Pastor Vampire', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(325, 'Phone', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(326, 'Pastor-Alcohol', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(327, 'Pastor stealing', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(328, 'Kick Ball', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(329, 'Helta Skelta', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(330, 'Blind-Money', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(331, 'Blind-Key', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(332, 'Axe', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(333, 'Blind man-Coins', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(334, 'Dog-Board', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(335, 'Electric Shock', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(336, 'Exchange Briefcase', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(337, 'Pastor Picking Wallet', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(338, 'Police-hands up', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(339, 'Police-shoes', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(340, 'Running Across 2', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(341, 'Sleeping at Bench', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(342, 'Snatching Drinks', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(343, 'Sneeze', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(344, 'Stealing fuel from a Car', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(345, 'Street Boys Giving Money', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(346, 'Syringe', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(347, 'Transformer-Oil', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(348, 'Vampire-Keys', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(349, 'Wrong Directions', '2013-03-28', '2013-03-28', '', 12, 15, 44, 'Ripped'),
(350, 'Love Dust A', '2013-04-05', '2013-04-05', 'site', 1, 1, 5, 'Published'),
(351, 'Love Dust B', '2013-04-05', '2013-04-05', 'site', 1, 1, 5, 'Published'),
(352, 'benta', '2013-04-05', '2013-04-05', 'site', 1, 1, 5, 'Published'),
(353, 'wale watu', '2013-04-08', '2013-04-08', '', 1, 1, 5, 'Published'),
(355, 'Consequences', '2013-04-08', '2013-04-08', '', 1, 1, 5, 'Published'),
(356, 'Bush Helmet', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(357, 'Cockroach in Cake', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(358, 'Dummy Driver', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(359, 'Lost White Lady Giving Money', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(360, 'Mask-Direction', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(361, 'Men Scattering', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(362, 'Patient Stabbing Himself', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(363, 'Pay Policeman Bills', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(364, 'Sick Patient', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(365, 'Slashing a Snake', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(366, 'Smoke From the Bush', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(367, 'Smoke From the Engine', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(368, 'Snake Pulled From a Tree', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(369, 'Snatching a Fake hand', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(370, 'Staring', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(371, 'Sugarcane-Direction', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(372, 'Suspect on Bench', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(373, 'Walking Bush', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(374, 'White Lady Begging', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(375, 'White Man Eating From Trash', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(376, 'White Man Stealing', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(377, 'Ventilated Shoes', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(378, 'Eating Goat Leg', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(379, 'Stuck Coins', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(380, 'White Man playing With a Snake', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(381, 'Couple destructing', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(382, 'Eating Bread', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(383, 'Electrocuted', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(384, 'Fake Note', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(385, 'Imaginary Ghost', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(386, 'Pick Something', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(387, 'Set Up', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(388, 'Smoking-Chewing Electrician', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(389, 'Spraying Doom', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(390, 'Throwing Rope 3', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(391, 'Wedding In the street', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(392, 'Waider Oulling guys out of the Bench', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(393, 'Dirty Hands Pushing a Vehicle', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(394, 'Gangster Calling Stranger', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(395, 'Making Faces on the Mirror', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(396, 'Mumbling Prayers', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(397, 'Picking Money out of Wheel chair', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(398, 'Prisoner', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(399, 'Solve this equation', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(400, 'Super Glued Water Bottle', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(401, 'Tiger Dropped from Tree', '2013-04-18', '2013-04-18', '', 12, 15, 44, 'Published'),
(402, 'All girls together', '2013-04-18', '2013-04-18', '', 1, 1, 36, 'Ripped'),
(403, 'After the Dreams', '2013-04-18', '2013-04-18', '', 1, 2, 36, 'Ripped'),
(404, 'Conspiracy in numbers A', '2013-04-18', '2013-04-18', '', 1, 1, 36, 'Ripped'),
(405, 'Conspiracy in numbers B', '2013-04-18', '2013-04-18', '', 1, 1, 36, 'Ripped'),
(406, 'Jipe moyo Forever', '2013-04-18', '2013-04-18', 'site', 1, 2, 36, 'Published'),
(407, 'Kiihu 1', '2013-04-18', '2013-04-18', '', 1, 3, 36, 'Published'),
(408, 'Kiihu 2', '2013-04-18', '2013-04-18', '', 1, 3, 36, 'Published'),
(409, 'kingiuma naku', '2013-04-18', '2013-04-18', '', 1, 3, 36, 'Published'),
(410, 'Malkia Teli 3', '2013-04-18', '2013-04-18', '', 1, 2, 36, 'Published'),
(411, 'Matata matatani', '2013-04-18', '2013-04-18', '', 1, 2, 36, 'Published'),
(412, 'Mganga digital', '2013-04-18', '2013-04-18', '', 1, 2, 36, 'Published'),
(413, 'Msako wa jambazi', '2013-04-18', '2013-04-18', '', 1, 2, 36, 'Published'),
(414, 'Obohoz', '2013-04-18', '2013-04-18', '', 1, 1, 36, 'Published'),
(415, 'Salio', '2013-04-18', '2013-04-18', '', 1, 2, 36, 'Published'),
(416, 'Sunset at Dawn', '2013-04-18', '2013-04-18', '', 1, 1, 36, 'Published'),
(417, 'Film Maker', '2013-04-18', '2013-04-18', '', 1, 2, 36, 'Published'),
(418, 'Upon a time', '2013-04-18', '2013-04-18', '', 1, 1, 36, 'Published'),
(419, 'Tawala Kenya', '2013-04-18', '2013-04-18', 'site', 10, 13, 42, 'Published'),
(420, 'Kibaki at his best', '2013-04-18', '2013-04-18', 'site', 10, 13, 42, 'Published'),
(421, 'Unfit to govern', '2013-05-06', '2013-05-06', '', 1, 1, 12, 'Published'),
(422, 'Wangu wa makeri', '2013-03-12', '2013-03-13', '', 1, 3, 36, 'Published');

-- --------------------------------------------------------

--
-- Table structure for table `video_category`
--

CREATE TABLE IF NOT EXISTS `video_category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(100) DEFAULT NULL,
  `language` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `video_category`
--

INSERT INTO `video_category` (`cat_id`, `cat_name`, `language`) VALUES
(1, 'English', NULL),
(2, 'kiswahili', ''),
(3, 'kikuyu', ''),
(4, 'kamba', ''),
(5, 'luo', ''),
(6, 'Luhya', NULL),
(7, 'Kalenjin', NULL),
(8, 'papa shirandula', NULL),
(9, 'mother in law', NULL),
(10, 'tabasamu', NULL),
(11, 'pranks go bad', NULL),
(12, 'wambua', NULL),
(13, 'Random kenya', NULL),
(14, 'Documentary', NULL),
(15, 'Naswa', NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `video_ibfk_1` FOREIGN KEY (`sup_id`) REFERENCES `supplier` (`sup_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `video_ibfk_2` FOREIGN KEY (`prod_id`) REFERENCES `producer` (`prod_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `video_ibfk_3` FOREIGN KEY (`cat_id`) REFERENCES `video_category` (`cat_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
