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
public class Ingredient implements Serializable{
    private int ingredientId;
    private String name;
    private boolean isAvailable;
    private String measurement;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Ingredient() {
    }

    public Ingredient(int ingredientId, String name, boolean isAvailable, String measurement) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.isAvailable = isAvailable;
        this.measurement = measurement;
    }

    public Ingredient(int ingredientId, String name, String measurement) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.measurement = measurement;
    }

    
    
    @Override
    public String toString() {
        return "Ingredient{" + "ingredientId=" + ingredientId + ", name=" + name + ", isAvailable=" + isAvailable + ", measurement=" + measurement + '}';
    }
    
    
}
