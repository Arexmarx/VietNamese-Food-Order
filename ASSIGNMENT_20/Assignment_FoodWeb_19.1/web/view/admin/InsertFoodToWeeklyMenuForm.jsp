<%-- 
    Document   : InsertFoodToWeeklyMenuForm
    Created on : Jul 15, 2024, 7:26:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">
    </head>
    <body>
        <c:set var="listFood" value="${requestScope.listOfFood}"/>
        <c:set var="FoodName" value="${requestScope.searchName}"/>
        <div class="container-fluid" >
            <div class="content">
                <h1 style="text-align: center">Quản lí món ăn</h1>
                <form action="MainControllerAdmin?action=searchFood" class="form-horizontal" method="POST">
                    <div class="form-group col-sm-10">
                        <label for="product-name" class="col-sm-2 control-label">Tên sản phẩm</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="product-name" name="txtSearchFood" value="${FoodName}" placeholder="Tên sản phẩm">
                        </div>
                    </div>
                    <div class="form-group col-sm-1">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                        </div>
                    </div>
                </form>
                <div class="form-group col-sm-1">
                    <div class=" col-sm-10">
                        <a href="MainControllerAdmin?action=manageWeeklyMenu" class="btn btn-warning">Hoàn thành</a>
                    </div>
                </div>     
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Mã món ăn</th>
                            <th>Tên món ăn</th>
                            <th>Kiểu</th>
                            <th>Giá</th>
                            <th>Xuất Xứ</th>
                            <th>Xem chi tiết</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="i" items="${listFood}">
                            <tr>
                                <td>${i.id}</td>
                                <td>${i.name}</td>
                                <td><f:CatagoryTag catagoryId="${i.catagoryId}"></f:CatagoryTag></td>
                                <td id="price_${i.id}"><v:formatNumber value="${i.price}" maxFractionDigits="0"/>VND</td>
                                <td id="origin_${i.id}">${i.origin}</td>
                                <td><a class="btn btn-info" href="MainController?action=foodDetail&foodId=${i.id}">Chi tiết</a></td>
                                <td style="text-align: center">
                                    <a class="btn btn-primary" onclick="insertFood(${i.id})">Thêm</a>
                                </td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
        </div> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/utilsAdmin.js"></script>
        <script>
                                        function insertFood(FoodID) {
                                            $.ajax({
                                                type: 'POST',
                                                url: 'insertFoodServlet',
                                                data: {
                                                    foodId: FoodID,
                                                },
                                                success: function (response) {
                                                    alert(response);
                                                },
                                                error: function (xhr, status, error) {
                                                    alert('Đã xảy ra lỗi: ' + error);
                                                }
                                            });
                                        }
        </script>
    </body>
</html>
