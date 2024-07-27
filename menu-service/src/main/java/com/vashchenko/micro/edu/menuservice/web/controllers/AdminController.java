package com.vashchenko.micro.edu.menuservice.web.controllers;

import com.vashchenko.micro.edu.menuservice.model.dto.request.DishDto;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import com.vashchenko.micro.edu.menuservice.model.mapping.DishMapper;
import com.vashchenko.micro.edu.menuservice.repository.DishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    DishRepository dishRepository;
    DishMapper dishMapper;

    @PatchMapping("dishes/{dishId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateItem(@RequestBody DishDto request, @PathVariable(name = "dishId") Long dishId){
        Dish dish = dishMapper.toEntity(request);
        dish.setId(dishId);
        dishRepository.save(dish);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("dishes")
    void createItem(@RequestBody DishDto request){
        dishRepository.save(dishMapper.toEntity(request));
    }
}
