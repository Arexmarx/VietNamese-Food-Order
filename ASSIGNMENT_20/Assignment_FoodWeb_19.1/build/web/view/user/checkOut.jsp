<%-- 
    Document   : checkOut
    Created on : Jun 20, 2024, 8:05:50 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/registerStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <c:set var="acc" value="${sessionScope.LoginObj}"/>
        <c:set var="add" value="${sessionScope.AddressObj}"/>
        <div class="center" style="margin: 100px auto;">
            <h1>Kiểm tra thông tin thanh toán</h1>
            <form action="MainController?action=saveOrder" method="post">
                <div class="txt_field">
                    <input type="text" required name="txtPhone" value="${acc.phone}"/>
                    <span></span>
                    <label>Số điện thoại</label>
                </div>
                <div class="txt_field">
                    <input type="text" required name="txtFullname" value="${acc.fullName}"/>
                    <span></span>
                    <label>Họ và tên</label>
                </div>
                <div id="address-box">
                    <p id="headAddress">Địa chỉ nhận hàng</p>
                    <div class="address-select row">
                        <div class="city col-sm-4">
                            <select name="province" id="city">
                                <option value="${add.province}" selected>${add.province}</option>           
                            </select>
                        </div>
                        <div class="district col-sm-4">
                            <select name="district" id="district">
                            <option value="${add.district}" selected>${add.district}</option>
                            </select>
                        </div>
                        <div class="ward col-sm-4">
                            <select name="ward" id="ward">
                            <option value="${add.ward}" selected>${add.ward}</option>
                            </select>
                        </div>
                    </div> 
                    <div class="txt_field">
                        <input type="text" value="${add.notes}" required name="txtNote"/>
                        <span></span>
                        <label>Địa chỉ cụ thể</label>
                    </div>
                </div>
                <div class="txt_field">
                    Tồng tiền:
                    <input disabled="true" type="text" required name="txtEmail" value="<v:formatNumber value="${totalPrice}" type="currency" currencySymbol="VND" />"/>                    
                    <span></span>
                    <label></label>
                </div>  
                <div class="txt_field">
                    Phương pháp thanh toán:
                    <select name="PaymentMethod">
                        <option value="Cash" selected="">Cash</option>
                        <option value="VNPay" selected="">VNPay</option>
                        <option value="MoMo" selected="">MoMo</option>
                    </select>
                </div>    
                <input style="margin: 0 200px; margin-bottom: 20px; width: 200px;" type="submit" value="Confirm" />
            </form>
        </div>
        
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/addressVN.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
