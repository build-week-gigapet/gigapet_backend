package com.gigapet.backend.services;

import com.gigapet.backend.models.Parent;
import com.gigapet.backend.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "parentService")
public class ParentServiceImpl implements ParentService
{
    @Autowired
    private ParentRepository parentrepos;

    @Override
    public List<Parent> findAll()
    {
        List<Parent> list = new ArrayList<>();
        parentrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Parent findParentById(long id)
    {
        return parentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (parentrepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (parentrepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                parentrepos.deleteById(id);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Parent save(Parent quote)
    {
        return parentrepos.save(quote);
    }

    @Override
    public List<Parent> findByUserName(String username)
    {
        List<Parent> list = new ArrayList<>();
        parentrepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
