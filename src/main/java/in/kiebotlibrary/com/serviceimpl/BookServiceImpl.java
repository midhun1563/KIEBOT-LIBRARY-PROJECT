package in.kiebotlibrary.com.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import in.kiebotlibrary.com.criteria.BookSpecifications;
import in.kiebotlibrary.com.dto.AuthorDTO;
import in.kiebotlibrary.com.dto.BookDTO;
import in.kiebotlibrary.com.dto.BookSearchCriteria;
import in.kiebotlibrary.com.dto.LoanSummaryDTO;
import in.kiebotlibrary.com.dto.ReservationSummaryDTO;
import in.kiebotlibrary.com.entity.Book;
import in.kiebotlibrary.com.entity.BookLoan;
import in.kiebotlibrary.com.entity.Reservation;
import in.kiebotlibrary.com.repo.BookLoanRepository;
import in.kiebotlibrary.com.repo.BookRepository;
import in.kiebotlibrary.com.repo.ReservationRepository;
import in.kiebotlibrary.com.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookLoanRepository loanRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public ResponseEntity<Page<BookDTO>> searchBooks(BookSearchCriteria criteria, Pageable pageable) {

		Specification<Book> spec = BookSpecifications.build(criteria);

		Page<Book> booksPage = bookRepository.findAll(spec, pageable);

		Page<BookDTO> bookDTOPage = booksPage.map(book -> {
			int activeLoans = loanRepository.countByBookAndStatus(book, BookLoan.Status.ACTIVE);
			int availableCopies = book.getTotalCopies() - activeLoans;

			BookDTO dto = BookDTO.from(book, availableCopies);

			AuthorDTO authorDTO = AuthorDTO.from(book.getAuthor());
			dto.setAuthor(authorDTO);

			if (criteria.getLoanedByMemberId() != null) {
				List<BookLoan> loans = loanRepository.findByBookAndMember_MemberIdAndStatus(book,
						criteria.getLoanedByMemberId(), BookLoan.Status.ACTIVE);

				List<LoanSummaryDTO> loanSummaries = loans.stream().map(loan -> {
					LoanSummaryDTO summary = new LoanSummaryDTO();
					summary.setMemberId(loan.getMember().getMemberId());
					summary.setDueDate(loan.getDueDate());
					return summary;
				}).toList();

				dto.setCurrentLoansSummary(loanSummaries);
			}

			if (criteria.getLoanedByMemberId() != null) {
				List<Reservation> reservations = reservationRepository.findByBookAndMember_MemberIdAndStatusIn(book,
						criteria.getLoanedByMemberId(),
						List.of(Reservation.Status.PENDING, Reservation.Status.READY_FOR_PICKUP));

				List<ReservationSummaryDTO> reservationSummaries = reservations.stream().map(res -> {
					ReservationSummaryDTO summary = new ReservationSummaryDTO();
					summary.setMemberId(res.getMember().getMemberId());
					summary.setReservationDate(res.getReservationDate());
					return summary;
				}).toList();

				dto.setActiveReservationsSummary(reservationSummaries);
			}

			return dto;
		});

		return ResponseEntity.ok(bookDTOPage);
	}

}
