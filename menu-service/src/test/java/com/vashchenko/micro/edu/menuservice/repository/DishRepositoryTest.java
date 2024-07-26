package com.vashchenko.micro.edu.menuservice.repository;

import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
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
class DishRepositoryTest {

    @Autowired
    DishRepository dishRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void clearDatabase() {
        jdbcTemplate.update("TRUNCATE TABLE dishes");
    }

    @Test
    void save() {
        Dish dish = Instancio.of(Dish.class).ignore(field(Dish::getId)).create();
        dishRepository.save(dish);
        assertNotNull(dish.getId());
    }

    @Test
    void getAll() {
        List<Dish> itemList = Instancio.ofList(Dish.class).size(3).ignore(field(Dish::getId)).create();
        for (Dish item : itemList) {
            dishRepository.save(item);
        }
        List<Dish> items = dishRepository.findAll();
        assertEquals(items.size(),itemList.size());
    }

    @Test
    void findById() {
        Dish item = Instancio.of(Dish.class).ignore(field(Dish::getId)).create();
        dishRepository.save(item);
        Dish itemFromBd = dishRepository.findById(item.getId());
        assertEquals(item,itemFromBd);
    }
}