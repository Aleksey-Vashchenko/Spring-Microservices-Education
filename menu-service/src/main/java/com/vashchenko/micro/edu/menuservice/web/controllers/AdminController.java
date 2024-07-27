package com.vashchenko.micro.edu.menuservice.web.controllers;

import com.vashchenko.micro.edu.menuservice.model.dto.request.DishDto;
import com.vashchenko.micro.edu.menuservice.model.dto.response.Response;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import com.vashchenko.micro.edu.menuservice.model.mapping.DishMapper;
import com.vashchenko.micro.edu.menuservice.repository.DishRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    DishRepository dishRepository;
    DishMapper dishMapper;

    @PatchMapping("dishes/{dishId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    void updateItem(@Valid @RequestBody DishDto request, @PathVariable(name = "dishId") Long dishId){
        Dish dish = dishMapper.toEntity(request);
        dish.setId(dishId);
        dishRepository.save(dish);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("dishes")
    @ResponseStatus(HttpStatus.CREATED)
    Response<Map<String,Long>> createItem(@Valid  @RequestBody DishDto request){
        Dish dishToCreate = dishMapper.toEntity(request);
        dishRepository.save(dishToCreate);
        return new Response<>(Map.of("createdId",dishToCreate.getId()));
    }
}
