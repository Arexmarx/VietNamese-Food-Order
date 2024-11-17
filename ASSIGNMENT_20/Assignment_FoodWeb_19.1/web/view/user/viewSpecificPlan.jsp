<%-- 
    Document   : viewSpecificPlan
    Created on : Jul 8, 2024, 9:41:57 AM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/showPlanStyle.css">
        
    </head>
    <body>
        <%@include file="common/header.jsp" %> 
        <c:set var="ListFoodPlan" value="${sessionScope.FoodListPlan}"/>
        <c:set var="WeeklyListPlan" value="${sessionScope.WeeklyListPlan}"/>
        <c:set var="PlanPresent" value="${requestScope.PlanPresent}"/>
        <c:set var="ListFoodInWeek" value="${requestScope.ListFoodInWeek}"/>
        <c:set var="ListItemWeeklyMenu" value="${requestScope.ListItemWeeklyMenu}"/>
        <c:set var="FlagWeek" value="${requestScope.FlagWeek}"/>
        
        <c:set var="countWeek" value="${PlanPresent.weeklyNumber}" />
        <div class="container" style="margin-top: 100px; min-height: 600px;">
            <div class="row">
                <c:forEach begin="1" end="${PlanPresent.weeklyNumber}" varStatus="loop">
                    <c:set var="isNumberInList" value="false" />
                    <c:forEach var="x" items="${FlagWeek}">
                        <c:if test="${loop.index == x}">
                            <c:set var="isNumberInList" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${isNumberInList == false}">
                        <div class="col-md-${countWeek==1?"12":(countWeek==2?"6":(countWeek==3?"4":"6"))}">
                            <div class="panel panel-default">
                                <div class="panel-heading text-center">
                                    <h2>Tuần ${loop.index}</h2>
                                </div>
                                <div class="panel-body">
                                    <table id="mealPlanTable" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Ngày</th>
                                                <th>Tên món ăn</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach begin="2" end="7" varStatus="status">
                                                <tr>
                                                    <td>Thứ ${status.index}</td>
                                                    <td>
                                                        <c:forEach var="i" items="${ListFoodInWeek[loop.index]}">
                                                            <c:if test="${i.weekDay == status.index}">
                                                                <div>
                                                                    <f:GetNameTag id="${i.foodID}" />
                                                                </div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${isNumberInList == true}">
                        <c:set var="z" value="${ListItemWeeklyMenu[loop.index]}" />
                        <div class="col-md-${countWeek==1?"12":(countWeek==2?"6":(countWeek==3?"4":"6"))}">
                            <div class="panel panel-default">
                                <div class="panel-heading text-center">
                                    <h2>Tuần ${loop.index}</h2>
                                </div>
                                <div class="panel-body">
                                    <table id="mealPlanTable" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Ngày</th>
                                                <th>Tên món ăn</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach begin="0" end="5" varStatus="status">
                                                <tr>
                                                    <td>Thứ ${status.index + 2}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${z.size() > 7}">
                                                                <div>
                                                                    <f:GetNameTag id="${z[status.index].foodID}" />
                                                                </div>
                                                                <c:if test="${status.index + 6 < z.size()}">
                                                                    <div>
                                                                        <f:GetNameTag id="${z[status.index + 6].foodID}" />
                                                                    </div>
                                                                </c:if>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div>
                                                                    <f:GetNameTag id="${z[status.index].foodID}" />
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <%@include file="common/footer.html" %>
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>