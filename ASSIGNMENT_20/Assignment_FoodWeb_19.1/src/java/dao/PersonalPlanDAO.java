/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PersonalPlan;
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
public class PersonalPlanDAO {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<PersonalPlan> getAllPlanByAccid(int id) {
        List<PersonalPlan> list = new ArrayList<>();
        PersonalPlan plan = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String querry = "select [PL_ID],[Account_ID],[WeeklyNumber],[Name]\n"
                        + "from [dbo].[tblPERSONAL_PLAN]\n"
                        + "where [Account_ID]=?\n"
                        + "order by [PL_ID] desc";
                ps = cn.prepareStatement(querry);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int pID = rs.getInt(1);
                        int accid = rs.getInt(2);
                        int weeklyNumber = rs.getInt(3);
                        String name = rs.getString(4);
                        plan = new PersonalPlan(pID, accid, weeklyNumber, name);
                        list.add(plan);
                    }
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

        return list;
    }

    public PersonalPlan getPlanById(int pid) {
        PersonalPlan plan = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String querry = "select [PL_ID],[Account_ID],[WeeklyNumber],[Name]\n"
                        + "from [dbo].[tblPERSONAL_PLAN]\n"
                        + "where [PL_ID] =?";
                ps = cn.prepareStatement(querry);
                ps.setInt(1, pid);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int pID = rs.getInt(1);
                    int accid = rs.getInt(2);
                    int weeklyNumber = rs.getInt(3);
                    String name = rs.getString(4);
                    plan = new PersonalPlan(pID, accid, weeklyNumber, name);
                    return plan;
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
        return plan;
    }

    public int insertNewPlan(int accid, int numWeek, String namePlan) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "insert into [dbo].[tblPERSONAL_PLAN] ([Account_ID],[WeeklyNumber],[Name]) \n"
                        + "values (?,?,?)";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, accid);
                ps.setInt(2, numWeek);
                ps.setString(3, namePlan);
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

    public int deletePlan(int planID) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [dbo].[tblPERSONAL_PLAN] WHERE [PL_ID]= ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, planID);
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
        PersonalPlanDAO d = new PersonalPlanDAO();
//        for (PersonalPlan i : d.getAllPlanByAccid(15)) {
//            System.out.println(i);
//        }
        System.out.println(d.getPlanById(3));
    }
}
