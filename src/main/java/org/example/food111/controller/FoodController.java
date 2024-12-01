package org.example.food111.controller;

import org.example.food111.service.FoodService;
import org.example.food111.entry.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;


    @PostMapping("/food/add")
    public String addFood(Food food) {
        foodService.addFood(food);
        return "success";
    }

    @GetMapping("/food/getFoods")
    public List<Food> getFood(Integer tid) {
        System.out.println(tid);
        return foodService.getFoods(tid);
    }

}
