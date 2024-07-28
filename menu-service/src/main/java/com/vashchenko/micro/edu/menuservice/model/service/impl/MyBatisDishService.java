package com.vashchenko.micro.edu.menuservice.model.service.impl;

import com.vashchenko.micro.edu.menuservice.model.dto.response.graphql.DishPage;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import com.vashchenko.micro.edu.menuservice.model.service.DishService;
import com.vashchenko.micro.edu.menuservice.repository.MyBatisDishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class MyBatisDishService implements DishService {

    MyBatisDishRepository dishRepository;

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public DishPage findDishPage(int size, int page) {
        int offset = (page - 1) * size;
        List<Dish> dishes = dishRepository.selectDishesForPage(offset, size);
        int totalElements = dishRepository.countDishes();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        return new DishPage(dishes, totalElements, totalPages, page, size);
    }

    @Override
    public Dish save(Dish dish) {
        dishRepository.save(dish);
        return dish;
    }
}
