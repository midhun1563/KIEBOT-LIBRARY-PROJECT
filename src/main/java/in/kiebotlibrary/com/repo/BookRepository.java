package in.kiebotlibrary.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.kiebotlibrary.com.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	@Query("SELECT COUNT(bl) FROM BookLoan bl WHERE bl.book = :book AND bl.status = 'ACTIVE'")

	long countActiveLoansByBook(@Param("book") Book book);
}
