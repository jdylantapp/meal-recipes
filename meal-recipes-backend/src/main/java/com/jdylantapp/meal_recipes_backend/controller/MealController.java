package com.jdylantapp.meal_recipes_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jdylantapp.meal_recipes_backend.service.MealDbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MealController {

    @Autowired
    private MealDbService mealService;

    //SEARCH RECIPES FROM MEALDB USING KEYWORDS
    @GetMapping("/recipes/search")
    public String getMeals(@RequestParam String query) {
        return mealService.searchMeals(query);
    }

    //GET RECIPE FROM MEALDB USING MEALID
    @GetMapping("/recipes/{mealId}")
    public String getMealbyId(@PathVariable("mealId") String mealId) {
        return mealService.getMealById(mealId);
    }   
    
}
