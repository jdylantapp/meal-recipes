package com.jdylantapp.meal_recipes_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdylantapp.meal_recipes_backend.entity.SavedRecipe;
import com.jdylantapp.meal_recipes_backend.entity.User;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipe, Long> {

    List<SavedRecipe> findRecipesByUser(User user);

    boolean existsByUserAndMealId(User user, String mealId);

    Optional<SavedRecipe> findByUserAndMealId(User user, String mealId);
    
}
