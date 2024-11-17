<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Sản phẩm</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">
    </head>
    <body>
        <c:set var="AllIngredient" value="${requestScope.listIngredient}"/>
        <c:set var="FoodRecipe" value="${requestScope.foodRecipe}"/>
        `<div class="container-fluid">
            <h1 style="text-align: center">Tạo bảng nguyên liệu món ăn</h1>
            <form action="MainControllerAdmin?action=addRecipe" class="form-horizontal" method="POST">
                <div class="form-group col-sm-10">
                    <label for="product-name" class="col-sm-2 control-label">Tên sản phẩm</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="product-name" name="txtSearchIngredient" value="${IngredientSearch}" placeholder="Tên nguyên liệu">
                    </div>
                </div>
                <div class="form-group col-sm-2">
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                    </div>
                    <div class="col-sm-6 text-right" style="">
                        <a href="MainControllerAdmin?action=previewFoodPage" class="btn btn-info">Bước tiếp theo</a>
                    </div>
                </div>
            </form>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Mã nguyên liệu</th>
                        <th>Tên nguyên liệu</th>
                        <th>Đơn vị</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${AllIngredient}">
                        <tr>
                            <td>${i.ingredientId}</td>
                            <td>${i.name}</td>
                            <td>${i.measurement}</td>
                            <td>
                                <div class="dropdown">
                                    <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">
                                        Thêm nguyên liệu
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <div class="dropdown-form" style="padding: 15px; min-width: 250px;">
                                                <div class="form-group">
                                                    <label for="quantity-${i.ingredientId}">Nhập số lượng:</label>
                                                    <input type="number" class="form-control" id="quantity-${i.ingredientId}" name="quantity" required="true">
                                                </div>
                                                <button onclick="addIngredient(${i.ingredientId}, '${i.measurement}')" class="btn btn-success">Thêm</button>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>    
                </tbody>
            </table>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/utilsAdmin.js"></script>
    </body>
</html>
