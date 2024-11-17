/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.personal_plan;

import dao.FoodDAO;
import dao.PersonalFoodDAO;
import dao.PersonalWeekDAO;
import dao.WeeklyMenuDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class actionEditPlanServlet extends HttpServlet {

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
            String actionEdit = request.getParameter("ActionEdit");
            String planID = request.getParameter("planID");
            
            
            PersonalFoodDAO pfDao = new PersonalFoodDAO();
            PersonalWeekDAO pwDao = new PersonalWeekDAO();
            FoodDAO foodDao= new FoodDAO();
            WeeklyMenuDAO weekDao= new WeeklyMenuDAO();
            
            if (actionEdit.equalsIgnoreCase("deleteFood")) {
                String foodID = request.getParameter("PersonalFoodID");
                int idFood= Integer.parseInt(foodID);
                if(foodDao.getFood(idFood)!= null)
                    pfDao.deletePerFoodPlan(Integer.parseInt(foodID));
                
            }
            if(actionEdit.equalsIgnoreCase("addFood")){
                int foodID= Integer.parseInt(request.getParameter("PersonalFoodID"));
                int weekDay= Integer.parseInt(request.getParameter("weekDay"));
                int weekIndex= Integer.parseInt(request.getParameter("weekIndex"));
                int rs=pfDao.insertFoodPlan(Integer.parseInt(planID), foodID, weekDay, weekIndex);
                if (rs==0) {
                    request.setAttribute("msg", "ID món ăn có vẻ không đúng!!!");
                }
            }
            if(actionEdit.equalsIgnoreCase("addWeeklyMenu")){
                int weekID= Integer.parseInt(request.getParameter("WeeklyMenuID"));
                int weekIndex= Integer.parseInt(request.getParameter("weekIndex"));
                int rs=pwDao.insertWeekPlan(Integer.parseInt(planID), weekID, weekIndex);
                if (rs==0) {
                    request.setAttribute("msg", "ID tuần có vẻ không đúng!!!");
                }
            }
            if(actionEdit.equalsIgnoreCase("deleteWeeklyMenu")){
                int weekID= Integer.parseInt(request.getParameter("WeeklyMenuID"));
                int weekIndex= Integer.parseInt(request.getParameter("weekIndex"));
                pwDao.deletePlanByWeekIndex(Integer.parseInt(planID), weekIndex, weekID);
            }
            request.getRequestDispatcher("MainController?action=editPlan&planID="+planID).forward(request, response);
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
