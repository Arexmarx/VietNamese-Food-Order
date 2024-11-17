<%-- 
    Document   : foodDetails
    Created on : Jun 21, 2024, 4:18:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/detailStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="previewFood" value="${sessionScope.FoodCreated}" />
        <c:set var="previewRecipe" value="${sessionScope.foodRecipe}"/>
        <div class="row detail">
            <div class="col-md-12 text-right">
                <a href="MainControllerAdmin?action=saveFood&decision=cancle" class="btn btn-warning">Hủy</a>
                <a href="MainControllerAdmin?action=saveFood&decision=save" class="btn btn-warning">lưu món ăn</a>
            </div>
            <h1 class="textStyle" style="text-align: center"> ${previewFood.name}</h1> 
            <div class="col-md-6 detail-1">
                <div style="text-align: center">
                    <img class="imgStyle" src="${previewFood.imgURL}" >
                </div>
                <p class="textStyle"><strong>Nguồn Gốc:</strong> ${previewFood.origin}</p>
                <p class="textStyle"><strong>Thể Loại:</strong> <f:CatagoryTag catagoryId="${previewFood.catagoryId}"></f:CatagoryTag></p>
                <p class="textStyle"><strong>Mô Tả: </strong>${previewFood.description}</p> 
                <p class="textStyle"><strong>Cách Làm:</strong>${previewFood.wayCooking}</p> 
                <p class="textStyle" style="text-align: center"><strong>Giá:</strong><v:formatNumber value= "${previewFood.price}"  maxFractionDigits="0"/>VND</p>
            </div>
            <div class="col-md-5 detail-2">
                <p >Thành Phần<p>
                <c:forEach var="entry" items="${previewRecipe}">                
                    <ul>
                        <li id="detail-li"><f:IngredientTag id="${entry.key}"></f:IngredientTag><p id="textStyle" style="display: inline">${entry.value.quantity} ${entry.value.measurement}</p></li>
                    </ul>
                </c:forEach>
            </div>
        </div>  
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>