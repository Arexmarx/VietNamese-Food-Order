<%-- 
    Document   : viewHistoryOrder
    Created on : Jun 25, 2024, 7:58:46 PM
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
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/orderHistoryStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <main style="min-height: 700px;">
            <div class="container-fluid wrap">
                <div class="row text-center">
                    <div class="col-md-1 col-6">
                    </div>
                    <div class="col-md-2 col-6">
                        <a href="MainController?action=viewHistoryOrder&txtStatus=1"><i class="fa-solid fa-wallet fa-4x Order-icon"></i></a>
                    </div>
                    <div class="col-md-2 col-6">
                        <a href="MainController?action=viewHistoryOrder&txtStatus=2"><i class="fa-solid fa-box fa-4x Order-icon"></i></a>
                    </div>
                    <div class="col-md-2 col-6">
                        <a href="MainController?action=viewHistoryOrder&txtStatus=3"><i class="fa-solid fa-truck-fast fa-4x Order-icon"></i></a>
                    </div>
                    <div class="col-md-2 col-6">
                        <a href="MainController?action=viewHistoryOrder&txtStatus=4"><i class="fa-solid fa-square-check fa-4x Order-icon"></i></a>
                    </div>
                    <div class="col-md-2 col-6">
                        <a href="MainController?action=viewHistoryOrder&txtStatus=5"><i class="fa-solid fa-calendar-xmark fa-4x Order-icon"></i></a>
                    </div>
                    <div class="col-md-1 col-6">
                    </div>  
                </div>
            </div>
            <div class="order-container">
                <c:set var="list" value="${requestScope.HistoryOrder}"/>
                <c:if test="${list != null}">
                    <c:forEach var="i" items="${list}">
                        <div class="order-item row" id="order-${i.orderId}">
                            <p class="col-md-12 order-title">Tên khách hàng: ${i.cusName}</p>
                            <p class="col-md-9">Tổng tiền: <v:formatNumber value="${i.totalPrice}" type="currency" currencySymbol="VND" /></p> 
                            <p class="col-md-3"><a class="btn btn-success" href="MainController?action=viewOrderHistory&ordedId=${i.orderId}">Chi tiết đơn hàng</a></p>                      
                            <p class="col-md-9">Ngày đặt hàng: <v:formatDate value="${i.orderDate}" pattern="dd/MM/YYYY" /></p>
                            <c:if test="${i.status==1 || i.status==2}">
                                <p class="col-md-3"><a onclick="cancleOrder(${i.orderId}, '5')" class="btn btn-danger">Hủy Đơn</a></p>
                            </c:if>
                        </div>
                        <div class="order-divider"></div>    
                    </c:forEach>
                </c:if> 
                <c:if test="${empty list}">
                    <div class="order-item notify">
                        <i class="fa-solid fa-clipboard-question fa-10x center"></i>
                        <p class="order-title">Bạn chưa có đơn hàng nào</p>
                    </div>

                </c:if>
            </div>
        </main>
        <%@include file="common/footer.html" %>    
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assets/js/utils.js"></script>
    </body>
</html>
