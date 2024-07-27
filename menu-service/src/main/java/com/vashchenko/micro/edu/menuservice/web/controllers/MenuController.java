package com.vashchenko.micro.edu.menuservice.web.controllers;

import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import com.vashchenko.micro.edu.menuservice.repository.DishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuController {

    DishRepository dishRepository;
    @Cacheable(value = "menuCache", key = "'menu'")
    @QueryMapping()
    @SneakyThrows
    public Iterable<Dish> dishes(){
        return dishRepository.findAll();
    }
}
