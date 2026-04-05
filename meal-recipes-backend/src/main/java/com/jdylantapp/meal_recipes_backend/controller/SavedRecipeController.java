package com.jdylantapp.meal_recipes_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.jdylantapp.meal_recipes_backend.service.SavedRecipeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class SavedRecipeController {
    
    @Autowired
    private SavedRecipeService recipeService;

    //SAVE RECIPE TO USERS SAVED LIST
    @PostMapping("/users/saved")
    public String saveRecipe(@RequestParam String mealId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recipeService.saveRecipe(email, mealId);
    }

    //REMOVE RECIPE FROM USERS SAVED LIST
    @DeleteMapping("/users/saved/{mealId}")
    public String deleteRecipe(@PathVariable("mealId") String mealId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recipeService.unsaveRecipe(email, mealId);
    }

    //GET USERS SAVED RECIPES
    @GetMapping("/users/saved")
    public List<String> getSavedRecipes() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recipeService.getSavedRecipes(email);
    }
     
}
