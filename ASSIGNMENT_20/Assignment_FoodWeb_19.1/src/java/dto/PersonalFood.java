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
public class PersonalFood implements Serializable{
    private int id;
    private int personalID;
    private int foodID;
    private int weekDay;
    private int weekIndex;
    
    public PersonalFood() {
    }

    public PersonalFood(int id, int personalID, int foodID, int weekDay, int weekIndex) {
        this.id = id;
        this.personalID = personalID;
        this.foodID = foodID;
        this.weekDay = weekDay;
        this.weekIndex = weekIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonalID() {
        return personalID;
    }

    public void setPersonalID(int personalID) {
        this.personalID = personalID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }

    @Override
    public String toString() {
        return "PersonalFood{" + "id=" + id + ", personalID=" + personalID + ", foodID=" + foodID + ", weekDay=" + weekDay + ", weekIndex=" + weekIndex + '}';
    }

}
