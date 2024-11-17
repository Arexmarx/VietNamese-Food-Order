/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.manage_menu;

import dao.FoodDAO;
import dao.RecipeDAO;
import dto.Food;
import dto.Recipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class saveFoodServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String decision = request.getParameter("decision");
            HttpSession session = request.getSession();
            FoodDAO fDao = new FoodDAO();
            RecipeDAO rDao = new RecipeDAO();

            if (decision.equalsIgnoreCase("cancle")) {
                session.removeAttribute("FoodCreated");
                session.removeAttribute("foodRecipe");
                request.setAttribute("msgSuccsess", "Đã hủy");
            } else {
                Food food = (Food) session.getAttribute("FoodCreated");
                int check = fDao.insertFood(food.getCatagoryId(), food.getName(), food.getPrice(), food.getOrigin(),
                        food.getImgURL(), food.getDescription(), food.getWayCooking());
                if (check > 0) {
                    food = fDao.getFoods(food.getName()).get(0);
                    HashMap<Integer, Recipe> recipe = (HashMap<Integer, Recipe>) session.getAttribute("foodRecipe");
                    for (Map.Entry<Integer, Recipe> entry : recipe.entrySet()) {
                        Recipe r = entry.getValue();
                        rDao.addRecipe(food.getId(), r.getIngredientId(), r.getQuantity(), r.getMeasurement());
                    }
                }
                session.removeAttribute("FoodCreated");
                session.removeAttribute("foodRecipe");
                request.setAttribute("msgSuccsess", "Thêm món ăn thành công");
            }
            request.getRequestDispatcher("MainControllerAdmin?action=manageMenu").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
