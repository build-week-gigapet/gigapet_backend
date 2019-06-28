package com.gigapet.backend.services;

import com.gigapet.backend.models.Child;
import com.gigapet.backend.models.FoodEntry;
import com.gigapet.backend.models.Gigapet;
import com.gigapet.backend.repository.ChildRepository;
import com.gigapet.backend.repository.GigapetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (childrepos.findById(id).isPresent()) {
            childrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Child save(Child child) {
        Child newChild = new Child();
        newChild.setFavorites(child.getFavorites());
        newChild.setName(child.getName());
        newChild.setAllergies(child.getAllergies());

        ArrayList<Gigapet> newGigapets = new ArrayList<>();
        for (Gigapet g : child.getGigapets()) {
            newGigapets.add(new Gigapet(g.getName(),newChild, g.getState()));
        }
        newChild.setGigapets(newGigapets);

        ArrayList<FoodEntry> newFoodEntries = new ArrayList<>();
        for (FoodEntry f : child.getFoodEntries()) {
            //Child child, int category, long dateAdded, boolean isUsed
            newFoodEntries.add(new FoodEntry(newChild, f.getCategory(), f.getDateAdded(), f.isUsed()));
        }
        newChild.setFoodEntries(newFoodEntries);

        return childrepos.save(newChild);
    }

    @Transactional(readOnly = false)
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
            List<Gigapet> updateGigapets = new ArrayList<>();
            updateGigapets.addAll(c.getGigapets());
            c.getGigapets().clear();
            for (Gigapet g : updateGigapets) {
                Gigapet newGigapet = gigapetService.update(g, g.getGigapetid());
                newGigapet.setChild(c);
                newChild.getGigapets().add(newGigapet);
            }

            if (c.getFoodEntries() != null) {
                List<FoodEntry> updateFoodEntry = new ArrayList<>();
                       updateFoodEntry.addAll(c.getFoodEntries());
                       c.getFoodEntries().clear();
                for (FoodEntry f : updateFoodEntry) {
                    FoodEntry newFoodEntry = foodEntryService.update(f,f.getFoodentryid());
                    newFoodEntry.setChild(c);
                    newChild.getFoodEntries().add(newFoodEntry);
                }
            }
        }

        return childrepos.save(newChild);
    }
}
