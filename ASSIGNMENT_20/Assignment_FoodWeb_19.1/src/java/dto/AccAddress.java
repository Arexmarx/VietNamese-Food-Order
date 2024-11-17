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
public class AccAddress implements Serializable{
    private int addressID;
    private int accID;
    private String province;
    private String district;
    private String ward;
    private String notes;

    public AccAddress() {
    }

    public AccAddress(int addressID, int accID, String province, String district, String ward, String notes) {
        this.addressID = addressID;
        this.accID = accID;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.notes = notes;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "AccAddress{" + "addressID=" + addressID + ", accID=" + accID + ", province=" + province + ", district=" + district + ", ward=" + ward + ", notes=" + notes + '}';
    }
    
}
