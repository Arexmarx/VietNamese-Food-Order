/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Ingredient;
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
public class IngredientDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Ingredient getIngredient(int ingredientId) {
        Ingredient ingre = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [Ingredient_ID],[Name],[IsAvailable],[Measurement]\n"
                        + "from [dbo].[tblINGREDIENT] where [Ingredient_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, ingredientId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int ingreId = rs.getInt(1);
                    String name = rs.getString(2);
                    Boolean isAvail = rs.getBoolean(3);
                    String measurement = rs.getString(4);

                    ingre = new Ingredient(ingreId, name, isAvail, measurement);
                    return ingre;

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

        return null;
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [Ingredient_ID], [Name], [IsAvailable], [Measurement] "
                        + "from [dbo].[tblINGREDIENT]";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int ingreId = rs.getInt(1);
                    String name = rs.getString(2);
                    Boolean isAvail = rs.getBoolean(3);
                    String measurement = rs.getString(4);

                    Ingredient ingre = new Ingredient(ingreId, name, isAvail, measurement);
                    ingredients.add(ingre);
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
        return ingredients;
    }

    public List<Ingredient> searchIngredientsByName(String keyword) {
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [Ingredient_ID], [Name], [IsAvailable], [Measurement] "
                        + "FROM [dbo].[tblINGREDIENT] "
                        + "WHERE [Name] LIKE ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int ingreId = rs.getInt(1);
                    String name = rs.getString(2);
                    Boolean isAvail = rs.getBoolean(3);
                    String measurement = rs.getString(4);

                    Ingredient ingre = new Ingredient(ingreId, name, isAvail, measurement);
                    ingredients.add(ingre);
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
        return ingredients;
    }

    public boolean addIngredient(String name, String measurement) {
        boolean result = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[tblINGREDIENT] ([Name], [Measurement]) "
                        + "VALUES (?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, measurement);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
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
        return result;
    }

    public boolean updateIngredientAvailability(int ingredientId, int isAvailable) {
        boolean result = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[tblINGREDIENT] "
                        + "SET [IsAvailable] = ? "
                        + "WHERE [Ingredient_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, isAvailable);
                pst.setInt(2, ingredientId);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
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
        return result;
    }

    public static void main(String[] args) {
        IngredientDAO d = new IngredientDAO();
        System.out.println(d.searchIngredientsByName(""));
    }
}
