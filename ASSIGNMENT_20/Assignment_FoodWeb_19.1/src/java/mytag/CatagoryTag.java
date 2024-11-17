/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytag;

import dao.CatagoryDAO;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Admin
 */
public class CatagoryTag extends SimpleTagSupport {

    private int catagoryId;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            CatagoryDAO cataD = new CatagoryDAO();
            String cataName = cataD.getCatoryById(catagoryId).getCatagoryName();
            out.print("<span>" +cataName + "</span>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }
    
}
