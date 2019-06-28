package com.gigapet.backend.services;

import com.gigapet.backend.models.FoodEntry;
import com.gigapet.backend.models.User;

import java.util.List;

public interface FoodEntryService {
    List<FoodEntry> findAll();

    FoodEntry findFoodEntryById(long id);

    void delete(long id);

    FoodEntry save(FoodEntry foodEntry);

    FoodEntry update(FoodEntry foodEntry, long id);
}
