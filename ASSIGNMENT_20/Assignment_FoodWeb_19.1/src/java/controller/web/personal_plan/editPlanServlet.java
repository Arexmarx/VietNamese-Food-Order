/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.personal_plan;

import dao.PersonalFoodDAO;
import dao.PersonalPlanDAO;
import dao.PersonalWeekDAO;
import dao.WeeklyMenuDAO;
import dao.WeeklyMenuItemDAO;
import dto.PersonalFood;
import dto.PersonalPlan;
import dto.PersonalWeek;
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
 * @author Nguyễn Nhật Trường
 */
public class editPlanServlet extends HttpServlet {

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
            String planID = request.getParameter("planID");
            int parsedPlanID = Integer.parseInt(planID); // Parse once
            String check = request.getParameter("optionView");

            PersonalPlanDAO pDao = new PersonalPlanDAO();
            PersonalFoodDAO pfDao = new PersonalFoodDAO();
            PersonalWeekDAO pwDao = new PersonalWeekDAO();
            WeeklyMenuItemDAO wmnDao = new WeeklyMenuItemDAO();

            PersonalPlan planPresent = pDao.getPlanById(parsedPlanID);

            HashMap<Integer, List<PersonalFood>> listFoodInWeek = new HashMap<>();
            for (int i = 1; i <= planPresent.getWeeklyNumber(); i++) {
                List<PersonalFood> foods = pfDao.getFoodsByIndexOfWeek(parsedPlanID, i);
                if (!foods.isEmpty()) {
                    listFoodInWeek.put(i, foods);
                }
            }

            HashMap<Integer, List<WeeklyMenuItem>> listItemWeeklyMenu = new HashMap<>();
            for (int i = 1; i <= planPresent.getWeeklyNumber(); i++) {
                List<PersonalFood> foodsInWeek = listFoodInWeek.get(i);
                if (foodsInWeek == null || foodsInWeek.isEmpty()) {
                    PersonalWeek pw = pwDao.getWeekByWeekIndex(parsedPlanID, i);
                    if (pw != null) {
                        listItemWeeklyMenu.put(i, wmnDao.getMenuItemByID(pw.getWeekID()));
                    }
                }
            }
            List<Integer> flag= new ArrayList<>(listItemWeeklyMenu.keySet());
            out.print(listFoodInWeek);
            out.print("<br>");
            out.print(listItemWeeklyMenu);
            out.print("<br>");
            out.print(flag);
            
            request.setAttribute("PlanPresent", planPresent);
            request.setAttribute("ListFoodInWeek", listFoodInWeek);
            request.setAttribute("ListItemWeeklyMenu", listItemWeeklyMenu);
            request.setAttribute("FlagWeek", flag);
            if(check != null){
                if(check.equals("view")){
                    request.getRequestDispatcher("MainController?action=viewPlan").forward(request, response);
                }
            }else if (check== null) {
                request.getRequestDispatcher("MainController?action=showEditPlan").forward(request, response);
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
