<%-- 
    Document   : viewSearch
    Created on : Jul 1, 2024, 5:25:50 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/styleHomePage.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/weeklyMenuStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/viewSearchStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="common/header.jsp" %>

        <c:set var="list" value="${requestScope.SearchFood}"/> 
        <c:set var="weeklyList" value="${requestScope.WeeklyList}"/>
        <c:set var="weeklyItemList" value="${requestScope.WeeklyItemList}"/>
        <c:set var="notify" value="${requestScope.NotifyEmpty}"/>
        <c:if test="${list != null}">
            <div style="min-height: 800px; margin-top: 100px;">
                <div class="tieu-de"><span class="text-tieu-de">Món Ăn </span></div> 
                <div class="food">
                    <c:forEach var="i" items="${list}">
                        <div class="style-food">
                            <f:food idFood="${i.id}"></f:food>
                            </div>    
                    </c:forEach>
                </div>
            </div>
        </c:if>
        <c:if test="${list.isEmpty()}">
            <p style="text-align: center;font-weight: bold; margin-top: 100px">Có vẻ món ăn bạn cần tìm chúng tôi chưa có</p>
        </c:if>

        <c:if test="${weeklyList != null}">
            <c:forEach var="i" items="${weeklyItemList}" varStatus="loopStatus">
                <c:if test="${i.value.size() > 0}">
                    <div class="block-week">
                        <h1 id="menu-name" style="margin-top: 60px; text-align: center">${i.key.name} 
                            <v:formatDate value="${i.key.startDate}" pattern="dd/MM" />-
                            <v:formatDate value="${i.key.endDate}" pattern="dd/MM" />
                            <a href="${pageContext.request.contextPath}/MainController?action=weeklyMenu&menuID=${i.key.id}">Chi tiết</a>
                        </h1>
                        <div class="container">
                            <div class="row slide-show">
                                <div class="col-xs-11 col-md-10 col-centered">
                                    <div id="carousel-${loopStatus.index}" class="carousel slide" data-ride="carousel" data-type="multi" data-interval="2500">
                                        <div class="carousel-inner">
                                            <c:forEach var="a" items="${i.value}" varStatus="status">                                             
                                                <div class="item ${status.index == 0 ? 'active' : ''}">
                                                    <div class="carousel-col">
                                                        <f:food idFood="${a.foodID}"></f:food>
                                                        </div>
                                                    </div> 
                                            </c:forEach>

                                        </div>
                                        <!-- Controls -->
                                        <div class="left carousel-control">
                                            <a href="#carousel-${loopStatus.index}" role="button" data-slide="prev">
                                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </div>
                                        <div class="right carousel-control">
                                            <a href="#carousel-${loopStatus.index}" role="button" data-slide="next">
                                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:if>
                <c:if test="${i.value.size() == 0}">
                    <div class="block-week">
                        <h1 id="menu-name" style="margin-top: 60px;text-align: center">${i.key.name} 
                            <v:formatDate value="${i.key.startDate}" pattern="dd/MM" />-
                            <v:formatDate value="${i.key.endDate}" pattern="dd/MM" />
                        </h1>
                        <div id="empty-list">
                            <i id="logo" class="fa-solid fa-utensils"></i>
                            <p>Thực Đơn Đang Được Chế Biến!!!</p>
                        </div>
                    </div>
                </c:if>    
            </c:forEach>
        </c:if>
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assets/js/weeklyMenuSlide.js"></script>
        <script src="<%= request.getContextPath()%>/assets/js/utils.js"></script>
    </body>
</html>
