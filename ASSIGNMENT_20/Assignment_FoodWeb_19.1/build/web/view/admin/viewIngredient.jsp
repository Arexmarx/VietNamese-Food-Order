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
        <c:set var="ingreList" value="${requestScope.listIngre}"/>
        <c:set var="msgSuccess" value="${requestScope.msgSuccsess}"/>
        <div class="" style="padding: 1% 1% 1% 15%">
            <div class="container-fluid">
                <h1 style="text-align: center">Quản lí nguyên liệu</h1>

                <form action="MainControllerAdmin?action=manageIngredient" class="form-horizontal" method="POST">
                    <div class="form-group col-sm-9">
                        <label for="product-name" class="col-sm-2 control-label">Tên món ăn</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="product-name" name="txtSearchIngre" value="${searchName}" placeholder="Tên món ăn">
                        </div>
                    </div>
                    <div class="form-group col-sm-1">
                        <div class=" col-sm-4">
                            <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                        </div>
                    </div>
                </form>

                <span class="col-sm-1" style="margin-left: 10px">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                            Thêm Nguyên Liệu
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="dropdown-form" style="padding: 15px; min-width: 250px;">
                                    <form action="MainControllerAdmin?action=manageIngredient&option=addIngre" method="post">
                                        <div class="form-group">
                                            <label>Tên nguyên liệu:</label>
                                            <input type="text" class="form-control" name="nameIngre" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Đơn vị:</label>
                                            <input type="text" class="form-control" name="measurement" required>
                                        </div>
                                        <button type="submit" class="btn btn-success">Tạo</button>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </span>
                <div id="alert" style="
                     font-weight: bolder;
                     text-align: center;
                     color: red;" class="col-sm-12 ">${msg}</div> 
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Mã nguyên liệu</th>
                            <th>Tên nguyên liệu</th>
                            <th>Đơn vị</th>
                            <th>Khả dụng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="i" items="${ingreList}">
                            <tr>
                                <td>${i.ingredientId}</td>
                                <td>${i.name}</td>
                                <td>${i.measurement}</td>
                                <td>
                                    <button id="button1_${i.ingredientId}" onclick="setAvailable(${i.ingredientId},${i.isAvailable ? '0' : '1'})" class="btn btn-${i.isAvailable ? 'success' : 'danger'}">
                                        ${i.isAvailable ? "Có sẵn" : "Không có sẵn"}
                                    </button>
                                    <button id="button2_${i.ingredientId}" onclick="setAvailable(${i.ingredientId},${i.isAvailable ? '1' : '0'})" class="btn btn-${i.isAvailable ? 'danger' : 'success'}" style="display: none;">
                                        ${i.isAvailable ? "Không có sẵn" : "Có sẵn"}
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
        </div>                


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/manageIngreJs.js"></script>
    </body>
</html>
