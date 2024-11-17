<%-- 
    Document   : viewCart
    Created on : Jun 17, 2024, 6:59:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/cartStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <c:set var="cart" value="${sessionScope.cart}"/>
        <main>
            <c:if test="${cart.isEmpty() || cart== null}">
                <div style="text-align: center; padding: 200px auto;">
                    <img src="images/emptycart.png" height="350px" width="350px" style="margin-top: 200px; margin-bottom: 5px;">
                    <p style="font-size: 15px; font-weight: 600 ">Giỏ Hàng Của Bạn Có Vẻ Đang Trống, Hãy Lấp Đầy Nó Đi!!!</p>
                </div>
            </c:if>

            <c:if test="${!cart.isEmpty()}">
                <div class="container">
                    <div class="row ">
                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                            <c:if test="${cart != null}">
                                <div class="card-header">
                                    <h2 style="text-align: center; margin-top: 50px">Giỏ hàng của bạn</h2>
                                </div>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Sản phẩm</th>
                                            <th>Số lượng</th>
                                            <th class="text-center">Giá</th>
                                            <th class="text-center">Tổng</th>
                                            <th> </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total"  value="0" />
                                        <c:forEach var="i" items="${cart.keySet()}">
                                            <c:set var="quantity" value="${cart.get(i)}" />
                                            <c:set var="total" value="${total + quantity*i.price}" />
                                            <c:set var="totalFood" value="${quantity*i.price}"/>
                                        <form action="NotificationServlet"> 
                                            <input type="hidden" name="txtFoodId" value="${i.id}">
                                            <tr>
                                                <td class="col-sm-8 col-md-6">
                                                    <div class="media">
                                                        <a class="thumbnail pull-left" href="#"> <img class="media-object" src="${i.imgURL}" style="width: 72px; height: 72px;"> </a>
                                                        <div class="media-body">
                                                            <h4 class="media-heading"><a href="MainController?action=foodDetail&foodId=${i.id}">${i.name}</a></h4>
                                                            <h5 class="media-heading"> Nguồn gốc: ${i.origin}</h5>

                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="col-sm-1 col-md-1"><input type="number" class="form-control text-center" value="${quantity}" min="1" max="10" name="txtQuantity"></td>
                                                <td class="col-sm-1 col-md-1 text-center"><v:formatNumber value="${i.price}" type="currency" currencySymbol="VND" /></td>
                                                <td class="col-sm-1 col-md-1 text-center"><strong><v:formatNumber value="${totalFood}" type="currency" currencySymbol="VND" /></strong></td>

                                                <td class="col-sm-1 col-md-1"> 
                                                    <button onclick="confirmAction()" type="submit" value="Remove" name="btaction">
                                                        <i class="fas fa-trash-alt"></i>
                                                    </button>
                                                    <button type="submit" value="Update" name="btaction">
                                                        <i class="fa-solid fa-retweet"></i>
                                                    </button>
                                                </td> 
                                            </tr>
                                        </form>
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
                                <jsp:useBean id="d" class="java.util.Date"/>
                                <p class="text-center font-weight-semibold"> Ngày đặt hàng: <v:formatDate value="${d}" pattern="dd/MM/YYYY" /></p>
                                <div class="text-center">
                                    <a href="MainController?action=firstTime"><button type="button" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Tiếp tục mua sắm</button></a>
                                    <a href="MainController?action=checkOut&total=${total}"><button type="button" class="btn btn-lg btn-primary mt-2">Thanh toán</button></a>
                                </div>
                            </c:if>
                        </div>    
                    </div>
                </div>
            </c:if>
        </main>
        
        <%@include file="common/footer.html" %>
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
