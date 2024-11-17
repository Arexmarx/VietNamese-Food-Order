/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class Recipe implements Serializable{
    private int recipeId;
    private int foodId;
    private int ingredientId;
    private float quantity;
    private String measurement;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Recipe() {
    }

    public Recipe(int recipeId, int foodId, int ingredientId, float quantity, String measurement) {
        this.recipeId = recipeId;
        this.foodId = foodId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Recipe(int ingredientId, float quantity, String measurement) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipeId=" + recipeId + ", foodId=" + foodId + ", ingredientId=" + ingredientId + ", quantity=" + quantity + ", measurement=" + measurement + '}';
    }
}
