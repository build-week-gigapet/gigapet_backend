package com.gigapet.backend.services;

import com.gigapet.backend.models.Gigapet;
import com.gigapet.backend.repository.GigapetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void delete(long id) {
        if (gigapetrepos.findById(id).isPresent()) {
            gigapetrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Gigapet save(Gigapet gigapet) {
        Gigapet newGigapet = new Gigapet();
        newGigapet.setState(gigapet.getState());
        newGigapet.setName(gigapet.getName());
        newGigapet.setChild(gigapet.getChild());
        return gigapetrepos.save(newGigapet);
    }

    @Transactional
    @Override
    public Gigapet update(Gigapet g, long id) {
        Gigapet newGigapet = new Gigapet();

        if(g.getName() != null) {
            newGigapet.setName(g.getName());
        }
        if(g.getState() != 0) {
            newGigapet.setState(g.getState());
        }
        return gigapetrepos.save(newGigapet);
    }
}
