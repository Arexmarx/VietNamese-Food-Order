package controller.admin.manage_menu;

import dao.CatagoryDAO;
import dao.FoodDAO;
import dto.Food;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.ExtraUtils;

@MultipartConfig
public class createFoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String destFolder ="D:\\PRJ301\\Assignment\\Assignment_FoodWeb_19\\web\\images";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session= request.getSession();
        ExtraUtils tool= new ExtraUtils();
        FoodDAO fDao= new FoodDAO();
        CatagoryDAO cateDao= new CatagoryDAO();
        
        Part filePart = request.getPart("file");
        
        String foodName= tool.encodeUTF8(request.getParameter("foodName")) ;
        String foodCategory= request.getParameter("foodCategory");
        String foodOrigin= tool.encodeUTF8(request.getParameter("foodOrigin")) ;
        String foodPrice= request.getParameter("foodPrice");
        String description= tool.encodeUTF8(request.getParameter("description")) ;
        String wayCooking= tool.encodeUTF8(request.getParameter("wayCooking"));
        String imgUrl="";
        
        int price= Integer.parseInt(foodPrice);
        int categorID=0;
        
        if (cateDao.getCatoryById(Integer.parseInt(foodCategory))!= null) {
            categorID= Integer.parseInt(foodCategory);
        } else{
            request.getRequestDispatcher("MainControllerAdmin?action=manageMenu").forward(request, response);
        }
        if (filePart != null && destFolder != null && !destFolder.isEmpty()) {
            String fileName = getFileName(filePart);
            saveFile(filePart, destFolder, fileName);
            
            out.print("images\\"+fileName);
            imgUrl="images\\"+fileName;
            //fDao.insertFood(categorID, foodName, price, foodOrigin, imgUrl, description, wayCooking);
            Food food= new Food(foodName, price, foodOrigin, imgUrl, categorID, description, wayCooking);
            session.setAttribute("FoodCreated", food);
        } else {
            // Handle error if necessary
        }
        request.getRequestDispatcher("MainControllerAdmin?action=addRecipe").forward(request, response);
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    private void saveFile(Part filePart, String destFolder, String fileName) throws IOException {
        File destDir = new File(destFolder);

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        File destFile = new File(destDir, fileName);
        Files.copy(filePart.getInputStream(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
