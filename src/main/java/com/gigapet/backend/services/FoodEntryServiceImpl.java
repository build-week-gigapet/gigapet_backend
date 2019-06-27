package com.gigapet.backend.services;

import com.gigapet.backend.models.FoodEntry;
import com.gigapet.backend.models.Gigapet;
import com.gigapet.backend.repository.FoodEntryRepository;
import com.gigapet.backend.repository.GigapetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "foodEntryService")
public class FoodEntryServiceImpl implements FoodEntryService{

    @Autowired
    private FoodEntryRepository foodEntryRepository;

    @Override
    public List<FoodEntry> findAll()
    {
        List<FoodEntry> list = new ArrayList<>();
        foodEntryRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public FoodEntry findFoodEntryById(long id)
    {
        return foodEntryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public FoodEntry save(FoodEntry foodEntry) {
        return null;
    }

    @Override
    public FoodEntry update(FoodEntry f, long id) {
        FoodEntry newFoodEntry = new FoodEntry();

        newFoodEntry.setUsed(f.isUsed());

        newFoodEntry.setCategory(f.getCategory());

        newFoodEntry.setDateAdded(f.getDateAdded());

        newFoodEntry.setDateChanged(System.currentTimeMillis());

        return newFoodEntry;
    }
}
