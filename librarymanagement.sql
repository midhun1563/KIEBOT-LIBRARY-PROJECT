-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2025 at 04:11 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarymanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `biography` text DEFAULT NULL,
  `birth_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `name`, `biography`, `birth_date`) VALUES
(1, 'Carl Sagan', 'American astronomer, astrophysicist, and science communicator.', '1934-11-09'),
(2, 'Isaac Asimov', 'Prolific author and professor of biochemistry.', '1920-01-02'),
(3, 'J.K. Rowling', 'British author, best known for the Harry Potter series.', '1965-07-31');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `isbn` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `genre` enum('FICTION','SCIENCE','HISTORY','FANTASY','MYSTERY','BIOGRAPHY','TECHNOLOGY','TEXTBOOK') NOT NULL,
  `publication_year` int(11) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `total_copies` int(11) NOT NULL DEFAULT 1,
  `author_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `isbn`, `title`, `genre`, `publication_year`, `publisher`, `total_copies`, `author_id`) VALUES
(1, 'ISBN-0345539438', 'Cosmos', 'SCIENCE', 1980, 'Random House', 5, 1),
(2, 'ISBN-055338256X', 'Foundation', 'FICTION', 1951, 'Spectra', 3, 2),
(3, 'ISBN-0439554934', 'Harry Potter and the Sorcerer\'s Stone', 'FANTASY', 1997, 'Scholastic', 10, 3);

-- --------------------------------------------------------

--
-- Table structure for table `book_loan`
--

CREATE TABLE `book_loan` (
  `id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  `loan_date` date DEFAULT curdate(),
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `status` enum('ACTIVE','OVERDUE','RETURNED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book_loan`
--

INSERT INTO `book_loan` (`id`, `book_id`, `member_id`, `loan_date`, `due_date`, `return_date`, `status`) VALUES
(1, 1, 1, '2025-05-01', '2025-05-15', NULL, 'ACTIVE'),
(2, 2, 2, '2025-04-20', '2025-05-05', '2025-05-03', 'RETURNED'),
(3, 3, 1, '2025-05-10', '2025-05-24', NULL, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `library_member`
--

CREATE TABLE `library_member` (
  `id` bigint(20) NOT NULL,
  `member_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `membership_date` date DEFAULT curdate(),
  `status` enum('ACTIVE','INACTIVE','SUSPENDED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `library_member`
--

INSERT INTO `library_member` (`id`, `member_id`, `first_name`, `last_name`, `email`, `membership_date`, `status`) VALUES
(1, 'MEM-AB1234', 'Alice', 'Brown', 'alice.brown@example.com', '2024-01-10', 'ACTIVE'),
(2, 'MEM-CD5678', 'Bob', 'Smith', 'bob.smith@example.com', '2024-02-20', 'ACTIVE'),
(3, 'MEM-EF9012', 'Charlie', 'Johnson', 'charlie.j@example.com', '2024-03-15', 'SUSPENDED');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  `reservation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` enum('PENDING','READY_FOR_PICKUP','FULFILLED','CANCELED') NOT NULL,
  `pickup_expiry_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `book_id`, `member_id`, `reservation_date`, `status`, `pickup_expiry_date`) VALUES
(1, 1, 2, '2025-05-15 04:30:00', 'PENDING', NULL),
(2, 3, 2, '2025-05-16 09:00:00', 'READY_FOR_PICKUP', '2025-05-20 09:00:00'),
(3, 2, 1, '2025-05-17 04:15:00', 'CANCELED', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `UKor6k6jmywerxbme223c988bmg` (`name`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`),
  ADD UNIQUE KEY `UKehpdfjpu1jm3hijhj4mm0hx9h` (`isbn`),
  ADD KEY `author_id` (`author_id`);

--
-- Indexes for table `book_loan`
--
ALTER TABLE `book_loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `member_id` (`member_id`);

--
-- Indexes for table `library_member`
--
ALTER TABLE `library_member`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `member_id` (`member_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `UK16e9h7d0a7j4k14qk1g07lfol` (`member_id`),
  ADD UNIQUE KEY `UKq2bf30f14bjc52208ujtd5n9v` (`email`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `member_id` (`member_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `book_loan`
--
ALTER TABLE `book_loan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `library_member`
--
ALTER TABLE `library_member`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`);

--
-- Constraints for table `book_loan`
--
ALTER TABLE `book_loan`
  ADD CONSTRAINT `book_loan_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `book_loan_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `library_member` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `library_member` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
