package in.kiebotlibrary.com.dto;

import java.util.List;

import in.kiebotlibrary.com.entity.Book;

public class BookDTO {

	private String isbn;

	private String title;

	private String authorName;

	private String genre;

	private String publisher;

	private Integer publicationYear;

	private Integer totalCopies;

	private Integer availableCopies;

	private AuthorDTO author;

	private List<LoanSummaryDTO> currentLoansSummary;

	private List<ReservationSummaryDTO> activeReservationsSummary;

	public static BookDTO from(Book book, int availableCopies) {

		BookDTO dto = new BookDTO();
		dto.setIsbn(book.getIsbn());
		dto.setTitle(book.getTitle());
		dto.setAuthorName(book.getAuthor().getName());
		dto.setGenre(book.getGenre().name());
		dto.setPublisher(book.getPublisher());
		dto.setPublicationYear(book.getPublicationYear());
		dto.setTotalCopies(book.getTotalCopies());
		dto.setAvailableCopies(availableCopies);

		if (book.getAuthor() != null) {
		    dto.setAuthor(AuthorDTO.from(book.getAuthor()));
		}
		return dto;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Integer getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}

	public Integer getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(Integer availableCopies) {
		this.availableCopies = availableCopies;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public List<LoanSummaryDTO> getCurrentLoansSummary() {
		return currentLoansSummary;
	}

	public void setCurrentLoansSummary(List<LoanSummaryDTO> currentLoansSummary) {
		this.currentLoansSummary = currentLoansSummary;
	}

	public List<ReservationSummaryDTO> getActiveReservationsSummary() {
		return activeReservationsSummary;
	}

	public void setActiveReservationsSummary(List<ReservationSummaryDTO> activeReservationsSummary) {
		this.activeReservationsSummary = activeReservationsSummary;
	}

}
