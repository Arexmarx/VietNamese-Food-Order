/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.manage_menu;

import dao.FoodDAO;
import dao.IngredientDAO;
import dao.RecipeDAO;
import dto.Food;
import dto.Ingredient;
import dto.Recipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
public class updateRecipeServlet extends HttpServlet {

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
            HttpSession session= request.getSession();
            String option= request.getParameter("option");
            if(option==null || option.isEmpty()){
                option="searchIngredient";
            }
            String url="";
            
            switch(option){
                case "searchIngredient":
                    getDataIngredient(request, response);
                    getFoodRecipe(request, response);
                    url="updateRecipePage.jsp";
                    break;
                case "deleteIngredient":
                    deleteIngredient(request, response);
                    getDataIngredient(request, response);
                    getFoodRecipe(request, response);
                    url="updateRecipePage.jsp";
                    break;
                case "addIngredient":
                    addIngredient(request, response);
                    getDataIngredient(request, response);
                    getFoodRecipe(request, response);
                    url="updateRecipePage.jsp";
                    break;
                case "done":
                    session.removeAttribute("FID");
                    url="manageMenuServlet";
                    break;
            }
            String pathUser="/view/admin/";
            if (url.matches("^.+\\.jsp$")) {
                url=pathUser+url;
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    private void addIngredient(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession();
        String ingreID= request.getParameter("ingreId");
        String foodID= (String) session.getAttribute("FID");
        String quantity= request.getParameter("quantity");
        String measurement= request.getParameter("measurement");
        float amount= Integer.parseInt(quantity);
        RecipeDAO rDao= new RecipeDAO();
        for (Recipe i : rDao.getAllRecipe(Integer.parseInt(foodID))) {
            if(i.getIngredientId()==Integer.parseInt(ingreID)){
                amount= amount+ i.getQuantity();
                rDao.deleteRecipe(Integer.parseInt(foodID), Integer.parseInt(ingreID));
            }
        }
        rDao.addRecipe(Integer.parseInt(foodID), Integer.parseInt(ingreID), amount, measurement);
    }
    
    private void deleteIngredient(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession();
        String ingreID= request.getParameter("ingreId");
        RecipeDAO rDao= new RecipeDAO();
        String foodID= (String) session.getAttribute("FID");
        
        rDao.deleteRecipe(Integer.parseInt(foodID), Integer.parseInt(ingreID));
    }
    private void getFoodRecipe(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession();
        String foodID= request.getParameter("foodId");
        RecipeDAO rDao= new RecipeDAO();
        List<Recipe> list= new ArrayList<>();
        
        if (foodID==null || foodID.isEmpty()) {
            foodID= (String) session.getAttribute("FID");
            list= rDao.getAllRecipe(Integer.parseInt(foodID));
        }
        
        list= rDao.getAllRecipe(Integer.parseInt(foodID));
        session.setAttribute("FID", foodID);
        request.setAttribute("Recipe", list);
    }
    private void getDataIngredient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        IngredientDAO ingreDao = new IngredientDAO();
        ExtraUtils tool = new ExtraUtils();
        String findName = request.getParameter("txtSearchIngredient");
        if (findName == null || findName.isEmpty()) {
            findName = "";
        } else {
            findName = tool.encodeUTF8(findName);
        }
        List<Ingredient> list = ingreDao.searchIngredientsByName(findName);
        String act = request.getParameter("action");

        request.setAttribute("listIngredient", list);
        request.setAttribute("IngredientSearch", findName);
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
