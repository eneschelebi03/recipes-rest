package com.uni.receipesrest.controller;

import com.uni.receipesrest.model.dto.RecipeDto;
import com.uni.receipesrest.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeContoller {

    private final RecipeService recipeService;

    public RecipeContoller(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public ResponseEntity<RecipeDto> getSingleRecipe(@PathVariable("id") String id) {

        RecipeDto recipe = this.recipeService.getRecipe(id);

        return ResponseEntity.ok(recipe);
    }
}
