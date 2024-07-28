package com.vashchenko.micro.edu.menuservice.model.service;

import com.vashchenko.micro.edu.menuservice.model.dto.response.graphql.DishPage;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;

import java.util.List;

public interface DishService {

    Dish findById(Long id);
    List<Dish> findAll();
    DishPage findDishPage(int size, int page);
    Dish save(Dish dish);

}
