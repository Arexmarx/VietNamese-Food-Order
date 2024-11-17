/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytag;

import dao.FoodDAO;
import dto.Food;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class GetNameTag extends SimpleTagSupport {

    private int id;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            FoodDAO d= new FoodDAO();
            Food f= d.getFood(id);
            out.print("<a style='color: black;  font-weight: bold' href='MainController?action=foodDetail&foodId=" + f.getId() + "'>" + f.getName() + "</a>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
