package com.vashchenko.micro.edu.menuservice.model.dto.request;

import com.vashchenko.micro.edu.menuservice.model.validation.annotation.MoneyType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record DishDto(@NotEmpty String name,
                      String description,
                      @MoneyType
                      @Min(value = 0, message = "Price must be non-negative")
                      Double price,
                      Integer weight) {
}
