package com.corp.recipestolist.services;

import com.corp.recipestolist.dao.ShoppingListDAO;
import com.corp.recipestolist.model.Ingredient;
import com.corp.recipestolist.model.ShoppingList;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shoppinglists")
public class ShoppingLists {

    ShoppingListDAO shoppingListDAO = new ShoppingListDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ShoppingList createShoppingList(@NotNull List<Ingredient> productList){
       return shoppingListDAO.createShoppingList( productList);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{uuid}")
    public ShoppingList getShoppingList(@NotNull @PathParam("id") String uuid){
        return shoppingListDAO.getShoppingList(uuid);
    }
}
