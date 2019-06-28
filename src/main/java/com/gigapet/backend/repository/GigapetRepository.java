package com.gigapet.backend.repository;

import com.gigapet.backend.models.Gigapet;
import org.springframework.data.repository.CrudRepository;

public interface GigapetRepository extends CrudRepository<Gigapet, Long> {
}
