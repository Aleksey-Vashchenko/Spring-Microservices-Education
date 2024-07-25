package com.vashchenko.micro.edu.menuservice.repository;

import com.vashchenko.micro.edu.menuservice.model.MenuItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface MenuItemRepository {
    void save(MenuItem menuItem);
    List<MenuItem> getAll();

    MenuItem findById(Long id);
}
