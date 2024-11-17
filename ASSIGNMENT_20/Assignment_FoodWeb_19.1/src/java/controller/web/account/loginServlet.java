/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.account;

import dao.AccAddressDAO;
import dao.AccountDAO;
import dto.AccAddress;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.PasswordUtils;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class loginServlet extends HttpServlet {

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
            String name= request.getParameter("txtUsername");
            String pass= request.getParameter("txtPassword");
            PasswordUtils tool= new PasswordUtils();
            
            AccountDAO accd= new AccountDAO();
            Account acc= null;
            List<Account> listAcc= accd.getAll();
            for(Account i: listAcc){
                if(name.equals(i.getUserName()) && tool.checkPass(pass, i.getPassword())){
                    acc= accd.checkUserName(name);
                    break;
                }
            }
            HttpSession session= request.getSession();
            
            if (acc== null) {
                request.setAttribute("msgError", "Sai Tên Đăng nhập hoặc Mật khẩu !!! ");
                request.getRequestDispatcher("MainController?action=login").forward(request, response);
            }
            else if (!acc.isIsActive()) {
                request.setAttribute("msgError", "Tài khoản của bạn đã bị khóa !!!");
                request.getRequestDispatcher("MainController?action=login").forward(request, response);
            }
            else{
                AccAddressDAO add= new AccAddressDAO();
                AccAddress address= add.getAccAddress(acc.getAccid());
                
                session.setAttribute("AddressObj", address);
                session.setAttribute("LoginObj", acc);
                if(acc.getRole().equalsIgnoreCase("admin")){
                    //trang admin
                    request.getRequestDispatcher("MainControllerAdmin?action=homePageAd").forward(request, response);
                }else{
                    request.getRequestDispatcher("MainController?action=firstTime").forward(request, response);
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
