package com.vashchenko.micro.edu.menuservice.repository;

import com.vashchenko.micro.edu.menuservice.model.MenuItem;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class MenuItemRepositoryTest {

    @Autowired
    MenuItemRepository menuItemRepository;

    @Test
    void save() {
        MenuItem item = Instancio.of(MenuItem.class).ignore(field(MenuItem::getId)).create();
        menuItemRepository.save(item);
        assertNotNull(item.getId());
    }

    @Test
    void getAll() {
    }

    @Test
    void findById() {
    }
}