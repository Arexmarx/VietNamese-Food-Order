/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.OrderAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import utils.DBUtill;

/**
 *
 * @author Admin
 */
public class OrderAddressDAO {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public ArrayList<OrderAddress> getAllOrderAddress(){
        ArrayList list = new ArrayList();
        try {
            cn = DBUtill.makeConnection();
            if(cn!= null){
                String sql="select [Address_ID],[O_ID],[Province],[District],[Ward],[Note] from \n"
                        + "[dbo].[tblADDRESS_ORDER]";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int addressId = rs.getInt(1);
                        int orderId = rs.getInt(2);
                        String province = rs.getString(3);
                        String district = rs.getString(4);
                        String ward = rs.getString(5);
                        String note = rs.getString(6);
                        OrderAddress ordA = new OrderAddress(addressId, orderId, province, district, ward, note);
                        list.add(ordA);
                    }
                }
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cn!= null)
                    cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public OrderAddress getOrderAddress(int orderId){
        OrderAddress ordA;
        try {
            cn = DBUtill.makeConnection();
            if(cn != null){
                String sql="select [Address_ID],[O_ID],[Province],[District],[Ward],[Note] from \n"
                        + "[dbo].[tblADDRESS_ORDER] where [O_ID] = ? ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderId);
                rs = pst.executeQuery();
                if(rs.next()){
                    int addressId = rs.getInt(1);
                    int ordId = rs.getInt(2);
                    String province = rs.getString(3);
                    String district = rs.getString(4);
                    String ward = rs.getString(5);
                    String note = rs.getString(6);
                    ordA = new OrderAddress(addressId, ordId, province, district, ward, note);
                    return ordA;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cn != null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public int saveOrderAddress(String province,String district,String ward,String note){
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if(cn != null){
                String sql="select top 1 [O_ID] from [dbo].[tblORDER] \n"
                     + "order by [O_ID] desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs != null && rs.next()){
                    int orderId = rs.getInt("O_ID");
                    sql="insert into [dbo].[tblADDRESS_ORDER] \n"
                       + "values (?,?,?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderId);
                    pst.setString(2, province);
                    pst.setString(3, district);
                    pst.setString(4, ward);
                    pst.setString(5, note);
                    result = pst.executeUpdate();
                    cn.commit();
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cn != null){
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        OrderAddressDAO dao = new OrderAddressDAO();
        List<OrderAddress> list = dao.getAllOrderAddress();
        HashMap<String, HashMap<String, HashMap<String, List<OrderAddress>>>> map = new HashMap<>();
        
        for (OrderAddress o : list) {
            map.computeIfAbsent(o.getProvince(), k -> new HashMap<>())
               .computeIfAbsent(o.getDistrict(), k ->new HashMap<>())
               .computeIfAbsent(o.getWard(), k -> new ArrayList<>())
               .add(o);
        }

        
        for (String province : map.keySet()) {
            System.out.println("Province: " + province);
            HashMap<String, HashMap<String, List<OrderAddress>>> districtMap = map.get(province);
            for (String district : districtMap.keySet()) {
                System.out.println("  District: " + district);
                HashMap<String, List<OrderAddress>> wardMap = districtMap.get(district);
                for (String ward: wardMap.keySet()) {
                    System.out.println("    Ward: " + ward);
                    List<OrderAddress> address= wardMap.get(ward);
                    for (OrderAddress addres : address) {
                        System.out.println("     "+addres);
                    }
                }
            }
        }
    }
}
