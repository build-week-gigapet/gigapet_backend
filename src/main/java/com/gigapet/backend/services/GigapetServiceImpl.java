package com.gigapet.backend.services;

import com.gigapet.backend.models.Gigapet;
import com.gigapet.backend.repository.GigapetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "gigapetService")
public class GigapetServiceImpl implements GigapetService {

    @Autowired
    private GigapetRepository gigapetrepos;

    @Override
    public List<Gigapet> findAll()
    {
        List<Gigapet> list = new ArrayList<>();
        gigapetrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Gigapet findGigapetById(long id)
    {
        return gigapetrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

}
