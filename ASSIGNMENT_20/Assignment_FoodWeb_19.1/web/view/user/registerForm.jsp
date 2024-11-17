<%-- 
    Document   : registerForm
    Created on : Jun 12, 2024, 11:59:12 AM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/registerStyle.css">
    </head>
    <body>
        <div class="center">
            <h1>Đăng ký</h1>
            <form action="${pageContext.request.contextPath}/MainController?action=checkRegister" method="post">
                <div class="txt_field">
                    <input type="text" required name="txtUsername"/>
                    <span></span>
                    <label>Tên Đăng nhập</label>
                </div>
                <div class="err">
                    ${errUserName}
                </div>
                <div class="txt_field">
                    <input type="text" required name="txtPhone"
                           title="Your phone number is invalid must be have 10 number"/>
                    <span></span>
                    <label>Số điện thoại</label>
                </div>
                <div class="err">
                    ${errPhone}
                </div>
                <div class="txt_field">
                    <input type="text" required name="txtFullname"/>
                    <span></span>
                    <label>Họ và tên</label>
                </div>
                <div class="txt_field">
                    <input type="text" required name="txtEmail"/>
                    <span></span>
                    <label>Email</label>
                </div>
                <div class="err">
                    ${errMail}
                </div>
                <div class="txt_field eye">
                    <input type="password" required name="txtPassword" id="password"/>
                    <span></span>
                    <label for="password" >Mật khẩu</label>
                    <i class="show-password-toggle fas fa-eye-slash" data-toggle="password"></i>
                </div>
                <div class="txt_field eye">
                    <input type="password" required name="txtRepassword" id="repassword"/>
                    <span></span>
                    <label for="repassword" >Nhập lại mật khẩu</label>
                    <i class="show-password-toggle fas fa-eye-slash" data-toggle="repassword"></i>
                </div>
                <div class="err">
                    ${errRepass}
                </div>
                <div id="address-box">
                    <p id="headAddress">Địa chỉ</p>
                    <div class="address-select row">
                        <div class="city col-sm-4">
                            <select name="province" id="city">
                                <option value="" selected>Chọn tỉnh thành</option>           
                            </select>
                        </div>
                        <div class="district col-sm-4">
                            <select name="district" id="district">
                            <option value="" selected>Chọn quận huyện</option>
                            </select>
                        </div>
                        <div class="ward col-sm-4">
                            <select name="ward" id="ward">
                            <option value="" selected>Chọn phường xã</option>
                            </select>
                        </div>
                    </div> 
                    <div class="txt_field">
                        <input type="text" name="txtNote"/>
                        <span></span>
                        <label>Địa chỉ cụ thể</label>
                    </div>
                </div> 
                
                <input type="submit" value="Register" />
                <div class="signup_link">Bạn là thành viên ? <a href="${pageContext.request.contextPath}/MainController?action=login">Đăng nhập</a></div>
            </form>
        </div>    
        <script>
            setTimeout(function() {
                var errElements = document.getElementsByClassName('err');
                for (var i = 0; i < errElements.length; i++) {
                    errElements[i].style.display = 'none';
                }
            }, 5000);
        </script>    
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>   
        <script src="<%= request.getContextPath() %>/assets/js/showPass.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/addressVN.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
