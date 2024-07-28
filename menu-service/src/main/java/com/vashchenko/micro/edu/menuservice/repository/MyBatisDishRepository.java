package com.vashchenko.micro.edu.menuservice.repository;

import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface MyBatisDishRepository {
    void save(Dish dish);
    List<Dish> findAll();
    List<Dish> selectDishesForPage(@Param("offset") int offset, @Param("size") int size);
    Dish findById(Long id);

    Integer countDishes();
}
