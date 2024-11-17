/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.personal_plan;

import dao.FoodDAO;
import dao.WeeklyMenuDAO;
import dto.Food;
import dto.WeeklyMenu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class addToPlanServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            HashMap<Integer, Food> listFood = (HashMap<Integer, Food>) session.getAttribute("FoodListPlan");
            HashMap<Integer, WeeklyMenu> listWeeklyMenu = (HashMap<Integer, WeeklyMenu>) session.getAttribute("WeeklyListPlan");

            WeeklyMenuDAO wd = new WeeklyMenuDAO();
            FoodDAO fd = new FoodDAO();
            String foodID = request.getParameter("foodId");
            String weeklyID = request.getParameter("weeklyID");

            if (foodID != null) {
                if (listFood == null) {
                    listFood = new HashMap<>();
                    Food f = fd.getFood(Integer.parseInt(foodID.trim()));
                    listFood.put(f.getId(), f);
                    session.setAttribute("FoodListPlan", listFood);
                    request.getRequestDispatcher("MainController?action=foodDetail&foodID=" + foodID).forward(request, response);
                } else {
                    Food f = fd.getFood(Integer.parseInt(foodID.trim()));
                    listFood.put(f.getId(), f);
                    session.setAttribute("FoodListPlan", listFood);
                    request.getRequestDispatcher("MainController?action=foodDetail&foodID=" + foodID).forward(request, response);
                }
            }
            if (weeklyID != null) {
                if (listWeeklyMenu == null) {
                    listWeeklyMenu = new HashMap<>();
                    WeeklyMenu mn= wd.getMenuByID(Integer.parseInt(weeklyID.trim()));
                    listWeeklyMenu.put(mn.getId(), mn);
                    session.setAttribute("WeeklyListPlan", listWeeklyMenu);
                    request.getRequestDispatcher("MainController?action=weeklyMenu&menuID=" + weeklyID).forward(request, response);
                } else {
                    WeeklyMenu mn= wd.getMenuByID(Integer.parseInt(weeklyID.trim()));
                    listWeeklyMenu.put(mn.getId(), mn);
                    session.setAttribute("WeeklyListPlan", listWeeklyMenu);
                    request.getRequestDispatcher("MainController?action=weeklyMenu&menuID=" + weeklyID).forward(request, response);
                }
            }
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
