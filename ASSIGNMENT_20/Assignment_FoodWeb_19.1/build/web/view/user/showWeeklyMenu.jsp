<%-- 
    Document   : showWeeklyMenu
    Created on : Jun 26, 2024, 9:36:11 PM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v" %>
<%@ taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/weeklyMenuStyle.css">
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <c:set var="list" value="${requestScope.MenuItems}"/>
        <c:set var="menu" value="${requestScope.MenuPresent}"/>
        <c:set var="nextMenu" value="${requestScope.NextMenu}"/>
        <c:set var="notify" value="${requestScope.NotifyEmpty}"/>
        <div id="page">
            <c:if test="${menu!= null && nextMenu!= null}">
                <div id="frame">
                    <c:forEach var="i" items="${nextMenu}">
                        <a class="menu-date" href="${pageContext.request.contextPath}/MainController?action=weeklyMenu&menuID=${i.id}">
                            <v:formatDate value="${i.startDate}" pattern="dd/MM" />-
                            <v:formatDate value="${i.endDate}" pattern="dd/MM" />
                        </a>
                    </c:forEach>
                </div>
                <div class="menu-name-date">
                    <div class="menu-name">${menu.name}
                        <c:if test="${list.size()>=7}">
                            <a style="font-size: 12px; border-radius: 20px;" class="btn btn-warning"
                               href="MainController?action=addToPlan&weeklyID=${menu.id}">+Kế Hoạch</a>
                        </c:if>
                    </div>
                    <div class="menu-date-time"> 
                        <v:formatDate value="${menu.startDate}" pattern="dd/MM" />-
                        <v:formatDate value="${menu.endDate}" pattern="dd/MM" />
                    </div>
                </div>
            </c:if>
            <c:if test="${list.size() >0}">
                <div class="container">
                    <div class="row slide-show">
                        <div class="col-xs-11 col-md-10 col-centered">
                            <div id="carousel" class="carousel slide" data-ride="carousel" data-type="multi" data-interval="2500">
                                <div class="carousel-inner">
                                    <c:forEach var="i" items="${list}" varStatus="status"> 
                                        <div class="item ${status.index == 0 ? 'active' : ''}">
                                            <div class="carousel-col">
                                                <f:food idFood="${i.foodID}">
                                                </f:food>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!-- Controls -->
                                <div class="left carousel-control">
                                    <a href="#carousel" role="button" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                        <span class="sr-only">Trước </span>
                                    </a>
                                </div>
                                <div class="right carousel-control">
                                    <a href="#carousel" role="button" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                        <span class="sr-only">Sau</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${list.isEmpty()}">
                <div id="empty-list">
                    <i id="logo" class="fa-solid fa-utensils"></i>
                    <h4>Thực đơn đang được chế biến!!! </h4>
                </div>
            </c:if>
        </div>
        <%@include file="common/footer.html" %>
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assets/js/weeklyMenuSlide.js"></script>
        <script src="<%= request.getContextPath()%>/assets/js/utils.js"></script>
    </body>
</html>
