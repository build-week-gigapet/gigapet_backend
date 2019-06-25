package com.gigapet.backend.services;

import com.gigapet.backend.models.FoodEntry;

import java.util.List;

public interface FoodEntryService {
    List<FoodEntry> findAll();

    FoodEntry findFoodEntryById(long id);
}
