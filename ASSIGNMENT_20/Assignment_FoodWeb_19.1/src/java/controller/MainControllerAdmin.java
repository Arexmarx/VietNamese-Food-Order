/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class MainControllerAdmin extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String act= request.getParameter("action");
            String url="";
            switch(act){
                case "homePageAd":
                    url="homePageAdmin.jsp";
                    break;
                //-------------------------
                case "manageMenu":
                    url="manageMenuServlet";
                    break;
                case "prepareCreate":
                    url="prepareCreateFoodServlet";
                    break;
                case "createFood":
                    url="createFoodServlet";
                    break;
                case "formCreateFood":
                    url="createFoodForm.jsp";
                    break;
                case "addRecipe":
                    url="manageIngredientServlet";
                    break;
                case "addRecipePage":
                    url="createRecipePage.jsp";
                    break;
                case "previewFoodPage":
                    url="previewFoodPage.jsp";
                    break;
                case "saveFood":
                    url="saveFoodServlet";
                    break;
                case "updateRecipe":
                    url="updateRecipeServlet";
                    break;
                case "viewManageMenu":
                    url="manageMenu.jsp";
                    break;
                //------------------
                case "viewManageOrder":
                    url="manageOrder.jsp";
                    break;
                case "manageOrder":
                    url="manageOrderServlet";
                    break;
                case "bacthOrder":
                    url="batchOrderServlet";
                    break;
                case "viewbacthOrder":
                    url="viewBatchOrder.jsp";
                    break;
                //---------------------
                case "viewManageUser":
                    url="manageUser.jsp";
                    break;
                case "manageUser":
                    url="manageUserServlet";
                    break;
                //----------------------
                case "manageWeeklyMenu":
                    url="manageWeeklyMenuServlet";
                    break;
                case "viewManageWeeklyMenu":
                    url="manageWeeklyMenuPage.jsp";
                    break;
                case "viewWeeklyMenuDetail":
                    url="viewDetailWeeklyMenu.jsp";
                    break;    
                case "getWeeklyMenuDetail":
                    url = "getDetailWeeklyMenuServlet";
                    break;
                case "insertWeeklyMenu":
                    url = "insertWeeklyMenuServlet";
                    break; 
                case "formCreatWeeklyMenu":
                    url="createWeeklyMenuForm.jsp";
                    break;
                case "insertFoodToWeeklyMenu":
                    url="insertFoodToWeeklyMenuServlet";
                    break;
                case "formInsertFoodToWeeklyMenu":
                    url="InsertFoodToWeeklyMenuForm.jsp";
                    break;
                case "searchFood":
                    url="foodInWeeklyMenuServlet";
                    break;
                case "updateWeeklyMenu":
                    url="updateWeeklyMenuServlet";
                    break;
                case "manageFoodWeeklyMenu":
                    url="manageFoodWeeklyMenuServlet";
                    break;
                case "viewManageFoodWeeklyMenu":
                    url="viewManageFoodWeeklyMenu.jsp";
                    break; 
                //----------------------------    
                case "viewIngredient":
                    url="viewIngredient.jsp";
                    break;
                case "manageIngredient":
                    url="manageIngredientAllServlet";
                    break;    
            }
           
            String pathUser="/view/admin/";
            if (url.matches("^.+\\.jsp$")) {
                url=pathUser+url;
            }
            request.getRequestDispatcher(url).forward(request, response);
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
