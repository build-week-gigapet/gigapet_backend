package com.gigapet.backend.services;


import com.gigapet.backend.models.Author;
import com.gigapet.backend.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorrepos;


    @Override
    public List<Author> findAll(Pageable pageable) {
        List<Author> list = new ArrayList<>();
        authorrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Author findAuthorById(long id) {
        return authorrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {
        authorrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Author save(Author author) {
        return authorrepos.save(author);
    }

}
