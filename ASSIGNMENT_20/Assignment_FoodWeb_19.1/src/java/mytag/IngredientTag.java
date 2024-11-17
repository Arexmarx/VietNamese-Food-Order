/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytag;

import dao.IngredientDAO;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class IngredientTag extends SimpleTagSupport {

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
            IngredientDAO ingreD = new IngredientDAO();
            String IngreName = ingreD.getIngredient(id).getName();
            out.print("<p id='textStyle' style='display: inline'>" + IngreName + ":</p>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
