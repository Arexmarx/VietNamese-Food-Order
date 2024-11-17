/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.manage_ingredient;

import dao.IngredientDAO;
import dto.Ingredient;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.ExtraUtils;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class manageIngredientAllServlet extends HttpServlet {

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
            String option = request.getParameter("option");
            String url = "";
            if (option == null || option.isEmpty()) {
                option = "getIngredient";
            }
            switch (option) {
                case "getIngredient":
                    listOfIngre(request, response);
                    url = "viewIngredient.jsp";
                    break;
                case "addIngre":
                    addIngre(request, response);
                    listOfIngre(request, response);
                    url = "viewIngredient.jsp";
                    break;
                case "setAvailable":
                    setAvailable(request, response);
                    return ;

            }
            String pathUser = "/view/admin/";
            if (url.matches("^.+\\.jsp$")) {
                url = pathUser + url;
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void listOfIngre(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ExtraUtils tool = new ExtraUtils();
        String findName = request.getParameter("txtSearchIngre");
        if (findName == null || findName.isEmpty()) {
            findName = "";
        } else {
            findName = tool.encodeUTF8(findName).trim();
        }
        IngredientDAO ingreDao = new IngredientDAO();
        List<Ingredient> listofIngre = ingreDao.searchIngredientsByName(findName);
        request.setAttribute("listIngre", listofIngre);
        request.setAttribute("searchName", findName);
    }

    private void addIngre(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        IngredientDAO ingreDao = new IngredientDAO();
        ExtraUtils tool = new ExtraUtils();
        String ingreName = request.getParameter("nameIngre");
        String measurement = request.getParameter("measurement");
        List<Ingredient> listofIngre = ingreDao.searchIngredientsByName("");
        if (ingreName != null && measurement != null) {
            for (Ingredient i : listofIngre) {
                if (i.getName().equalsIgnoreCase(tool.encodeUTF8(ingreName))) {
                    request.setAttribute("msg", "Đã có nguyên liệu này ");
                    return;
                }
            }
            ingreDao.addIngredient(tool.encodeUTF8(ingreName), tool.encodeUTF8(measurement));
        }
    }

    private void setAvailable(HttpServletRequest request, HttpServletResponse response) {
        IngredientDAO ingreDao = new IngredientDAO();
        String ingreId = request.getParameter("ingreId");
        String setAvai = request.getParameter("setAvai");
        ingreDao.updateIngredientAvailability(Integer.parseInt(ingreId), Integer.parseInt(setAvai));

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
