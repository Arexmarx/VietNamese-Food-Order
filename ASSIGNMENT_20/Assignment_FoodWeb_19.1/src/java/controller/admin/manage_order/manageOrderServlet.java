/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.manage_order;

import dao.AccountDAO;
import dao.OrderDAO;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ExtraUtils;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class manageOrderServlet extends HttpServlet {

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
            ExtraUtils tool= new ExtraUtils();
            String date = request.getParameter("date");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String status= request.getParameter("txtStatus");
            
            OrderDAO orDao = new OrderDAO();
            AccountDAO accDao = new AccountDAO();
            ArrayList<Order> fullList = orDao.getAllOrders();
            
            if ((date != null && !date.isEmpty()) || (phone != null && !phone.isEmpty()) || (email != null && !email.isEmpty())) {
                int accid = -1;
                if (email != null && !email.isEmpty()) {
                    accid = accDao.checkEmail(email).getAccid();
                }

                if (date != null && !date.isEmpty()) {
                    if (phone != null && !phone.isEmpty() && email != null && !email.isEmpty()) {
                        fullList = orDao.getAllOrdersByDatePhoneAccid(phone, date, accid);
                    } else if (phone != null && !phone.isEmpty()) {
                        fullList = orDao.getAllOrdersByDateAndPhone(phone, date);
                    } else if (email != null && !email.isEmpty()) {
                        fullList = orDao.getAllOrdersByDateAccid(date, accid);
                    } else {
                        fullList = orDao.getAllOrdersByDate(date);
                    }
                } else if (phone != null && !phone.isEmpty()) {
                    if (email != null && !email.isEmpty()) {
                        fullList = orDao.getAllOrdersByPhoneAccid(phone, accid);
                    } else {
                        fullList = orDao.getAllOrdersByPhone(phone);
                    }
                } else if (email != null && !email.isEmpty()) {
                    fullList = orDao.getAllOrdersByAccId(accid);
                }
            }
            if(status!= null && !status.isEmpty()){
                fullList= new ArrayList<>();
                fullList= orDao.getAllOrdersByStatus(status);
            }
            request.setAttribute("fullListOrder", fullList);
            request.setAttribute("txtDate", date);
            request.setAttribute("txtPhone", phone);
            request.setAttribute("txtEmail", email);
            request.getRequestDispatcher("MainControllerAdmin?action=viewManageOrder").forward(request, response);

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
