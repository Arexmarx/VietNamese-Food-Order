<%-- 
    Document   : manageOrder
    Created on : Jul 6, 2024, 7:29:40 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Đơn hàng</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">
    </head>
    <body>
        <f:dasboardTag></f:dasboardTag>
            <div class="container">
                <h1 class="text-center">Quản Lý Đơn Hàng</h1>
                <form class="form-horizontal" action="MainControllerAdmin?action=manageOrder" method="post">
                    <input type="hidden" name="action" value="searchOrders">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="date">Ngày đặt hàng:</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" id="date" name="date" value="${requestScope.txtDate}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="phone">Số điện thoại Khách:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phone" name="phone" value="${requestScope.txtPhone}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email" >Email Khách:</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" value="${requestScope.txtEmail}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                        <a href="MainControllerAdmin?action=bacthOrder" class="btn btn-warning">Gom đơn theo địa chỉ</a>
                    </div>
                </div>
                <div class="form-group">
                    
                </div>
            </form>
            <div class="row text-center" style="margin-bottom: 10px;">
                <div class="col-md-1 col-6">
                </div>
                <div class="col-md-2 col-6">
                    <a class="btn btn-primary" href="MainControllerAdmin?action=manageOrder&txtStatus=1">Đang xác nhận</a>
                </div>
                <div class="col-md-2 col-6">
                    <a class="btn btn-primary" href="MainControllerAdmin?action=manageOrder&txtStatus=2">Đang đóng gói</a>
                </div>
                <div class="col-md-2 col-6">
                    <a class="btn btn-primary" href="MainControllerAdmin?action=manageOrder&txtStatus=3">Đang vận chuyển</a>
                </div>
                <div class="col-md-2 col-6">
                    <a class="btn btn-primary" href="MainControllerAdmin?action=manageOrder&txtStatus=4">Đã hoàn thành</a>
                </div>
                <div class="col-md-2 col-6">
                    <a class="btn btn-primary" href="MainControllerAdmin?action=manageOrder&txtStatus=5">Đã hủy</a>
                </div>
                <div class="col-md-1 col-6">
                </div>  
            </div>
            <c:set var="OrderList" value="${requestScope.fullListOrder}"/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Mã đơn hàng</th>
                        <th>Tên khách hàng</th>
                        <th>Ngày đặt hàng</th>
                        <th>Số điện thoại</th>
                        <th>Tổng tiền</th>
                        <th>Chi tiết đơn hàng</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${OrderList}">
                        <tr>
                            <td>${i.orderId}</td>
                            <td>${i.cusName}</td>
                            <td><v:formatDate value="${i.orderDate}" pattern="dd/MM/YYYY"/></td>
                            <td>${i.phone}</td>
                            <td><v:formatNumber value="${i.totalPrice}" type="currency" currencySymbol="VND" maxFractionDigits="0"/></td>
                            <td><a class="btn btn-info" href="MainController?action=viewOrderHistory&ordedId=${i.orderId}">Xem</a></td>
                            <td>
                                <c:choose>
                                    <c:when test = "${i.status<=4}">
                                        <select class="form-control" onchange="updateStatusOrder(${i.orderId}, this.value)">
                                            <option value="1" <c:if test="${i.status == 1}">selected</c:if>>Chờ xác nhận</option>
                                            <option value="2" <c:if test="${i.status == 2}">selected</c:if>>Đang đóng gói</option>
                                            <option value="3" <c:if test="${i.status == 3}">selected</c:if>>Đang vận chuyển</option>
                                            <option value="4" <c:if test="${i.status == 4}">selected</c:if>>Hoàn thành</option>
                                            </select> 
                                    </c:when>
                                    <c:otherwise>
                                        <p class="form-control">Đã Hủy</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>    
                </tbody>
            </table>
        </div>   
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/utilsAdmin.js"></script>
    </body>
</html>
