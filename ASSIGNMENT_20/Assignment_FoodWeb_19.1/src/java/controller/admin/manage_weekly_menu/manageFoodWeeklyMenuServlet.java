/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.manage_weekly_menu;

import dao.FoodDAO;
import dao.WeeklyMenuDAO;
import dao.WeeklyMenuItemDAO;
import dto.Food;
import dto.WeeklyMenu;
import dto.WeeklyMenuItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.ExtraUtils;

/**
 *
 * @author Admin
 */
public class manageFoodWeeklyMenuServlet extends HttpServlet {

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
            String option = request.getParameter("option");
            String url = "";
            if (option == null || option.isEmpty()) {
                option = "formUpdateFood";
            }
            switch (option) {
                case "formUpdateFood":
                    listOfFood(request, response);
                    listAllFood(request, response);
                    url = "viewManageFoodWeeklyMenu.jsp";
                    break;
                case "deleteFood":
                    deleteFood(request, response);
                    listOfFood(request, response);
                    listAllFood(request, response);
                    url = "viewManageFoodWeeklyMenu.jsp";
                    break;
                case "addFood":
                    addFood(request, response);
                    listOfFood(request, response);
                    listAllFood(request, response);
                    url = "viewManageFoodWeeklyMenu.jsp";
                    break;
                case "done":
                    HttpSession session = request.getSession();
                    session.removeAttribute("weeklyMenuId");
                    session.removeAttribute("weeklyObj");
                    url="manageWeeklyMenuServlet";
                    break;

            }
            String pathUser = "/view/admin/";
            if (url.matches("^.+\\.jsp$")) {
                url = pathUser + url;
            }
            request.getRequestDispatcher(url).forward(request, response);

        }
    }

    private void listOfFood(HttpServletRequest request, HttpServletResponse response) {
        String weeklyId = request.getParameter("weekId");
        HttpSession session = request.getSession();
        if (weeklyId == null || weeklyId.isEmpty()) {
            weeklyId = (String) session.getAttribute("weeklyMenuId");
        }
        WeeklyMenuItemDAO weekItemD = new WeeklyMenuItemDAO();
        List<WeeklyMenuItem> list = weekItemD.getMenuItemByID(Integer.parseInt(weeklyId));
        request.setAttribute("itemOfWeeklyMenu", list);
        session.setAttribute("weeklyMenuId", weeklyId);
        WeeklyMenuDAO weekDao = new WeeklyMenuDAO();
        WeeklyMenu weekly = weekDao.getMenuByID(Integer.parseInt(weeklyId));
        session.setAttribute("weeklyObj", weekly);
    }
    
    private void listAllFood(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ExtraUtils tool = new ExtraUtils();
        String foodName = request.getParameter("textSearchFood");

        if (foodName == null || foodName.isEmpty()) {
            foodName = "";
        } else {
            foodName = tool.encodeUTF8(request.getParameter("textSearchFood"));
        }

        FoodDAO foodDao = new FoodDAO();
        List<Food> list = foodDao.getFoods(foodName);

        request.setAttribute("searchQuerry", foodName);
        request.setAttribute("listOfAllFood", list);
    }

    private void deleteFood(HttpServletRequest request, HttpServletResponse response) {
        WeeklyMenuItemDAO weekItemD = new WeeklyMenuItemDAO();
        HttpSession session = request.getSession();
        String weekId = (String) session.getAttribute("weeklyMenuId");
        String foodId = request.getParameter("foodId");
        weekItemD.deleteFoodFromWeeklyMenu(Integer.parseInt(weekId), Integer.parseInt(foodId));

    }

    private void addFood(HttpServletRequest request, HttpServletResponse response) {
        WeeklyMenuItemDAO weekItemD = new WeeklyMenuItemDAO();
        HttpSession session = request.getSession();
        String weekId = (String) session.getAttribute("weeklyMenuId");
        String foodId = request.getParameter("foodId");
        for(WeeklyMenuItem i: weekItemD.getMenuItemByID(Integer.parseInt(weekId))){
            if(i.getFoodID() == Integer.parseInt(foodId)){
                request.setAttribute("msg", "Đã có món này trong thực đơn");
                return ;
            }
        }
        weekItemD.insertFoodToWeeklyMenu(Integer.parseInt(weekId), Integer.parseInt(foodId));
        
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
