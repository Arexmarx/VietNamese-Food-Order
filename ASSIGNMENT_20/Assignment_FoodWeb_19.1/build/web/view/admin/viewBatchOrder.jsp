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
        <f:dasboardTag>
        </f:dasboardTag>
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
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <a href="MainControllerAdmin?action=bacthOrder" class="btn btn-warning">Gom đơn theo địa chỉ</a>
                    </div>
                </div>
            </form>
            <c:set var="batchList" value="${requestScope.BatchOrderAddress}"/>
            <h1 class="text-center">Batch Order Addresses</h1>
            <c:forEach var="provinceEntry" items="${BatchOrderAddress}">
                <h2>Province: ${provinceEntry.key}</h2>
                <c:forEach var="districtEntry" items="${provinceEntry.value}">
                    <h3>District: ${districtEntry.key}</h3>
                    <c:forEach var="wardEntry" items="${districtEntry.value}">
                        <h4>Ward: ${wardEntry.key}</h4>
                        <table class="table table-bordered">
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
                                <c:forEach var="orderAddress" items="${wardEntry.value}">
                                    <tr>
                                        <f:OrderTag id="${orderAddress.orderId}"></f:OrderTag>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
        </div>          
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/utilsAdmin.js"></script>
</body>
</html>
