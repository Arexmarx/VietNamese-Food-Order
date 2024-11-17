/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.WeeklyMenu;
import java.sql.Connection;
import java.sql.Date;
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
public class WeeklyMenuDAO {

    private Connection cn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public List<WeeklyMenu> getAllWeeklyMenu() {
        List<WeeklyMenu> list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [W_ID],[Name],[StartDate],[EndDate]\n"
                        + "from [dbo].[tblWEEK_MENU]";
                Statement st = cn.createStatement();
                rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String name = rs.getString(2);
                        Date startDate = rs.getDate(3);
                        Date endDate = rs.getDate(4);

                        WeeklyMenu w = new WeeklyMenu(id, name,
                                new java.util.Date(startDate.getTime()), new java.util.Date(endDate.getTime()));
                        list.add(w);
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

    /**
     *
     * @return
     */
    public WeeklyMenu getMenuToday() {
        WeeklyMenu menu = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [W_ID],[Name],[StartDate],[EndDate]\n"
                        + "FROM [dbo].[tblWEEK_MENU] \n"
                        + "WHERE CAST(StartDate AS DATE) <= CAST(GETDATE() AS DATE)AND CAST(EndDate AS DATE) >= CAST(GETDATE() AS DATE)";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    Date startDate = rs.getDate(3);
                    Date endDate = rs.getDate(4);

                    menu = new WeeklyMenu(id, name,
                            new java.util.Date(startDate.getTime()), new java.util.Date(endDate.getTime()));
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
        return menu;
    }

    public WeeklyMenu getMenuByID(int id) {
        WeeklyMenu menu = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [W_ID],[Name],[StartDate],[EndDate]\n"
                        + "from [dbo].[tblWEEK_MENU]\n"
                        + "where [W_ID]=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int wid = rs.getInt(1);
                    String name = rs.getString(2);
                    Date startDate = rs.getDate(3);
                    Date endDate = rs.getDate(4);

                    menu = new WeeklyMenu(wid, name,
                            new java.util.Date(startDate.getTime()), new java.util.Date(endDate.getTime()));
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
        return menu;
    }

    public ArrayList<WeeklyMenu> getMenuByName(String name) {
        WeeklyMenu menu = null;
        ArrayList<WeeklyMenu> list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [W_ID],[Name],[StartDate],[EndDate]\n"
                        + "from [dbo].[tblWEEK_MENU]where [Name] like ? "
                        + "ORDER BY [StartDate]";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int wid = rs.getInt(1);
                        String wName = rs.getString(2);
                        Date startDate = rs.getDate(3);
                        Date endDate = rs.getDate(4);

                        menu = new WeeklyMenu(wid, wName,
                                new java.util.Date(startDate.getTime()), new java.util.Date(endDate.getTime()));
                        list.add(menu);
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

    public boolean insertWeeklyMenu(String name, String startDate, String endDate) {
        boolean isInserted = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[tblWEEK_MENU] ([Name], [StartDate], [EndDate]) VALUES (?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, startDate);
                pst.setString(3, endDate);
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

    public boolean updateWeeklyMenu(int Wid, String weekName, String startDate, String endDate) {
        boolean isUpdated = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[tblWEEK_MENU]\n"
                        + "set [Name] = ? , [StartDate] = ? , [EndDate] = ?\n"
                        + "where [W_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(4, Wid);
                pst.setString(1, weekName);
                pst.setString(2, startDate);
                pst.setString(3, endDate);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isUpdated = true; // Chèn thành công
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
        return isUpdated; // Trả về kết quả chèn
    }

    public boolean updateWeeklyMenuName(int Wid, String weekName) {
        boolean isUpdated = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[tblWEEK_MENU]\n"
                        + "set [Name] = ?\n"
                        + "where [W_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(2, Wid);
                pst.setString(1, weekName);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isUpdated = true; // Chèn thành công
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
        return isUpdated; // Trả về kết quả chèn
    }

}
