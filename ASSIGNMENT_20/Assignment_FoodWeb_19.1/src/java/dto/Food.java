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
public class Food implements Serializable{
    private int id;
    private String name;
    private int price;
    private String origin;
    private String imgURL;
    private int catagoryId;
    private String description;
    private String wayCooking;
    
    public Food() {
    }

    public Food(String name, int price, String origin, String imgURL, int catagoryId, String description, String wayCooking) {
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.imgURL = imgURL;
        this.catagoryId = catagoryId;
        this.description = description;
        this.wayCooking = wayCooking;
    }
    
    
    public Food(int id, String name, int price, String origin, String imgURL, int catagoryId, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.imgURL = imgURL;
        this.catagoryId = catagoryId;
        this.description = description;
    }

    public Food(int id, String name, int price, String origin, String imgURL, int catagoryId, String description, String wayCooking) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.imgURL = imgURL;
        this.catagoryId = catagoryId;
        this.description = description;
        this.wayCooking = wayCooking;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWayCooking() {
        return wayCooking;
    }

    public void setWayCooking(String wayCooking) {
        this.wayCooking = wayCooking;
    }

    @Override
    public String toString() {
        return "Food{" + "id=" + id + ", name=" + name + ", price=" + price + ", origin=" + origin + ", imgURL=" + imgURL + ", catagoryId=" + catagoryId + ", description=" + description + ", wayCooking=" + wayCooking + '}';
    }
    
}
