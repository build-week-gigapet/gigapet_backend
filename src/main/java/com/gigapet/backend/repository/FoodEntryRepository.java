package com.gigapet.backend.repository;

import com.gigapet.backend.models.FoodEntry;
import com.gigapet.backend.models.Gigapet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodEntryRepository  extends PagingAndSortingRepository<FoodEntry, Long> {
}
