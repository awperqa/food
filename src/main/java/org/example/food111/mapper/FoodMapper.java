package org.example.food111.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.food111.entry.Food;

import java.util.List;

@Mapper
public interface FoodMapper {

    void addFood(Food food);

    List<Food> getFoods(Integer tno);


}
