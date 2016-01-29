package com.corp.recipestolist.services;

import com.corp.recipestolist.model.Recipe;
import com.corp.recipestolist.dao.RecipeDAO;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/recipes")
public class Recipes {

    RecipeDAO recipeDAO = new RecipeDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recipe> findAllRecipes(){
        return recipeDAO.getRecipes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Recipe findRecipe(@NotNull @PathParam("id") Integer id){
        return recipeDAO.getRecipe(id);
    }
}
