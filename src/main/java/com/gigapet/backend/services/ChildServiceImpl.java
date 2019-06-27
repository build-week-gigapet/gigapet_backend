package com.gigapet.backend.services;

import com.gigapet.backend.models.Child;
import com.gigapet.backend.models.FoodEntry;
import com.gigapet.backend.models.Gigapet;
import com.gigapet.backend.repository.ChildRepository;
import com.gigapet.backend.repository.GigapetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "childService")
public class ChildServiceImpl implements ChildService {
    @Autowired
    private ChildRepository childrepos;

    @Autowired
    private FoodEntryServiceImpl foodEntryService;

    @Autowired
    private GigapetServiceImpl gigapetService;

    @Override
    public List<Child> findAll()
    {
        List<Child> list = new ArrayList<>();
        childrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Child findChildById(long id)
    {
        return childrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Child save(Child child) {
        return null;
    }

    @Override
    public Child update(Child c, long id) {
        Child newChild = new Child();

        if(c.getName() != null){
            newChild.setName(c.getName());
        }

        if(c.getAllergies() != null){
            newChild.setAllergies(c.getAllergies());
        }

        if(c.getFavorites() != null){
            newChild.setFavorites(c.getFavorites());
        }

        if(c.getName() != null){
            newChild.setName(c.getName());
        }
        if(c.getName() != null){
            newChild.setName(c.getName());
        }

        if(c.getGigapets() != null) {
            for (Gigapet g : c.getGigapets()) {
                Gigapet newGigapet = gigapetService.update(g, g.getGigapetid());
                newGigapet.setChild(c);
                c.getGigapets().add(newGigapet);
            }

            if (c.getFoodEntries() != null) {
                for (FoodEntry f : c.getFoodEntries()) {
                    FoodEntry newFoodEntry = foodEntryService.update(f,f.getFoodentryid());
                    newFoodEntry.setChild(c);
                    c.getFoodEntries().add(newFoodEntry);             }
            }
        }

        return newChild;
    }
}
