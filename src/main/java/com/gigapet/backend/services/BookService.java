package com.gigapet.backend.services;


import com.gigapet.backend.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> findAll(Pageable pageable);

    Book findBookById(long id);

    void delete(long id);

    Book save(Book book);

    Book update(Book updateBook, long id);
}
