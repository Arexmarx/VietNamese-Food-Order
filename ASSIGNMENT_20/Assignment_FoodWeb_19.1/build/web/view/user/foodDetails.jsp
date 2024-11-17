<%-- 
    Document   : foodDetails
    Created on : Jun 21, 2024, 4:18:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB" prefix="g"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/detailStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="acc" value="${sessionScope.LoginObj}"/>
        <c:if test="${acc.role=='User'}">
            <%@include file="common/header.jsp" %>  
        </c:if>
        <c:set var="detailFoods" value="${requestScope.detailFood}" />
        <c:set var="recipeFoodList" value="${requestScope.recipeFood}"/>
        <div class="row detail">
            <h1 class="textStyle" style="text-align: center"> ${detailFoods.name}</h1> 
            <div class="col-md-6 detail-1">
                <div style="text-align: center">
                        <img class="imgStyle" src="${detailFoods.imgURL}" >
                </div>
                <p class="textStyle"><strong>Nguồn Gốc:</strong> ${detailFoods.origin}</p>
                <p class="textStyle"><strong>Thể Loại:</strong> <f:CatagoryTag catagoryId="${detailFoods.catagoryId}"></f:CatagoryTag></p>
                <p class="textStyle"><strong>Mô Tả: </strong>${detailFoods.description}</p> 
                <p class="textStyle"><strong>Cách Làm:</strong>${detailFoods.wayCooking}</p> 
                <p class="textStyle" style="text-align: center"><strong>Giá:</strong><v:formatNumber value= "${detailFoods.price}"  maxFractionDigits="0"/>VND</p>
            </div>
            <div class="col-md-5 detail-2">
                <p >Thành Phần<p>
                    <c:forEach var="i" items="${recipeFoodList}">                
                    <ul>
                        <li id="detail-li"><f:IngredientTag id="${i.ingredientId}"></f:IngredientTag> <p id="textStyle" style="display: inline">${i.quantity} ${i.measurement}</p></li>
                        </ul>
                </c:forEach>
            </div>
            <c:if test="${acc.role=='User'}">
                <div class="col-md-12" >
                    <a  class="btn btn-warning" onclick="addToCart(${detailFoods.id})" role="button">Thêm vào giỏ hàng</a>
                    <a  class="btn btn-warning" href="MainController?action=addToPlan&foodId=${detailFoods.id}" role="button">Thêm vào kế hoạch của bạn</a>
                </div> 
            </c:if>
        </div>   

        <c:if test="${acc.role=='User'}">
            <%@include file="common/footer.html" %>
        </c:if>    
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assets/js/utils.js"></script>
    </body>
</html>
