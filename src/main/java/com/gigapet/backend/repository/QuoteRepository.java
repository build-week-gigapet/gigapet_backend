package com.gigapet.backend.repository;


import com.gigapet.backend.models.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long>
{

}
