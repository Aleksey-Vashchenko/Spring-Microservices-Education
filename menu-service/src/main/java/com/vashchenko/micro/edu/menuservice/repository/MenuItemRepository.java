package com.vashchenko.micro.edu.menuservice.repository;

import com.vashchenko.micro.edu.menuservice.model.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    void save(MenuItem menuItem);
    List<MenuItem> getAll();

    MenuItem findById(Long id);
}
