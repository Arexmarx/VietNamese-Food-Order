/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytag;

import dao.FoodDAO;
import dao.IngredientDAO;
import dao.RecipeDAO;
import dto.Food;
import dto.Ingredient;
import dto.Recipe;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class FoodTag extends SimpleTagSupport {

    private int idFood;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            FoodDAO d = new FoodDAO();
            RecipeDAO rDao = new RecipeDAO();
            IngredientDAO ingreDao = new IngredientDAO();
            Food f = d.getFood(idFood);

            Boolean check = true;
            for (Recipe i : rDao.getAllRecipe(f.getId())) {
                if (!ingreDao.getIngredient(i.getIngredientId()).isIsAvailable()) {
                    check = false;
                }
            }
            Locale vietnamLocale = new Locale("vi", "VN");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnamLocale);
            String formattedAmount = currencyFormatter.format(f.getPrice());
            if(f.getPrice()>0){
                out.print("<div class='monan'>");
                out.print("<img src='" + f.getImgURL() + "' />");
                out.print("<div id='food-infor'>");
                out.print("<p id='foodName' style='margin: 0'>" + f.getName() + "</p>");
                out.print("<p class='field'>Nguồn Gốc:</p> " + f.getOrigin() + "<br/>");
                out.print("<p class='field'>Giá:</p> " + formattedAmount + "<br/>");
                out.print("<div style='padding-bottom: 10px'>");
                if (check) {
                    out.print("<a class='button btn btn-warning' onclick='addToCart(" + f.getId() + ")' role='button'>Thêm vào giỏ hàng</a>");
                    out.print("<a class='button btn btn-warning' href='MainController?action=foodDetail&foodId=" + f.getId() + "' role='button'>Chi tiết</a>");
                } else {
                    out.print("<p style='text-align:center; margin-left: 90px;' class='button btn btn-warning'>Đã bán hết</p>");
                }
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
            }
//            <v:formatNumber value='"+f.getPrice()+"' type='currency' currencySymbol='VND'/>
//            <v:formatNumber value= "${i.price}" type="currency" currencySymbol="VND"/>

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

}
