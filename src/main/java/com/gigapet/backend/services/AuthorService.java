package com.gigapet.backend.services;



import com.gigapet.backend.models.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    List<Author> findAll(Pageable pageable);

    Author findAuthorById(long id);

    void delete(long id);

    Author save(Author author);

}
