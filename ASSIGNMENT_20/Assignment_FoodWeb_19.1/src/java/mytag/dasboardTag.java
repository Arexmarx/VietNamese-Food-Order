/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Admin
 */
public class dasboardTag extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String contextPath = request.getContextPath();

        try {
            out.print("<div class='sidebar'>");
            out.print("<h2>Admin Panel</h2><ul>");
            out.print("<li><a href='MainControllerAdmin?action=homePageAd' data-section='dashboard'>Dashboard</a></li>");
            out.print("<li><a href='MainControllerAdmin?action=manageUser'>Quản lý Người dùng</a></li>");
            out.print("<li><a href='MainControllerAdmin?action=manageMenu'>Quản lý Món Ăn</a></li>");
            out.print("<li><a href='MainControllerAdmin?action=manageIngredient'>Quản lý Nguyên Liệu</a></li>");
            out.print("<li><a href='MainControllerAdmin?action=manageOrder'>Quản Lý Đơn Hàng</a></li>");
            out.print("<li><a href='MainControllerAdmin?action=manageWeeklyMenu'>Quản Lý Thực Đơn</a></li>");
            out.print("<li><a href='MainController?action=logout'>Đăng Xuất</a></li>");
            out.print("</ul></div>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
