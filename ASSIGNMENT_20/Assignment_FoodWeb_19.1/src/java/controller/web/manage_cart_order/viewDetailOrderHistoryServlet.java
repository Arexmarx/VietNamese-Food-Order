/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.manage_cart_order;

import dao.FoodDAO;
import dao.OrderAddressDAO;
import dao.OrderDAO;
import dto.Food;
import dto.Order;
import dto.OrderAddress;
import dto.OrderItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class viewDetailOrderHistoryServlet extends HttpServlet {

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
            String orderId = request.getParameter("ordedId");
            OrderDAO  ordDAO = new OrderDAO();
            ArrayList<OrderItem> list = new ArrayList<>();
            list =  ordDAO.getItemfromOrder(Integer.parseInt(orderId));
            FoodDAO fd = new FoodDAO();
            OrderAddressDAO orAddDAO = new OrderAddressDAO();
            ArrayList<Food> listFoodDetail = new ArrayList<>();
            for(OrderItem i:list){
                listFoodDetail.add(fd.getFood(i.getFoodId()));
            }
            OrderAddress orAdd = orAddDAO.getOrderAddress(Integer.parseInt(orderId));            
            Order ord = ordDAO.getOrder(Integer.parseInt(orderId));
            request.setAttribute("listDetailOrderHistory", list);
            request.setAttribute("listFoodDetailOrder", listFoodDetail);
            request.setAttribute("OrderAddress", orAdd);
            request.setAttribute("OrderObj", ord);
            request.getRequestDispatcher("MainController?action=viewDetailOrder").forward(request, response);
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
