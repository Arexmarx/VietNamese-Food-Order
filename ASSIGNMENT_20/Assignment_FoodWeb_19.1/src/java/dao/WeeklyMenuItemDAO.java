/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.WeeklyMenu;
import dto.WeeklyMenuItem;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utils.DBUtill;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class WeeklyMenuItemDAO {
    private Connection cn= null;
    private PreparedStatement pst= null;
    private ResultSet rs= null;
    private WeeklyMenuDAO wmd= null;
    
    public List<WeeklyMenuItem> getMenuItemByID(int wID){
        List<WeeklyMenuItem> list= new ArrayList<>();
        wmd= new WeeklyMenuDAO();
        try {
            cn= DBUtill.makeConnection();
            if(cn!= null){
                String sql = "select [WI_ID],[W_ID],[F_ID]\n"
                        + "from [dbo].[tblWEEK_ITEM]\n"
                        + "where [W_ID]=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, wID);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        int wid = rs.getInt(2);
                        int fid = rs.getInt(3);
                        WeeklyMenuItem item = new WeeklyMenuItem(id, wid, fid);
                        list.add(item);
                    }
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
        return list;
    }

    public boolean insertFoodToWeeklyMenu(int wID, int fID) {
        boolean isInserted = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[tblWEEK_ITEM] ([W_ID], [F_ID]) VALUES (?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, wID);
                pst.setInt(2, fID);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isInserted = true; // Chèn thành công
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isInserted; // Trả về kết quả chèn
    }
    public boolean deleteFoodFromWeeklyMenu(int wID, int fID) {
        boolean isDeleted = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [dbo].[tblWEEK_ITEM] WHERE [W_ID] = ? AND [F_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, wID);
                pst.setInt(2, fID);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isDeleted = true; // Xóa thành công
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isDeleted; // Trả về kết quả xóa
    }
}
