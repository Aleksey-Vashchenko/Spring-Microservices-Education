package com.vashchenko.micro.edu.menuservice.web.controllers;

import com.vashchenko.micro.edu.menuservice.model.dto.response.graphql.DishPage;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import com.vashchenko.micro.edu.menuservice.model.service.DishService;
import com.vashchenko.micro.edu.menuservice.repository.MyBatisDishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuController {

    DishService dishService;
    @Cacheable(value = "menuCache", key = "'menu'")
    @QueryMapping()
    public Iterable<Dish> allDishes(){
        return dishService.findAll();
    }

    @Cacheable(value = "menuCache", key = "'menu'")
    @QueryMapping()
    public DishPage pageableDishes(@Argument int page, @Argument int size){
        return dishService.findDishPage(size,page);
    }
}
