/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.manage_food_weeklymenu;

import dao.WeeklyMenuDAO;
import dao.WeeklyMenuItemDAO;
import dto.WeeklyMenu;
import dto.WeeklyMenuItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class showWeeklyMenuServlet extends HttpServlet {

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
            String menuID= request.getParameter("menuID");
            String idMenu= (String) request.getAttribute("menuID");
            
            WeeklyMenuDAO menu= new WeeklyMenuDAO();
            WeeklyMenuItemDAO menuItem= new WeeklyMenuItemDAO();
            
            WeeklyMenu mn= menu.getMenuToday();
            
            List<WeeklyMenuItem> listItem= new ArrayList<>();
            List<WeeklyMenu> listMenu= new ArrayList<>();
            
            if(mn!= null){
                listItem= menuItem.getMenuItemByID(mn.getId());
                List<WeeklyMenu> allMenu= menu.getAllWeeklyMenu();
                int count=0;
                listMenu.add(mn);
                for (int i = 0; i < allMenu.size() && count!=4; i++) {
                    if(mn.getEndDate().before(allMenu.get(i).getStartDate())){
                        listMenu.add(allMenu.get(i));
                        count++;
                    }
                }
                if(listItem.isEmpty()){
                    request.setAttribute("NotifyEmpty", "Thực đơn đang được chế biến!!!");
                }
            }
            if(menuID!= null){
                mn= menu.getMenuByID(Integer.parseInt(menuID.trim()));
                listItem= menuItem.getMenuItemByID(mn.getId());
                if(listItem.isEmpty()){
                    request.setAttribute("NotifyEmpty", "Thực đơn đang được chế biến!!!");
                }
            }
            
            if(idMenu!= null){
                mn= menu.getMenuByID(Integer.parseInt(idMenu.trim()));
                listItem= menuItem.getMenuItemByID(mn.getId());
                if(listItem.isEmpty()){
                    request.setAttribute("NotifyEmpty", "Thực đơn đang được chế biến!!!");
                }
            }
            request.setAttribute("MenuPresent", mn);
            request.setAttribute("MenuItems", listItem);
            request.setAttribute("NextMenu", listMenu);

            request.getRequestDispatcher("view/user/showWeeklyMenu.jsp").forward(request, response);
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
