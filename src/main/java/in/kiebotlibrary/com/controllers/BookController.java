package in.kiebotlibrary.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.kiebotlibrary.com.dto.BookDTO;
import in.kiebotlibrary.com.dto.BookSearchCriteria;
import in.kiebotlibrary.com.service.BookService;

@RestController
@RequestMapping("/api/v1/books")

public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/search")
	public ResponseEntity<Page<BookDTO>> searchBooks(BookSearchCriteria criteria,
			@PageableDefault(size = 10, sort = "title") Pageable pageable) {

		return bookService.searchBooks(criteria, pageable);

	}

}
