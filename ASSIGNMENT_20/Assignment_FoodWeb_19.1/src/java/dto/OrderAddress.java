/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Admin
 */
public class OrderAddress {
    private int addressId;
    private int orderId;
    private String province;
    private String district;
    private String ward;
    private String note;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderAddress() {
    }

    public OrderAddress(int addressId, int orderId, String province, String district, String ward, String note) {
        this.addressId = addressId;
        this.orderId = orderId;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.note = note;
    }
    
    
}
