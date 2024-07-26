package com.vashchenko.micro.edu.menuservice.model.mapping;

import com.vashchenko.micro.edu.menuservice.model.dto.request.DishDto;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DishMapperTest {

    @Autowired
    DishMapper dishMapper;

    @Test
    void toEntity() {
        DishDto dto = Instancio.of(DishDto.class).create();
        Dish dish = dishMapper.toEntity(dto);
        assertEquals(dto.description(),dish.getDescription());
        assertEquals(dto.name(),dish.getName());
        assertEquals(dto.price(),dish.getPrice());
        assertEquals(dto.weight(),dish.getWeight());
    }
}