/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.AccAddressDAO;
import dao.AccountDAO;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Part;
import static jdk.nashorn.internal.objects.NativeError.getFileName;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class ExtraUtils {
    private String patterPhone = "^0(86|96|97|98|39|38|37|36|35|34|33|32|91|94|88|83|84|85|81|82|70|79|77|76|78|89|90|93|92|52|56|58)\\d{7}$";
    private String patternMail = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    private String patternUserName = "^(?=.*[A-Z]).{6,}$";
    private String patternPass = "^.{6,}$";

    public List<String> errUnigeAccount(List<String> list) {
        List<String> err = new ArrayList<>();
        AccountDAO d = new AccountDAO();
        for (int i = 0; i < 4; i++) {
            err.add("");
        }
        //check username
        if (list.get(0).matches(patternUserName)) {
            if (d.checkUserName(list.get(0)) != null) {
                err.set(0, "Tên đăng nhập đã tồn tại!");
            }
        } else {
            err.set(0, "Tên đăng nhập cần ít nhất 6 kí tự và 1 kí tự in hoa!");
        }

        //check phone number
        if (list.get(1).matches(patterPhone)) {
            if (d.checkPhone(list.get(1)) != null) {
                err.set(1, "Số điện thoại đẫ tồn tại!");
            }
        } else {
            err.set(1, "Số điện thoại của bạn không hợp lệ!");
        }
        //check email
        if (list.get(2).matches(patternMail)) {
            if (d.checkEmail(list.get(2)) != null) {
                err.set(2, "Email này đã tồn tại!");
            }
        } else {
            err.set(2, "Email không hợp lệ!");
        }
        //check repassword
        if (list.get(3).matches(patternPass)) {
            if (!list.get(3).equals(list.get(4))) {
                err.set(3, "Mật khẩu nhập lại không đúng!");
            }
        } else {
            err.set(3, "Mật khẩu cần ít nhất 6 kí tự!");
        }
        return err;
    }
    
    public String encodeUTF8(String str) throws UnsupportedEncodingException{
        return new String (str.getBytes ("iso-8859-1"), "UTF-8");
    }
    
    public String formatDate(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
        String day = formatter.format(d);
        
        return day;
    }
    
    public String formatDateJSP(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String day = formatter.format(d);
        return day;
    }
}
