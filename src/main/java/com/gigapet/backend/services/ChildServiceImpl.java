package com.gigapet.backend.services;

import com.gigapet.backend.models.Child;
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
}
