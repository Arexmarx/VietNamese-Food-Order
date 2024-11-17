/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytag;

import dao.OrderDAO;
import dto.Order;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import utils.ExtraUtils;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class OrderTag extends SimpleTagSupport {

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
            OrderDAO ord = new OrderDAO();
            Order i = ord.getOrder(id);
            ExtraUtils ex = new ExtraUtils();
            Locale vietnamLocale = new Locale("vi", "VN");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnamLocale);

            String formattedAmount = currencyFormatter.format(i.getTotalPrice());
            
            
            out.print("<td>" + i.getOrderId() + "</td>");
            out.print("<td>" + i.getCusName() + "</td>");
            out.print("<td>" + ex.formatDate(i.getOrderDate()) + "</td>");
            out.print("<td>" + i.getPhone() + "</td>");
            out.print("<td>" + formattedAmount + "</td>");
            out.print("<td><a class=\"btn btn-info\" href=\"MainController?action=viewOrderHistory&ordedId=" + i.getOrderId() + "\">Xem</a></td>");
            out.print("<td>");
            out.print("<select class=\"form-control\" onchange=\"updateStatusOrder(" + i.getOrderId() + ", this.value)\">");
            out.print("<option value=\"1\"" + (i.getStatus() == 1 ? " selected" : "") + ">Chờ xác nhận</option>");
            out.print("<option value=\"2\"" + (i.getStatus() == 2 ? " selected" : "") + ">Đang đóng gói</option>");
            out.print("<option value=\"3\"" + (i.getStatus() == 3 ? " selected" : "") + ">Đang vận chuyển</option>");
            out.print("<option value=\"4\"" + (i.getStatus() == 4 ? " selected" : "") + ">Hoàn thành</option>");
            out.print("</select>");
            out.print("</td>");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

}
