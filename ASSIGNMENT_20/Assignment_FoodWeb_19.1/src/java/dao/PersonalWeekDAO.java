
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PersonalWeek;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtill;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class PersonalWeekDAO {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public PersonalWeek getWeekByWeekIndex(int personalID, int weekIndex) {
        PersonalWeek weekPlan = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String querry = "select [PW_ID],[PL_ID],[W_ID],[WeekIndex]\n"
                        + "from [dbo].[tblPERSONAL_WEEK]\n"
                        + "where [PL_ID]=? and [WeekIndex]=?";
                ps = cn.prepareStatement(querry);
                ps.setInt(1, personalID);
                ps.setInt(2, weekIndex);

                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    int plID = rs.getInt(2);
                    int wID = rs.getInt(3);
                    int wIndex = rs.getInt(4);
                    weekPlan = new PersonalWeek(id, plID, wID, wIndex);
                }
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

        return weekPlan;
    }

    public int insertWeekPlan(int personalID, int weekID, int weekIndex) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "insert into [dbo].[tblPERSONAL_WEEK] ([PL_ID],[W_ID],[WeekIndex])\n"
                        + "values (?,?,?)";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, personalID);
                ps.setInt(2, weekID);
                ps.setInt(3, weekIndex);
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

    public int deleteWeekPlan(int personalID) {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [dbo].[tblPERSONAL_WEEK] WHERE [PL_ID] = ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, personalID);
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

    public int deletePlanByWeekIndex(int planID, int weekIndex, int weekID) {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [dbo].[tblPERSONAL_WEEK] \n"
                        + "WHERE [PL_ID] = ? and [WeekIndex]= ? and [W_ID]= ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, planID);
                ps.setInt(2, weekIndex);
                ps.setInt(3, weekID);
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

    public static void main(String[] args) {
        PersonalWeekDAO w = new PersonalWeekDAO();

    }
}
