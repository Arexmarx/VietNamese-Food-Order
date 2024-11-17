<%-- 
    Document   : viewManageFoodWeeklyMenu
    Created on : Jul 16, 2024, 8:39:43 PM
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
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">
    </head>
    <body>



        <c:set var="listFoodOfWeekly" value="${requestScope.itemOfWeeklyMenu}"/>
        <c:set var="listOfAllFood" value="${requestScope.listOfAllFood}"/>
        <c:set var="search" value="${requestScope.searchQuerry}"/>
        <c:set var="weekObj" value="${sessionScope.weeklyObj}"/>
        <div class="" style="padding: 1% 1% 1% 1%">
            <div class="container-fluid">
                <div class="col-md-4">
                    <h2 style="text-align: center">${weekObj.name}</h2>
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Mã món ăn</th>
                                <th>Tên món ăn</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${listFoodOfWeekly}">
                                <tr>
                                    <td>${i.foodID}</td>
                                    <td><f:GetNameTag id="${i.foodID}"></f:GetNameTag></td>
                                        <td style="text-align: center">
                                            <a href="MainControllerAdmin?action=manageFoodWeeklyMenu&option=deleteFood&foodId=${i.foodID}" class="btn btn-primary">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div> 
                <div class="col-md-8">
                    <div class="row">
                        <form action="MainControllerAdmin?action=manageFoodWeeklyMenu&option=formUpdateFood" class="form-horizontal" method="POST">
                            <div class="form-group col-sm-10">
                                <label for="product-name" class="col-sm-2 control-label">Tên món ăn</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="product-name" name="textSearchFood" value="${search}" placeholder="Tên món ăn">
                                </div>
                            </div>
                            <div class="form-group col-sm-1">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                                </div>
                            </div>
                        </form>
                        <div class="col-sm-1" style="margin-left: 30px;">
                            <a href="MainControllerAdmin?action=manageFoodWeeklyMenu&option=done" class="btn btn-warning">Hoàn thành</a>
                        </div>
                    </div>
                    <p id="alert" style="margin: 5px 0;
                       font-weight: bolder;
                       text-align: center;
                       color: red;">${msg}</p>
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Mã món ăn</th>
                                <th>Tên món ăn</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${listOfAllFood}">
                                <tr>
                                    <td>${i.id}</td>
                                    <td><f:GetNameTag id="${i.id}"></f:GetNameTag></td>
                                        <td style="text-align: center">
                                            <a href="MainControllerAdmin?action=manageFoodWeeklyMenu&option=addFood&foodId=${i.id}" class="btn btn-primary">Thêm vào Thực đơn tuần</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script>
            setTimeout(function () {
                var errElements = document.getElementById('alert');
                errElements.style.display = 'none';
            }, 2000);
        </script>
    </body>
</html>
