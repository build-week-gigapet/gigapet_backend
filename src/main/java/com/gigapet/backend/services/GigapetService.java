package com.gigapet.backend.services;

import com.gigapet.backend.models.Gigapet;

import java.util.List;

public interface GigapetService {
    List<Gigapet> findAll();

    Gigapet findGigapetById(long id);

    void delete(long id);

    Gigapet save(Gigapet gigapet);

    Gigapet update(Gigapet gigapet, long id);
}
