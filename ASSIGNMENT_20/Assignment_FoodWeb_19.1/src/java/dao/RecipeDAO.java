/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Recipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtill;

/**
 *
 * @author Admin
 */
public class RecipeDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ArrayList<Recipe> getAllRecipe(int foodId) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [R_ID],[F_ID],[Ingredient_ID],[Quantity],[Measurement]\n"
                        + "from [dbo].[tblRECIPE] where [F_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, foodId);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int recipeId = rs.getInt(1);
                        int foodID = rs.getInt(2);
                        int ingredientId = rs.getInt(3);
                        float quantity = rs.getFloat(4);
                        String measurement = rs.getString(5);

                        Recipe re = new Recipe(recipeId, foodID, ingredientId, quantity, measurement);
                        list.add(re);
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

    public boolean addRecipe(int foodId, int ingredientId, float quantity, String measurement) {
        boolean isSuccess = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[tblRECIPE] ([F_ID], [Ingredient_ID], [Quantity], [Measurement])\n"
                        + "VALUES (?, ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, foodId);
                pst.setInt(2, ingredientId);
                pst.setFloat(3, quantity);
                pst.setString(4, measurement);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isSuccess = true;
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
        return isSuccess;
    }

    public boolean deleteRecipe(int foodId, int ingredientId) {
        boolean isSuccess = false;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [dbo].[tblRECIPE] WHERE [F_ID] = ? AND [Ingredient_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, foodId);
                pst.setInt(2, ingredientId);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isSuccess = true;
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
        return isSuccess;
    }

}
