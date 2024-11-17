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
public class PersonalWeek implements Serializable{
    private int id;
    private int personalID;
    private int weekID;
    private int weekIndex;

    public PersonalWeek() {
    }

    public PersonalWeek(int id, int personalID, int weekID, int weekIndex) {
        this.id = id;
        this.personalID = personalID;
        this.weekID = weekID;
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

    public int getWeekID() {
        return weekID;
    }

    public void setWeekID(int weekID) {
        this.weekID = weekID;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }

    @Override
    public String toString() {
        return "PersonalWeek{" + "id=" + id + ", personalID=" + personalID + ", weekID=" + weekID + ", weekIndex=" + weekIndex + '}';
    }
    
    
}
