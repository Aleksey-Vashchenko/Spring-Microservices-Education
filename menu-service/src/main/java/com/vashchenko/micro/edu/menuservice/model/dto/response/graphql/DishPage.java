package com.vashchenko.micro.edu.menuservice.model.dto.response.graphql;

import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishPage {
    List<Dish> content;
    int totalElements;
    int totalPages;
    int currentPage;
    int pageSize;
}