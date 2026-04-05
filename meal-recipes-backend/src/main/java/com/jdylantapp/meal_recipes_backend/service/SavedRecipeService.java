package com.jdylantapp.meal_recipes_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdylantapp.meal_recipes_backend.entity.SavedRecipe;
import com.jdylantapp.meal_recipes_backend.entity.User;
import com.jdylantapp.meal_recipes_backend.repository.SavedRecipeRepository;
import com.jdylantapp.meal_recipes_backend.repository.UserRepository;

@Service
public class SavedRecipeService {

    @Autowired
    private SavedRecipeRepository recipeRepository;

    @Autowired
    private MealDbService mealService;

    @Autowired
    private UserRepository userRepository;

    //SAVE RECIPE
    public String saveRecipe(String email, String mealId) {
        User foundUser = userRepository.findUserByEmail(email).orElseThrow();

        if (recipeRepository.existsByUserAndMealId(foundUser, mealId)) {
            throw new IllegalArgumentException("Error: recipe is already saved for this User");
        }

        SavedRecipe newRecipe = new SavedRecipe(foundUser, mealId);

        recipeRepository.save(newRecipe);

        return "Successfully saved recipe to list";
    }

    //UNSAVE RECIPE
    public String unsaveRecipe(String email, String mealId) {
        User foundUser = userRepository.findUserByEmail(email).orElseThrow();

        if (!recipeRepository.existsByUserAndMealId(foundUser, mealId)) {
            throw new IllegalArgumentException("Error: recipe is not saved for this User");
        }

        SavedRecipe recipeToDelete = recipeRepository.findByUserAndMealId(foundUser, mealId).orElseThrow();

        recipeRepository.delete(recipeToDelete);

        return "Successfully deleted recipe from list";
    }

    //GET SAVED RECIPES
    public List<String> getSavedRecipes(String email) {
        User foundUser = userRepository.findUserByEmail(email).orElseThrow();

        List<SavedRecipe> foundRecipes = recipeRepository.findRecipesByUser(foundUser);

        List<String> recipeList = new ArrayList<>();

        for (SavedRecipe recipe:foundRecipes) {
            String recipeString = mealService.getMealById(recipe.getMealId());
            recipeList.add(recipeString);
        }

        return recipeList;
    }
    
}
