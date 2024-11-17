/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
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
public class AccountDAO {
    private Connection cn= null;
    private PreparedStatement ps= null;
    private ResultSet rs= null;
    
    public Account getAccount(String userName, String password){
        Account acc;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String querry="select [Account_ID],[UserName],[Password],[PhoneNumber],[Role],[FullName],[Email],[IsActive]"
                        + "from [dbo].[tblACCOUNT]\n" +
                            "where [UserName]= ? and [Password]= ?";
                ps=cn.prepareStatement(querry);
                ps.setString(1, userName);
                ps.setString(2, password);
                rs=ps.executeQuery();
                if(rs.next()){
                    int accid= rs.getInt(1);
                    String user= rs.getString(2);
                    String pass= rs.getString(3);
                    String phone= rs.getString(4);
                    String role= rs.getBoolean(5)?"Admin":"User";
                    String fullName= rs.getString(6);
                    String email= rs.getString(7);
                    boolean isActive= rs.getBoolean(8);
                    
                    acc= new Account(accid, user, pass, phone, role, fullName, email, isActive);
                    return acc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn!= null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public List<Account> getAllAccount(){
        List<Account> list= new ArrayList<>();
        Account acc= null;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String querry= "select [Account_ID],[UserName],[Password],[PhoneNumber],[Role],[FullName],[Email],[IsActive]"
                        + "from [dbo].[tblACCOUNT]";
                Statement st= cn.createStatement();
                rs= st.executeQuery(querry);
                if(rs!= null){
                    while (rs.next()) {                        
                        int accid= rs.getInt(1);
                        String user= rs.getString(2);
                        String pass= rs.getString(3);
                        String phone= rs.getString(4);
                        String role= rs.getBoolean(5)?"Admin":"User";
                        String fullName= rs.getString(6);
                        String email= rs.getString(7);
                        boolean isActive= rs.getBoolean(8);

                        acc= new Account(accid, user, pass, phone, role, fullName, email, isActive);
                        if (role.equals("User")) {
                            list.add(acc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn!=null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    public List<Account> getAll(){
        List<Account> list= new ArrayList<>();
        Account acc= null;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String querry= "select [Account_ID],[UserName],[Password],[PhoneNumber],[Role],[FullName],[Email],[IsActive]"
                        + "from [dbo].[tblACCOUNT]";
                Statement st= cn.createStatement();
                rs= st.executeQuery(querry);
                if(rs!= null){
                    while (rs.next()) {                        
                        int accid= rs.getInt(1);
                        String user= rs.getString(2);
                        String pass= rs.getString(3);
                        String phone= rs.getString(4);
                        String role= rs.getBoolean(5)?"Admin":"User";
                        String fullName= rs.getString(6);
                        String email= rs.getString(7);
                        boolean isActive= rs.getBoolean(8);

                        acc= new Account(accid, user, pass, phone, role, fullName, email, isActive);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn!=null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    //Check Attribute is unique in database
    public Account checkUserName(String userName){
        Account acc;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String querry="select [Account_ID],[UserName],[Password],[PhoneNumber],[Role],[FullName],[Email],[IsActive]"
                                + "from [dbo].[tblACCOUNT]\n" +
                                    "where [UserName] like ?";
                ps=cn.prepareStatement(querry);
                ps.setString(1, userName);
                rs=ps.executeQuery();
                if(rs.next()){
                    int accid= rs.getInt(1);
                    String user= rs.getString(2);
                    String pass= rs.getString(3);
                    String phone= rs.getString(4);
                    String role= rs.getBoolean(5)?"Admin":"User";
                    String fullName= rs.getString(6);
                    String email= rs.getString(7);
                    boolean isActive= rs.getBoolean(8);
                    
                    acc= new Account(accid, user, pass, phone, role, fullName, email, isActive);
                    return acc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn!= null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public Account checkPhone(String phoneNum){
        Account acc;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String querry="select [Account_ID],[UserName],[Password],[PhoneNumber],[Role],[FullName],[Email],[IsActive]"
                                + "from [dbo].[tblACCOUNT]\n" +
                                    "where [PhoneNumber]= ?";
                ps=cn.prepareStatement(querry);
                ps.setString(1, phoneNum);
                rs=ps.executeQuery();
                if(rs.next()){
                    int accid= rs.getInt(1);
                    String user= rs.getString(2);
                    String pass= rs.getString(3);
                    String phone= rs.getString(4);
                    String role= rs.getBoolean(5)?"Admin":"User";
                    String fullName= rs.getString(6);
                    String email= rs.getString(7);
                    boolean isActive= rs.getBoolean(8);
                    
                    acc= new Account(accid, user, pass, phone, role, fullName, email, isActive);
                    return acc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn!= null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public Account checkEmail(String checkEmail){
        Account acc;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String querry="select [Account_ID],[UserName],[Password],[PhoneNumber],[Role],[FullName],[Email],[IsActive]"
                                + "from [dbo].[tblACCOUNT]\n" +
                                    "where [Email]= ?";
                ps=cn.prepareStatement(querry);
                ps.setString(1, checkEmail);
                rs=ps.executeQuery();
                if(rs.next()){
                    int accid= rs.getInt(1);
                    String user= rs.getString(2);
                    String pass= rs.getString(3);
                    String phone= rs.getString(4);
                    String role= rs.getBoolean(5)?"Admin":"User";
                    String fullName= rs.getString(6);
                    String email= rs.getString(7);
                    boolean isActive= rs.getBoolean(8);
                    
                    acc= new Account(accid, user, pass, phone, role, fullName, email, isActive);
                    return acc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn!= null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public Account getUserByEmailAndPhone(String phone,String email){
        Account acc;
        try {
            cn = DBUtill.makeConnection();
            if(cn != null){
                String sql="select [Account_ID],[UserName],[Password],[PhoneNumber],[Role],[FullName],[Email],[IsActive]\n"
                        + "from [dbo].[tblACCOUNT]where [Email] = ? and [PhoneNumber] = ?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, phone);
                rs=ps.executeQuery();
                if (rs.next()) {
                    int accid = rs.getInt(1);
                    String user = rs.getString(2);
                    String pass = rs.getString(3);
                    String ph = rs.getString(4);
                    String role = rs.getBoolean(5) ? "Admin" : "User";
                    String fullName = rs.getString(6);
                    String em = rs.getString(7);
                    boolean isActive = rs.getBoolean(8);

                    acc = new Account(accid, user, pass, ph, role, fullName, em, isActive);
                    return acc;
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
                e.printStackTrace();
            }
        }
        return null;
    }
    
    //insert Account to data base
    public int insertAccount(String userName, String password, String phoneNum, String fullName, String email){
        int result=0;
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String sql="insert into [dbo].[tblACCOUNT] (UserName,Password,PhoneNumber,FullName,Email)\n" +
                            "values (?,?,?,?,?)";
                ps= cn.prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.setString(3, phoneNum);
                ps.setString(4, fullName);
                ps.setString(5, email);
                
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
    public int blockAccount(int accid){
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if(cn != null){
                String sql="UPDATE [dbo].[tblACCOUNT] SET [IsActive] = 0\n"
                        + "WHERE [Account_ID] = ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, accid);
                result = ps.executeUpdate();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn != null){
                    cn.close();
                }    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public int unBlockAccount(int accid) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[tblACCOUNT] SET [IsActive] = 1\n"
                        + "WHERE [Account_ID] = ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, accid);
                result = ps.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public int upgradeAdmin(int accid) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[tblACCOUNT] SET [Role] = 1\n"
                        + "WHERE [Account_ID] = ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, accid);
                result = ps.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    

}
