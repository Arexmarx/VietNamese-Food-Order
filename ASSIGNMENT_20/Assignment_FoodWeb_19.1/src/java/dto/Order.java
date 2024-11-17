/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;


/**
 *
 * @author Admin
 */
public class Order {
  private int orderId;
  private int accId;
  private String cusName;
  private int totalPrice;
  private String phone;
  private Date orderDate;
  private String payment;
  private int status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order() {
    }

    public Order(int orderId, int accId, String cusName, int totalPrice, String phone, Date orderDate, String payment, int status) {
        this.orderId = orderId;
        this.accId = accId;
        this.cusName = cusName;
        this.totalPrice = totalPrice;
        this.phone = phone;
        this.orderDate = orderDate;
        this.payment = payment;
        this.status = status;
    }
  
    
  
  
}
