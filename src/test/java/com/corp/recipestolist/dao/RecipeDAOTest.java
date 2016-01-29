package com.corp.recipestolist.dao;

import com.corp.recipestolist.model.Recipe;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class RecipeDAOTest {

    private RecipeDAO recipeDAO = new RecipeDAO();

    @Test
    public void getRecipes_should_return_json_data(){

        //not much to mock there !
        List<Recipe> recipes = recipeDAO.getRecipes();

        assertTrue(CollectionUtils.isNotEmpty(recipes));
        assertThat(recipes.size()).isEqualTo(10);
    }

    @Test
    public void getRecipe_should_return_right_recipe(){
        Recipe recipeTwo = recipeDAO.getRecipe(2);

        assertTrue(recipeTwo!=null);
        assertThat(recipeTwo.getId()).isEqualTo(2);
        assertThat(recipeTwo.getTitle()).isEqualTo("Crab And Apple Coleslaw");
        assertTrue(CollectionUtils.isNotEmpty(recipeTwo.getIngredients()));
        assertThat(recipeTwo.getIngredients().size()).isEqualTo(12);
    }

}