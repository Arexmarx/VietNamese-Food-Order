<%-- 
    Document   : updateRecipePage
    Created on : Jul 16, 2024, 7:28:14 PM
    Author     : Nguyễn Nhật Trường
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
        <style>
            #mess{
                margin: 5px 0;
                font-weight: bolder;
                color: red;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <c:set var="AllIngredient" value="${requestScope.listIngredient}"/>
        <c:set var="Recipe" value="${requestScope.Recipe}"/>
        <div class="container-fluid row">
            <div class="col-sm-4" style="margin-top: 50px;">
                <h1 style="text-align: center">Công thức món <f:GetNameTag id="${Recipe[0].foodId}"></f:GetNameTag></h1>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Mã nguyên liệu</th>
                            <th>Tên nguyên liệu</th>
                            <th>Số lượng</th>
                            <th>Đơn vị</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="i" items="${Recipe}">
                            <tr>
                                <td>${i.ingredientId}</td>
                                <td><f:IngredientTag id="${i.ingredientId}"></f:IngredientTag></td>
                                <td>${i.quantity}</td>
                                <td>${i.measurement}</td>
                                <td><a href="MainControllerAdmin?action=updateRecipe&option=deleteIngredient&ingreId=${i.ingredientId}"
                                       class="btn btn-danger">
                                        xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
            <div class="col-sm-8">
                <form action="MainControllerAdmin?action=updateRecipe&option=searchIngredient" class="form-horizontal" method="POST">
                    <h1 style="text-align: center">Cập Nhật Ngyên Liệu Món Ăn 
                        <a class="btn btn-success" href="MainControllerAdmin?action=updateRecipe&option=done">
                            <i class="fa-solid fa-check"></i>
                        </a>
                    </h1>
                    <div id="mess">${mess}</div>
                    <div class="form-group col-sm-10">
                        <label for="product-name" class="col-sm-2 control-label">Tên nguyên liệu</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="product-name" name="txtSearchIngredient" value="${IngredientSearch}" placeholder="Tên nguyên liệu">
                        </div>
                    </div>
                    <div class="form-group col-sm-2">
                        <div class="col-sm-6">
                            <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
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
                                    <form action="MainControllerAdmin?action=updateRecipe&option=addIngredient" method="POST">
                                        <input type="hidden" name="ingreId" value="${i.ingredientId}">
                                        <input type="hidden" name="measurement" value="${i.measurement}">
                                        <input type="number" name="quantity" min="0" placeholder="số lượng">
                                        <button type="submit" class="btn btn-success ">Thêm</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/manageMenuJs.js"></script>
        <script>
            setTimeout(function () {
                var errElements = document.getElementById('mess');
                errElements.style.display = 'none';
            }, 5000);
        </script>  
    </body>
</html>
