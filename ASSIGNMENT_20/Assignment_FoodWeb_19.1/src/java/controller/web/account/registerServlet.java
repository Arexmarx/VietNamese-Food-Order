/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.account;

import dao.AccAddressDAO;
import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ExtraUtils;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class registerServlet extends HttpServlet {

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
            
            String userName= request.getParameter("txtUsername");
            String phone= request.getParameter("txtPhone");
            String fullName=tool.encodeUTF8(request.getParameter("txtFullname"));
            String email= request.getParameter("txtEmail");
            String password= (String) request.getAttribute("encodePass");
            String repassword= request.getParameter("txtRepassword");
            String province=tool.encodeUTF8(request.getParameter("province"));
            String district=tool.encodeUTF8(request.getParameter("district"));
            String ward=tool.encodeUTF8(request.getParameter("ward"));
            String notes=tool.encodeUTF8(request.getParameter("txtNote"));
            
            AccountDAO d= new AccountDAO();
            AccAddressDAO ad= new AccAddressDAO();
            
            d.insertAccount(userName, password, phone, fullName, email);
            Account acc= d.getAccount(userName, password);
            ad.insertAccount(acc.getAccid(), province, district, ward, notes);
            request.getRequestDispatcher("MainController?action=firstTime").forward(request, response);
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
