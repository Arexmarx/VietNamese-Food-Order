<%-- 
    Document   : showInforPage
    Created on : Jun 18, 2024, 8:05:40 PM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:set var="acc" value="${sessionScope.LoginObj}"/>
        <c:set var="add" value="${sessionScope.AddressObj}"/>
        <div class="center" style="margin: 0;">
            <h1>Thông tin của bạn</h1>
            <form action="#" method="post">
                <div class="txt_field">
                    <input type="text" required name="txtUsername" value="${acc.userName}"/>
                    <span></span>
                    <label>Tên Đăng nhập</label>
                </div>
                <div style="color: red">
                    ${errUserName}
                </div>
                <div class="txt_field">
                    <input type="text" required name="txtPhone" value="${acc.phone}"/>
                    <span></span>
                    <label>Số điện thoại</label>
                </div>
                <div style="color: red">
                    ${errPhone}
                </div>
                <div class="txt_field">
                    <input type="text" required name="txtFullname" value="${acc.fullName}"/>
                    <span></span>
                    <label>Họ và tên</label>
                </div>
                <div class="txt_field">
                    <input type="text" required name="txtEmail" value="${acc.email}"/>
                    <span></span>
                    <label>Email</label>
                </div>
                <div style="color: red">
                    ${errMail}
                </div>
                 
                <div class="txt_field">
                    <input type="text" required name="txtAddress"
                           value="${add.notes},${add.ward},${add.district},${add.province}"/>
                    <span></span>
                    <label>Địa chỉ</label>
                </div>
            </form>
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
