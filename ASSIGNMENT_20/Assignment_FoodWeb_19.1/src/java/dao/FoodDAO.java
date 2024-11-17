package dao;

import dto.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtill;

public class FoodDAO {

    private Connection cn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public ArrayList<Food> getFoods(String findName) {
        ArrayList<Food> list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [F_ID], [Name], [Price], [Origin], [ImgURL], [Category_ID], [Description], [WayCooking] FROM [dbo].[tblFOOD] WHERE [Name] LIKE ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + findName + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int foodId = rs.getInt("F_ID");
                    String foodName = rs.getString("Name");
                    int price = rs.getInt("Price");
                    String origin = rs.getString("Origin");
                    String imageURL = rs.getString("ImgURL");
                    int categoryId = rs.getInt("Category_ID");
                    String description = rs.getString("Description");
                    String wayCooking = rs.getString("WayCooking");
                    Food fd = new Food(foodId, foodName, price, origin, imageURL, categoryId, description, wayCooking);
                    list.add(fd);
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

    public Food getFood(int foodId) {
        Food gf = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [F_ID], [Name], [Price], [Origin], [ImgURL], [Category_ID], [Description], [WayCooking] FROM [dbo].[tblFOOD] WHERE [F_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, foodId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String foodName = rs.getString("Name");
                    int price = rs.getInt("Price");
                    String origin = rs.getString("Origin");
                    String imageURL = rs.getString("ImgURL");
                    int categoryID = rs.getInt("Category_ID");
                    String description = rs.getString("Description");
                    String wayCooking = rs.getString("WayCooking");
                    gf = new Food(foodId, foodName, price, origin, imageURL, categoryID, description, wayCooking);
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
        return gf;
    }

    public int insertFood(int categoryID, String name, int price, String origin, String imgURL, String description, String wayCooking) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[tblFOOD] ([Category_ID], [Name], [Price], [Origin], [ImgURL], [Description], [WayCooking]) VALUES (?, ?, ?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, categoryID);
                pst.setString(2, name);
                pst.setInt(3, price);
                pst.setString(4, origin);
                pst.setString(5, imgURL);
                pst.setString(6, description);
                pst.setString(7, wayCooking);
                result = pst.executeUpdate();
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

    public int updateFood(String foodId, String name, String price, String origin) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[tblFOOD]\n"
                        + "SET [Price] = ?,[Name]=? ,[Origin] = ?\n"
                        + "WHERE [F_ID] = ?;";
                pst = cn.prepareStatement(sql);
                pst.setString(1, price);
                pst.setString(2, name);
                pst.setString(3, origin);
                pst.setString(4, foodId);
                result = pst.executeUpdate();
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

    public ArrayList<Food> getFoodsByCategory(String categoryId) {
        ArrayList<Food> list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [F_ID], [Name], [Price], [Origin], [ImgURL], [Category_ID], [Description], [WayCooking] FROM [dbo].[tblFOOD] WHERE [Category_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, categoryId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int foodId = rs.getInt("F_ID");
                    String foodName = rs.getString("Name");
                    int price = rs.getInt("Price");
                    String origin = rs.getString("Origin");
                    String imageURL = rs.getString("ImgURL");
                    int cateId= rs.getInt("Category_ID");
                    String description = rs.getString("Description");
                    String wayCooking = rs.getString("WayCooking");
                    Food fd = new Food(foodId, foodName, price, origin, imageURL, cateId, description, wayCooking);
                    list.add(fd);
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

    public static void main(String[] args) {
        FoodDAO d = new FoodDAO();
        System.out.println(d.getFood(35));
    }
}
