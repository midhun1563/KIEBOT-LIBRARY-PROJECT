University Library Management System (KIEBOT PROJECT)
=========================================

A microservices-based Library Management System for managing books, authors, 
members, loans, and reservations with dynamic search and availability tracking.

Features
=============
Postman documentation included

Post man export json included(KIEBOT.postman_collection.json)

DB Scripts for MySQL(librarymanagement.sql)



🔧 Tech Stack
=================
Backend: Spring Boot, Spring Data JPA, Spring Web, Hibernate

Database: MySQL 

Build Tool: Maven

Other: DTO Mapping, Bean Validation, RESTful APIs, Exception Handling

 Book Search(Refer post man export json for tested working scenarios)
 ============
Basic Search by Title
GET /api/v1/books/search?title=Cosmos&page=0&size=10

Advanced Search
GET /api/v1/books/search?authorName=Sagan&genre=SCIENCE&publicationYearFrom=1970&publicationYearTo=1990&sort=publicationYear,asc

Search by Member ID (Current Loans)
GET /api/v1/books/search?loanedByMemberId=MEM-AB1234&page=0&size=5

Search Available Books
GET /api/v1/books/search?isAvailable=true&genre=TECHNOLOGY&sort=title,asc



