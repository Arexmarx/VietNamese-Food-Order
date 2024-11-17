/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.manage_cart_order;

import dao.OrderAddressDAO;
import dao.OrderDAO;
import dto.Account;
import dto.Food;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class saveOrderServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            ExtraUtils tool= new ExtraUtils();
            
            Account acc = (Account) session.getAttribute("LoginObj");
            String payment = request.getParameter("PaymentMethod");
            String phone = request.getParameter("txtPhone");
            String fullName=tool.encodeUTF8(request.getParameter("txtFullname"));
            String province=tool.encodeUTF8(request.getParameter("province"));
            String district=tool.encodeUTF8(request.getParameter("district"));
            String ward=tool.encodeUTF8(request.getParameter("ward"));
            String notes=tool.encodeUTF8(request.getParameter("txtNote"));
            
            if(acc != null){
                OrderDAO d = new OrderDAO();
                OrderAddressDAO ad = new OrderAddressDAO();
                HashMap<Food,Integer> cart = (HashMap<Food,Integer>) session.getAttribute("cart");
                if(cart != null){
                    d.saveOrder(acc, payment, fullName, phone, cart);
                    ad.saveOrderAddress(province, district, ward, notes);
                    session.removeAttribute("cart");
                    request.setAttribute("msgThanks", "Cám ơn Bạn đã mua hàng");
                    request.getRequestDispatcher("MainController?action=firstTime").forward(request, response);
                }
            }else{
                    request.getRequestDispatcher("MainController?action=login").forward(request, response);
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
