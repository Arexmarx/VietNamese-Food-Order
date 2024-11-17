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
public class PersonalPlan implements Serializable{
    private int id;
    private int accid;
    private int weeklyNumber;
    private String name;
    
    public PersonalPlan() {
    }

    public PersonalPlan(int id, int accid, int weeklyNumber) {
        this.id = id;
        this.accid = accid;
        this.weeklyNumber = weeklyNumber;
    }

    public PersonalPlan(int id, int accid, int weeklyNumber, String name) {
        this.id = id;
        this.accid = accid;
        this.weeklyNumber = weeklyNumber;
        this.name = name;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public int getWeeklyNumber() {
        return weeklyNumber;
    }

    public void setWeeklyNumber(int weeklyNumber) {
        this.weeklyNumber = weeklyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonalPlan{" + "id=" + id + ", accid=" + accid + ", weeklyNumber=" + weeklyNumber + ", name=" + name + '}';
    }
}