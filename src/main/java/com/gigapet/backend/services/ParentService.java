package com.gigapet.backend.services;



import com.gigapet.backend.models.Parent;

import java.util.List;

public interface ParentService
{
    List<Parent> findAll();

    Parent findParentById(long id);

    List<Parent> findByUserName(String username);

    void delete(long id);

    Parent save(Parent quote);
}
