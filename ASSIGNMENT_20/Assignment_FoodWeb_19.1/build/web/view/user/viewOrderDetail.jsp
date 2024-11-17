<%-- 
    Document   : viewOrderDetail
    Created on : Jul 5, 2024, 10:16:14 AM
    Author     : Admin
--%>

<%@page import="dao.FoodDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
    <c:set var="acc" value="${sessionScope.LoginObj}"/> 
    <c:if test="${acc.role=='User'}">
        <%@include file="common/header.jsp" %>  
    </c:if>
    <c:set var="listDetail" value="${requestScope.listDetailOrderHistory}"/>
    <c:set var="listFoodDetail" value="${requestScope.listFoodDetailOrder}"/>
    <c:set var="orderAddress" value="${requestScope.OrderAddress}"/>
    <c:set var="OrderObj" value="${requestScope.OrderObj}"/>
    <c:if test="${!listDetail.isEmpty()}">
        <div class="container" style="min-height: 700px;">
            <div class="row ">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <c:if test="${listDetail != null}">
                        <div class="card-header">
                            <h2 style="text-align: center; margin-top: 70px">Chi tiết đơn hàng</h2>
                        </div>
                        <div>
                            <h3>Địa chỉ nhận hàng</h3>
                            <h4>${OrderObj.cusName}</h4>
                            <h5>${OrderObj.phone}</h5>
                            <p style="font-size: large">${orderAddress.note},${orderAddress.ward},${orderAddress.district},${orderAddress.province}</p>
                        </div>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Số lượng</th>
                                    <th class="text-center">Giá</th>
                                    <th class="text-center">Tổng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="0"/>
                            <c:forEach var="i" items="${listDetail}" varStatus="status">
                                <c:set var="quantity" value="${i.quantity}" />
                                <c:set var="totalFood" value="${quantity*i.price}"/> 
                                <c:set var="total" value="${total + totalFood}"/>
                                <tr>
                                    <td class="col-sm-8 col-md-6">
                                        <div class="media">
                                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="${listFoodDetail.get(status.index).imgURL}" style="width: 72px; height: 72px;"> </a>
                                            <div class="media-body">
                                                <h4 class="media-heading"><a href="MainController?action=foodDetail&foodId=${i.foodId}">${listFoodDetail.get(status.index).name}</a></h4>
                                                <h5 class="media-heading"> Nguồn gốc:${listFoodDetail.get(status.index).origin} </h5>

                                            </div>
                                        </div>
                                    </td>
                                    <td class="col-sm-1 col-md-1"><input disabled="true" type="number" class="form-control text-center" value="${quantity}" min="1" max="10" name="txtQuantity"></td>
                                    <td class="col-sm-1 col-md-1 text-center"><v:formatNumber value="${i.price}" type="currency" currencySymbol="VND" /></td>
                                    <td class="col-sm-1 col-md-1 text-center"><strong><v:formatNumber value="${totalFood}" type="currency" currencySymbol="VND" /></strong></td>
                                </tr>     
                                    
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="align-items-center">
                            <div>
                                <div class="text-center">
                                    <label class="text-center"><h2>Tổng tiền</h2></label>
                                    <h2 class="text-large"><strong><v:formatNumber value="${total}" type="currency" currencySymbol="VND" /></strong></h2>

                                </div>
                            </div>
                        </div>

                    </c:if>
                </div>    
            </div>
        </div>
    </c:if>
    <c:if test="${acc.role=='User'}">
        <%@include file="common/footer.html" %>    
    </c:if>
    <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>   
    </body>
</html>
