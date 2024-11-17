/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class WeeklyMenuItem {
    private int id;
    private int weekID;
    private int foodID;

    public WeeklyMenuItem() {
    }

    public WeeklyMenuItem(int id, int weekID, int foodID) {
        this.id = id;
        this.weekID = weekID;
        this.foodID = foodID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeekID() {
        return weekID;
    }

    public void setWeekID(int weekID) {
        this.weekID = weekID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    @Override
    public String toString() {
        return "WeeklyMenuItem{" + "id=" + id + ", weekID=" + weekID + ", foodID=" + foodID + '}';
    }
    
}
