/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.manage_user;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class manageUserServlet extends HttpServlet {

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
            AccountDAO accDao = new AccountDAO();
            ArrayList<Account> list = (ArrayList<Account>) accDao.getAllAccount();
            String check = request.getParameter("active");
            String accid = request.getParameter("id");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            if (check != null && accid != null) {
                int accountId = Integer.parseInt(accid);
                switch (check) {
                    case "block":
                        accDao.blockAccount(accountId);
                        
                        break;
                    case "unBlock":
                        accDao.unBlockAccount(accountId);
                        
                        break;
                    case "addAdmin":
                        accDao.upgradeAdmin(accountId);
                        break;
                }
            }

            Account acc = null;
            if ((phone != null &&!phone.isEmpty()) || (email != null&&!email.isEmpty())) {
                if (phone != null && !phone.isEmpty() && email != null && !email.isEmpty()) {
                    acc = accDao.getUserByEmailAndPhone(phone, email);
                } else if (phone != null && !phone.isEmpty()) {
                    acc = accDao.checkPhone(phone);
                } else if (email != null && !email.isEmpty()) {
                    acc = accDao.checkEmail(email);
                }
                if (acc!= null) {
                    request.setAttribute("msg", "");
                }else
                    request.setAttribute("msg", "Không tìm thấy");
            }
           
            request.setAttribute("account", acc);
            request.setAttribute("listUser", list);
            request.setAttribute("textPhone", phone);
            request.setAttribute("textEmail", email);

            if (check == null) {
                request.getRequestDispatcher("MainControllerAdmin?action=viewManageUser").forward(request, response);
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
