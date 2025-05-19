package in.kiebotlibrary.com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.kiebotlibrary.com.dto.BookDTO;
import in.kiebotlibrary.com.dto.BookSearchCriteria;

public interface BookService {
	
    public ResponseEntity<Page<BookDTO>> searchBooks(BookSearchCriteria criteria,
            @PageableDefault(size = 10, sort = "title") Pageable pageable);
    
    

}
