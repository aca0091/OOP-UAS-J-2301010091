-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2025 at 11:57 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cruise_agent`
--

-- --------------------------------------------------------

--
-- Table structure for table `seaman`
--

CREATE TABLE `seaman` (
  `NAMA` varchar(60) NOT NULL,
  `CREW_ID` int(10) NOT NULL,
  `EXPERIENCE` varchar(50) NOT NULL,
  `POSITION` varchar(50) NOT NULL,
  `DOKUMENT` varchar(50) NOT NULL,
  `INTERVIEW_PASSED` varchar(50) NOT NULL,
  `EMERGENCY_CONTACT` varchar(50) NOT NULL,
  `REMARK` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seaman`
--

INSERT INTO `seaman` (`NAMA`, `CREW_ID`, `EXPERIENCE`, `POSITION`, `DOKUMENT`, `INTERVIEW_PASSED`, `EMERGENCY_CONTACT`, `REMARK`) VALUES
('I KADEK TULAN EKA DASI', 1001, 'YES', 'Hotel Steward', 'Lengkap', 'English', '087751236985', NULL),
('PUTU WIKA MAHAYANA', 1002, 'YES', 'Cabin Steward', 'Lengkap', 'English', '083145628954', 'Need Improve English'),
('I GUSTI NGURAH AGUNG WISNU NEGARA', 1003, 'YES', 'JR Waiter', 'Lengkap', 'Hire Flix', '082589654856', 'Ready To Join'),
('KADEK HERI MAHENDRA', 1004, 'YES', 'Bar Server', 'Lengkap', 'Hire Flix', '086485544145', 'Ready To Join'),
('GEDE NYOMAN RESTU ADI ARTA ', 1005, 'NO', 'Asst Cook', 'Lengkap', 'Final', '089562548223', 'Ready to Hire Flix');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `seaman`
--
ALTER TABLE `seaman`
  ADD PRIMARY KEY (`CREW_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
