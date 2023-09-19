package com.uni.receipesrest.controller;

import com.uni.receipesrest.model.dto.RecipeDto;
import com.uni.receipesrest.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeContoller {

    private final RecipeService recipeService;

    public RecipeContoller(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getSingleRecipe(@PathVariable("id") String id) {
        RecipeDto recipe = this.recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> recipes = this.recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRecipe(@RequestBody RecipeDto recipeDto) {
        this.recipeService.saveRecipe(recipeDto);
        return ResponseEntity.ok(recipeDto.getTitle() + " save successfully!");
    }
}
