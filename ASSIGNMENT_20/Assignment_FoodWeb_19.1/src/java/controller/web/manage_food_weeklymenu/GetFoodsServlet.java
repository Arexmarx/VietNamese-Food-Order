/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.manage_food_weeklymenu;

import dao.FoodDAO;
import dto.Food;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
public class GetFoodsServlet extends HttpServlet {

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
            FoodDAO d = new FoodDAO();
            ArrayList<Food> list = new ArrayList<>();

            if (d != null) {
                list = d.getFoods("");
                if (list == null) {
                    list = new ArrayList<>();
                }
                String cateID = request.getParameter("cateID");

                if (cateID != null && !cateID.isEmpty()) {
                    ArrayList<Food> filteredList = d.getFoodsByCategory(cateID);
                    if (filteredList != null) {
                        list = filteredList;
                    }
                }

                request.setAttribute("ListFoods", list);
                if (request.getRequestDispatcher("MainController?action=userHome") != null) {
                    request.getRequestDispatcher("MainController?action=userHome").forward(request, response);
                } else {
                    out.println("RequestDispatcher is null");
                }
            } else {
                out.println("FoodDAO object is null");
            }
        } catch (Exception e) {
            e.printStackTrace(out);  // In lỗi ra console hoặc log file
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
