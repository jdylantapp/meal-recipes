package com.jdylantapp.meal_recipes_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MealDbService {

    private String baseUrl = "https://www.themealdb.com/api/json/v1/1";

    private final RestClient restClient;

    public MealDbService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
    }

    //GET RECIPE FROM MEALDB USING KEYWORDS
    public String searchMeals(String query) {
        return this.restClient.get().uri("/search.php?s={query}", query).retrieve().body(String.class);
    }

    //GET SINGLE RECIPE FROM MEALDB USING MEALID
    public String getMealById(String mealId) {
        return this.restClient.get().uri("/lookup.php?i={mealId}", mealId).retrieve().body(String.class);
    }
}
