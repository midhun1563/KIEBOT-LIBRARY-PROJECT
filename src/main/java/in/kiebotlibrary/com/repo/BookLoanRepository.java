package in.kiebotlibrary.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kiebotlibrary.com.entity.Book;
import in.kiebotlibrary.com.entity.BookLoan;
import in.kiebotlibrary.com.entity.LibraryMember;

import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

    @Query("SELECT COUNT(bl) FROM BookLoan bl WHERE bl.book.id = :bookId AND bl.status = 'ACTIVE'")
    long countActiveLoansByBookId(Long bookId);

    @Query("SELECT bl.book.id FROM BookLoan bl WHERE bl.member.memberId = :memberId AND bl.status = 'ACTIVE'")
    List<Long> findActiveLoanedBookIdsByMemberId(String memberId);
    
    int countByBookAndStatus(Book book, BookLoan.Status status);
    
    List<BookLoan> findByBookAndMember_MemberIdAndStatus(Book book, String memberId, BookLoan.Status status);

    List<BookLoan> findByMember(LibraryMember member);



}
