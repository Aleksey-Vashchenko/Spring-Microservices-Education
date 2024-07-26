package com.vashchenko.micro.edu.menuservice.model.mapping;

import com.vashchenko.micro.edu.menuservice.model.dto.request.DishDto;
import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class DishMapper {

    public abstract Dish toEntity(DishDto dto);
}
