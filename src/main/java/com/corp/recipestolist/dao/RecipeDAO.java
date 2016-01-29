package com.corp.recipestolist.dao;

import com.corp.recipestolist.model.Recipe;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeDAO {

    private List<Recipe> recipes = new ArrayList();

    public List<Recipe> getRecipes() {
        if (CollectionUtils.isEmpty(recipes)) {
            loadObjects();
        }

        return recipes;
    }

    public Recipe getRecipe(Integer id) {
        if (CollectionUtils.isEmpty(recipes)) {
            loadObjects();
        }

        List<Recipe> recipeId =  recipes.stream().filter(r -> r.getId().equals(id)).collect(Collectors.toList());

        return CollectionUtils.isNotEmpty(recipeId)?recipeId.get(0):null;
    }

    private void loadObjects() {
        try {
            URL jsonFile = getClass().getResource("/recipes.json");
            ObjectMapper objectMapper = new ObjectMapper();
            recipes = objectMapper.readValue(new File(jsonFile.getFile()), new TypeReference<List<Recipe>>() {});

        }
        catch(IOException e){
            //LOG error in next version
        }
    }
}
