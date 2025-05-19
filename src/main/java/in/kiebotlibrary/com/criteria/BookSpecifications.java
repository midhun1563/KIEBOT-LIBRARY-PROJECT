package in.kiebotlibrary.com.criteria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import in.kiebotlibrary.com.dto.BookSearchCriteria;
import in.kiebotlibrary.com.entity.Author;
import in.kiebotlibrary.com.entity.Book;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Join;

public class BookSpecifications {
	
	public static Specification<Book> build(BookSearchCriteria criteria) {
		return (root, query, cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();

			if (criteria.getIsbn() != null) {
				
				predicates.add(cb.equal(root.get("isbn"), criteria.getIsbn()));
				
			}
			if (criteria.getTitle() != null) {
				
				predicates.add(cb.like(cb.lower(root.get("title")), "%" + criteria.getTitle().toLowerCase() + "%"));
				
			}
			if (criteria.getAuthorName() != null) {
				
				Join<Book, Author> authorJoin = root.join("author");
				predicates.add(
						cb.like(cb.lower(authorJoin.get("name")), "%" + criteria.getAuthorName().toLowerCase() + "%"));
				
			}
			if (criteria.getGenre() != null) {
				
				predicates.add(cb.equal(root.get("genre"), criteria.getGenre()));
				
			}
			if (criteria.getPublisher() != null) {
				
				predicates.add(
						cb.like(cb.lower(root.get("publisher")), "%" + criteria.getPublisher().toLowerCase() + "%"));
				
			}
			if (criteria.getPublicationYearFrom() != null) {
				
				predicates.add(cb.greaterThanOrEqualTo(root.get("publicationYear"), criteria.getPublicationYearFrom()));
				
			}
			if (criteria.getPublicationYearTo() != null) {
				
				predicates.add(cb.lessThanOrEqualTo(root.get("publicationYear"), criteria.getPublicationYearTo()));
				
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
