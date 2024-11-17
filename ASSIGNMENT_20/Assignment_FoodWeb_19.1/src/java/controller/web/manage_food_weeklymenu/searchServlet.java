/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.manage_food_weeklymenu;

import dao.FoodDAO;
import dao.WeeklyMenuDAO;
import dao.WeeklyMenuItemDAO;
import dto.Food;
import dto.WeeklyMenu;
import dto.WeeklyMenuItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class searchServlet extends HttpServlet {

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
            String searchName = request.getParameter("dataSearch");
            if(searchName.equals("1")){
                FoodDAO d = new FoodDAO();
                String findName = "";
                findName = request.getParameter("txtSearch");
                if(findName == null ||  findName.isEmpty()){
                    findName ="";
                } 
                ArrayList<Food> list = d.getFoods(findName);
                request.setAttribute("SearchFood", list);
                request.setAttribute("searchQuery", findName);
                request.getRequestDispatcher("MainController?action=viewSearch").forward(request, response);
            }else if(searchName.equals("2")){
                WeeklyMenuDAO weeklyDAO = new WeeklyMenuDAO();
                WeeklyMenuItemDAO weeklyItemDAO = new WeeklyMenuItemDAO();
                String weeklyName = "";
                weeklyName = request.getParameter("txtSearch");
                
                List<WeeklyMenu> weeklyList = weeklyDAO.getMenuByName(weeklyName);
                HashMap<WeeklyMenu,List<WeeklyMenuItem>> weeklyItemList = new HashMap<>();
                
                if(weeklyList != null && !weeklyList.isEmpty()){
                    for(WeeklyMenu i:weeklyList){
                        weeklyItemList.putIfAbsent(i,weeklyItemDAO.getMenuItemByID(i.getId()));
                        
                    }
                }
                request.setAttribute("WeeklyList", weeklyList);
                request.setAttribute("searchQuery", weeklyName);
                request.setAttribute("WeeklyItemList", weeklyItemList);
                request.getRequestDispatcher("view/user/viewSearch.jsp").forward(request, response);
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
