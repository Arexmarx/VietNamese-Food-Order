<%-- 
    Document   : loginForm
    Created on : Jun 11, 2024, 11:26:43 PM
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
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/loginStyle.css">
    </head>
    <body>
        <div class="center">
            <h1>Đăng nhập</h1>
            <form action="${pageContext.request.contextPath}/MainController?action=checkLogin" method="post">
                <div class="txt_field">
                    <input type="text" required name="txtUsername"/>
                    <span></span>
                    <label>Tên Đăng Nhập</label>
                </div>
                <div class="txt_field eye">
                    <input type="password" required name="txtPassword" id="password"/>
                    <span></span>
                    <label for="password" >Mật Khẩu</label>
                    <i class="show-password-toggle fas fa-eye-slash" data-toggle="password"></i>
                </div>
                <div id="errLogin">
                    ${msgError}
                </div>
                <input type="submit" value="Login" />
                <div class="signup_link">Không phải thành viên ? <a href="${pageContext.request.contextPath}/MainController?action=register">Đăng ký</a></div>
            </form>
        </div> 
        <c:if test="${requestScope.msgErrorCart != null}">
            <script>
                alert('${requestScope.msgErrorCart}');
            </script>   
        </c:if>
            <c:if test="${requestScope.errAddToPlan != null}">
            <script>
                alert('${requestScope.errAddToPlan}');
            </script>   
        </c:if>
            <c:if test="${requestScope.errShowPlan != null}">
            <script>
                alert('${requestScope.errShowPlan}');
            </script>   
        </c:if>
        <script>
            setTimeout(function () {
                var errElement = document.getElementById('errLogin');
                if (errElement) {
                    errElement.style.display = 'none';
                }
            }, 5000);
        </script>
        <script src="<%= request.getContextPath()%>/assets/js/showPass.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
