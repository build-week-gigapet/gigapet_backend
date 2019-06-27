package com.gigapet.backend.services;

import com.gigapet.backend.models.Child;

import java.util.List;

public interface ChildService {
    List<Child> findAll();

    Child findChildById(long id);

    void delete(long id);

    Child save(Child child);

    Child update(Child child, long id);
}
