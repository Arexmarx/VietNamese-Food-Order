<%-- 
    Document   : homePage
    Created on : Jun 11, 2024, 12:37:19 PM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/commonStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styleHomePage.css">
    </head>

    <body>
        <%@include file="common/header.jsp" %>
        <div id="mainInfo">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <!-- Start slider -->
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner anh-dong">
                                <div class="item active">
                                    <img src="${pageContext.request.contextPath}/images/Anh_bia_1.jpg" alt="Vietnamese Cuisine">
                                </div>
                                <div class="item">
                                    <img src="${pageContext.request.contextPath}/images/Anh_bia_2.jpg" alt="Vietnamese Cuisine">
                                </div>
                                <div class="item">
                                    <img src="${pageContext.request.contextPath}/images/Anh_bia_3.jpg" alt="Vietnamese Cuisine">
                                </div>
                                <div class="item">
                                    <img src="${pageContext.request.contextPath}/images/Anh_bia_4.jpg" alt="Vietnamese Cuisine">
                                </div>
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                        <!-- End slider -->
                    </div>
                </div>

                <div class="tieu-de">
                    <span class="text-tieu-de">Danh Sách Món Ăn</span>
                </div>
                <div class="row category-section">
                    <c:forEach var="i" begin="1" end="6">
                        <div class="col-md-2">
                            <div class="category-card">
                                <a href="MainController?action=firstTime&cateID=${i}">
                                    <c:choose>
                                        <c:when test="${i == 1}">Món Nước</c:when>
                                        <c:when test="${i == 2}">Món Khô</c:when>
                                        <c:when test="${i == 3}">Món Nướng</c:when>
                                        <c:when test="${i == 4}">Món Chiên</c:when>
                                        <c:when test="${i == 5}">Các Món Khác</c:when>
                                        <c:when test="${i == 6}">Luộc Hoặc Hấp</c:when>
                                    </c:choose>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <c:set var="list" value="${requestScope.ListFoods}" />
                    <c:if test="${list != null}">
                        <div class="food">
                            <c:forEach var="i" items="${list}">
                                <f:food idFood="${i.id}" />
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
            <c:if test="${requestScope.msgThanks != null}">
                <script>
                    alert('${requestScope.msgThanks}');
                </script>
            </c:if>
            <%@include file="common/footer.html" %>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/js/notifications.js"></script>
            <script src="${pageContext.request.contextPath}/assets/js/utils.js"></script>
    </body>
</html>
