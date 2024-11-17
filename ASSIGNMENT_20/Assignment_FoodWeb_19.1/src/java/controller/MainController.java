/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class MainController extends HttpServlet {

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
            String act= request.getParameter("action");
            String total = request.getParameter("total");
            request.setAttribute("totalPrice", total);
            
            String url="";
            if (act== null) {
                act= "firstTime";
            }
            switch(act){
                case"firstTime":
                    url="GetFoodsServlet";
                    break;
                case"userHome":
                    url="homePage.jsp";
                    break;
                case"login":
                    url="loginForm.jsp";
                    break;
                case"checkLogin":
                    url="loginServlet";
                    break;
                case"register":
                    url="registerForm.jsp";
                    break;
                case"checkRegister":
                    url="registerServlet";
                    break;
                case "logout":
                    url="logoutServlet";
                    break;
                case "showInfor":
                    url="showInforPage.jsp" ;
                    break;
                case"viewCart":
                    url="viewCart.jsp";
                    break;
                case"checkOut":
                    url="checkOut.jsp";
                    break;
                case"foodDetail":
                    url="foodDetailsServlet";
                    break;
                case"foodDetailPage":
                    url="foodDetails.jsp";
                    break;
                case"saveOrder":
                    url="saveOrderServlet";
                    break;
                case"historyOrder":
                    url="viewHistoryOrder.jsp";
                    break;
                case"viewHistoryOrder":
                    url="historyOrderServlet";
                    break;
                case"viewOrderHistory":
                    url="viewDetailOrderHistoryServlet";
                    break;
                case"viewDetailOrder":
                    url="viewOrderDetail.jsp";
                    break;
                case"weeklyMenu":
                    url="showWeeklyMenuServlet";
                    break;
                case"showWeeklyMenu":
                    url="showWeeklyMenu.jsp";
                    break;
                case"searchName":
                    url="searchServlet";
                    break;
                case"viewSearch":
                    url="viewSearch.jsp";
                    break;
                case"addToPlan":
                    url="addToPlanServlet";
                    break;
                case"myPlan":
                    url="showPersonalPlanServlet";
                    break;
                case"showMyPlan":
                    url="showPlan.jsp";
                    break;
                case"createPlan":
                    url="createPlanServlet";
                    break;
                case"deletePlan":
                    url="deletePlanServlet";
                    break;
                case"editPlan":
                    url="editPlanServlet";
                    break;
                case"showEditPlan":
                    url="editPlanPage.jsp";
                    break;
                case"optionsEdit":
                    url="actionEditPlanServlet";
                    break;
                case"viewPlan":
                    url="viewSpecificPlan.jsp";
                    break;
            }
            String pathUser="/view/user/";

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
