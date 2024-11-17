/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Catagory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtill;

/**
 *
 * @author Admin
 */
public class CatagoryDAO {
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Catagory getCatoryById(int catagoryId){
        try {
            cn = DBUtill.makeConnection();
            if(cn != null){
                String sql = "select [Category_ID] , [Category_Name] from \n"
                        + "[dbo].[tblCATEGORY] where [Category_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, catagoryId);
                rs = pst.executeQuery();
                if(rs.next()){
                    int id = rs.getInt(1);
                    String catagoryName = rs.getString(2);
                    Catagory cata= new Catagory(id, catagoryName);
                    return cata;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn != null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();;
            }
        }
        return null;
    }
    
    public List<Catagory> getAllCategories() {
        List<Catagory> categories = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [Category_ID], [Category_Name] FROM [dbo].[tblCATEGORY]";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Category_ID");
                    String name = rs.getString("Category_Name");
                    Catagory category = new Catagory(id, name);
                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(cn != null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();;
            }
        }
        return categories;
    }
}
