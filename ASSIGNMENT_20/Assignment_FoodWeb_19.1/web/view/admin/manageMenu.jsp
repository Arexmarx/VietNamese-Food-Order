<%-- 
    Document   : manageMenu
    Created on : Jul 6, 2024, 7:09:18 PM
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
        <f:dasboardTag></f:dasboardTag>
        <c:set var="FoodList" value="${requestScope.FoodList}"/>
        <c:set var="FoodName" value="${requestScope.searchName}"/>
        <c:set var="msgSuccess" value="${requestScope.msgSuccsess}"/>
        <div class="" style="padding: 1% 1% 1% 15%">
            <div class="content">
                <h1 style="text-align: center">Quản lí món ăn</h1>
                <form action="MainControllerAdmin?action=manageMenu" class="form-horizontal" method="POST">
                    <div class="form-group col-sm-10">
                        <label for="product-name" class="col-sm-2 control-label">Tên món ăn</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="product-name" name="txtSearchFood" value="${FoodName}" placeholder="Tên món ăn">
                        </div>
                    </div>
                    <div class="form-group col-sm-1">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                        </div>
                    </div>
                </form>
                <span class="col-sm-1" style="margin-left: 10px">
                    <a href="MainControllerAdmin?action=prepareCreate" class="btn btn-warning">Tạo Món Ăn</a>
                </span>
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
                        <c:forEach var="i" items="${FoodList}">
                            <tr>
                                <td>${i.id}</td>
                                <td id="name_${i.id}" >${i.name}</td>
                                <td><f:CatagoryTag catagoryId="${i.catagoryId}"></f:CatagoryTag></td>
                                <td id="price_${i.id}"><v:formatNumber value="${i.price}" maxFractionDigits="0"/>VND</td>
                                <td id="origin_${i.id}">${i.origin}</td>
                                <td><a class="btn btn-info" href="MainController?action=foodDetail&foodId=${i.id}">Chi tiết</a></td>
                                <td style="text-align: center">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updatePriceModal" data-id="${i.id}" data-name="${i.name}" data-price="${i.price}" data-origin="${i.origin}">Cập Nhật</button>
                                </td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
        </div>                

        <!-- Update Price Modal -->
        <div id="updatePriceModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cập Nhật Món Ăn</h4>
                    </div>
                    <div class="modal-body">
                        <form id="updatePriceForm" method="POST">
                            <input type="hidden" name="foodId" id="modalFoodId">
                            <div class="form-group">
                                <label for="modalFoodName">Tên sản phẩm</label>
                                <input type="text" class="form-control" id="modalFoodName" required>
                            </div>
                            <div class="form-group">
                                <label for="modalFoodPrice">Giá mới</label>
                                <input type="number" class="form-control" id="modalFoodPrice" name="foodPrice" required>
                            </div>
                            <div class="form-group">
                                <label for="modalFoodOrigin">Nguồn gốc mới</label>
                                <input type="text" class="form-control" id="modalFoodOrigin" name="foodOrigin" required>
                            </div>                            
                        </form>
                        <button onclick="updateFood()" class="btn btn-primary">Cập Nhật</button>
                        <a onclick="updateFood()" class="btn btn-warning" id="updateRecipeLink">Cập Nhật Công Thức</a>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/manageMenuJs.js"></script>
        <c:if test="${msgSuccess != null && !msgSuccess.isEmpty()}">
            <script>
                window.alert('${msgSuccess}');
            </script>
        </c:if> 
        <c:if test="${msgUpdate != null && !msgUpdate.isEmpty()}">
            <script>
                window.alert('${msgUpdate}');
            </script>
        </c:if>    
    </body>
</html>
