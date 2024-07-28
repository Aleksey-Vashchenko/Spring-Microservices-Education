package com.vashchenko.micro.edu.menuservice.web.controllers;

import com.vashchenko.micro.edu.menuservice.model.dto.request.DishDto;
import com.vashchenko.micro.edu.menuservice.model.dto.response.Response;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import com.vashchenko.micro.edu.menuservice.model.mapping.DishMapper;
import com.vashchenko.micro.edu.menuservice.model.service.DishService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    DishService dishService;
    DishMapper dishMapper;

    @PatchMapping("dishes/{dishId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value = "menuCache", key = "'menu'")
    public void updateItem(@Valid @RequestBody DishDto request, @PathVariable(name = "dishId") Long dishId){
        Dish dish = dishMapper.toEntity(request);
        dish.setId(dishId);
        dishService.save(dish);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("dishes")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "menuCache", key = "'menu'")
    public Response<Map<String,Long>> createItem(@Valid  @RequestBody DishDto request){
        Dish dishToCreate = dishMapper.toEntity(request);
        dishService.save(dishToCreate);
        return new Response<>(Map.of("createdId",dishToCreate.getId()));
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CacheEvict(value = "menuCache", key = "'menu'")
    public Dish createDish(@Argument DishDto input){
        return dishService.save(dishMapper.toEntity(input));
    }
    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CacheEvict(value = "menuCache", key = "'menu'")
    public Dish updateDish(@Argument Long id, @Argument DishDto input){
        Dish dishToSave = dishMapper.toEntity(input);
        dishToSave.setId(id);
        return dishService.save(dishToSave);
    }



}
