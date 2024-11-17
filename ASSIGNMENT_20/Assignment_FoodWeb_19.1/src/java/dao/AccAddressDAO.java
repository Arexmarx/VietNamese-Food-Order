/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtill;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class AccAddressDAO {
     Connection cn= null;
     PreparedStatement ps= null;
     ResultSet rs= null;
    
    public AccAddress getAccAddress(int accid){
        AccAddress address= null;
        try {
            cn= DBUtill.makeConnection();
            if (cn!= null) {
                String querry="select [Address_ID],[Account_ID],[Province],[District],[Ward],[Note]\n" +
                            "from [dbo].[tblADDRESS_ACCOUNT]\n" +
                            "where [Account_ID]=?";
                ps= cn.prepareCall(querry);
                ps.setInt(1, accid);
                rs=ps.executeQuery();
                if(rs.next()){
                    int addressID= rs.getInt(1);
                    int accID= rs.getInt(2);
                    String province= rs.getString(3);
                    String district= rs.getString(4);
                    String ward= rs.getString(5);
                    String notes= rs.getString(6);
                    
                    address= new AccAddress(addressID, accID, province, district, ward, notes);
                    return address;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (cn!= null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return address;
    }
    
    public List<AccAddress> getAllAccAddress(){
        AccAddress address= null;
        List<AccAddress> list= new ArrayList<AccAddress>();
        try {
            cn= DBUtill.makeConnection();
            if (cn!= null) {
                String querry="select [Address_ID],[Account_ID],[Province],[District],[Ward],[Note]\n" +
                            "from [dbo].[tblADDRESS_ACCOUNT]";
                Statement st= cn.createStatement();
                rs= st.executeQuery(querry);
                while(rs.next()){
                    int addressID= rs.getInt(1);
                    int accID= rs.getInt(2);
                    String province= rs.getString(3);
                    String district= rs.getString(4);
                    String ward= rs.getString(5);
                    String notes= rs.getString(6);
                    
                    address= new AccAddress(addressID, accID, province, district, ward, notes);
                    list.add(address);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (cn!= null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    //Insert Address of user
    public int insertAccount(int accid, String province, String district, String ward, String notes){
        int result=0;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String sql="insert into [dbo].[tblADDRESS_ACCOUNT] (Account_ID,Province,District,Ward,Note)\n" +
                            "values (?,?,?,?,?)";
                ps= cn.prepareStatement(sql);
                ps.setInt(1, accid);
                ps.setString(2, province);
                ps.setString(3, district);
                ps.setString(4, ward);
                ps.setString(5, notes);
                
                result= ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(cn!= null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static void main(String[] args) {
        AccAddressDAO d= new AccAddressDAO();
        for (AccAddress s : d.getAllAccAddress()) {
            System.out.println(s);
        }
    }
}
