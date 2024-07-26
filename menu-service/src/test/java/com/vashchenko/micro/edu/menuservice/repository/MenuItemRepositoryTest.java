package com.vashchenko.micro.edu.menuservice.repository;

import com.vashchenko.micro.edu.menuservice.model.MenuItem;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class MenuItemRepositoryTest {

    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void clearDatabase() {
        jdbcTemplate.update("TRUNCATE TABLE menu_items");
    }

    @Test
    void save() {
        MenuItem item = Instancio.of(MenuItem.class).ignore(field(MenuItem::getId)).create();
        menuItemRepository.save(item);
        assertNotNull(item.getId());
    }

    @Test
    void getAll() {
        List<MenuItem> itemList = Instancio.ofList(MenuItem.class).size(3).ignore(field(MenuItem::getId)).create();
        for (MenuItem item : itemList) {
            menuItemRepository.save(item);
        }
        List<MenuItem> items = menuItemRepository.findAll();
        assertEquals(items.size(),itemList.size());
    }

    @Test
    void findById() {
        MenuItem item = Instancio.of(MenuItem.class).ignore(field(MenuItem::getId)).create();
        menuItemRepository.save(item);
        MenuItem itemFromBd = menuItemRepository.findById(item.getId());
        assertEquals(item,itemFromBd);
    }
}