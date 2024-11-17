<%-- 
    Document   : editPlanPage
    Created on : Jul 4, 2024, 3:49:25 PM
    Author     : Nguyễn Nhật Trường
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Plan Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/commonStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/editPlanPageStyle.css">
    </head>
    <body>
        <%@include file="common/header.jsp" %>

        <c:set var="ListFoodPlan" value="${sessionScope.FoodListPlan}"/>
        <c:set var="WeeklyListPlan" value="${sessionScope.WeeklyListPlan}"/>
        <c:set var="PlanPresent" value="${requestScope.PlanPresent}"/>
        <c:set var="ListFoodInWeek" value="${requestScope.ListFoodInWeek}"/>
        <c:set var="ListItemWeeklyMenu" value="${requestScope.ListItemWeeklyMenu}"/>
        <c:set var="FlagWeek" value="${requestScope.FlagWeek}"/>

        <main class="container-fluid" style="min-height: 800px; margin-top: 70px;">
            <div class="row">
                <div class="col-md-6" style="padding: 0 100px; font-size: 15px;">
                    <table class="table table-sm">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên món ăn</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${ListFoodPlan.size() > 0}">
                                <c:forEach var="i" items="${ListFoodPlan.values()}"> 
                                    <tr>
                                        <td>${i.id}</td>
                                        <td><a href="MainController?action=addToPlan&foodId=${i.id}">${i.name}</a></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-6" style="padding: 0 150px; font-size: 20px;">
                    <table class="table table-sm">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên tuần lễ</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${WeeklyListPlan.size() > 0}">
                                <c:forEach var="i" items="${WeeklyListPlan.values()}"> 
                                    <tr>
                                        <td>${i.id}</td>
                                        <td>${i.name}</td>
                                        <td><a href="MainController?action=weeklyMenu&menuID=${i.id}">Xem chi tiết</a></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <c:set var="countWeek" value="${PlanPresent.weeklyNumber}" />
            <div class="row">
                <c:forEach begin="1" end="${countWeek}" varStatus="loop">
                    <c:set var="isNumberInList" value="false" />
                    <c:forEach var="x" items="${FlagWeek}">
                        <c:if test="${loop.index==x}">
                            <c:set var="isNumberInList" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${isNumberInList==false}">
                        <div class="container col-md-${countWeek==1?"12":(countWeek==2?"6":(countWeek==3?"4":"3"))}"
                             style="${countWeek==1?"padding: 0 20%;":""}">
                            <h2 class="text-center">Tuần ${loop.index}
                                <c:if test="${ListFoodInWeek[loop.index]== null}"><div class="dropdown" style="display: inline">
                                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                                            Thêm thực đơn hàng tuần
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li>
                                                <div class="dropdown-form" style="padding: 15px; min-width: 250px;">
                                                    <form action="MainController?action=optionsEdit" method="post">

                                                        <div class="form-group">
                                                            <label>Nhập ID thực đơn hàng tuần:</label>
                                                            <input type="text" class="form-control" name="WeeklyMenuID" required>
                                                        </div>

                                                        <input type="hidden" name="planID" value="${PlanPresent.id}">
                                                        <input type="hidden" name="ActionEdit" value="addWeeklyMenu">
                                                        <input type="hidden" name="weekIndex" value="${loop.index}">
                                                        <button type="submit" class="btn btn-success">Create</button>
                                                    </form>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>
                            </h2>
                            <table id="mealPlanTable" class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Ngày</th>
                                        <th>Tên món</th>
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
                                                            <f:GetNameTag id="${i.foodID}" >
                                                            </f:GetNameTag>
                                                            <span>
                                                                <form action="MainController?action=optionsEdit" method="post" style="display: inline">
                                                                    <input type="hidden" name="PersonalFoodID" value="${i.id}">
                                                                    <input type="hidden" name="planID" value="${PlanPresent.id}">
                                                                    <input type="hidden" name="ActionEdit" value="deleteFood">
                                                                    <button type="submit" class="btn btn-warning button-option">
                                                                        Xóa
                                                                    </button>
                                                                </form>
                                                            </span>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <div class="dropdown">
                                                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                                                        Thêm thức ăn
                                                        <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li>
                                                            <div class="dropdown-form" style="padding: 15px; min-width: 250px;">
                                                                <form action="MainController?action=optionsEdit" method="post">
                                                                    <div class="form-group">
                                                                        <label>Nhập ID món ăn:</label>
                                                                        <input type="text" class="form-control" name="PersonalFoodID" required>
                                                                    </div>
                                                                    <input type="hidden" name="planID" value="${PlanPresent.id}">
                                                                    <input type="hidden" name="ActionEdit" value="addFood">
                                                                    <input type="hidden" name="weekDay" value="${status.index}">
                                                                    <input type="hidden" name="weekIndex" value="${loop.index}">
                                                                    <button type="submit" class="btn btn-success">Create</button>
                                                                </form>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>

                    <c:if test="${isNumberInList==true}">
                        <c:set var="z" value="${ListItemWeeklyMenu[loop.index]}"/>
                        <div class="container col-md-${countWeek==1?"12":(countWeek==2?"6":(countWeek==3?"4":"3"))}"
                             style="${countWeek==1?"padding: 0 20%;":""}">
                            <h2 class="text-center">Tuần ${loop.index}
                                <span>
                                    <form action="MainController?action=optionsEdit" method="post" style="display: inline">
                                        <input type="hidden" name="WeeklyMenuID" value="${z[0].weekID}">
                                        <input type="hidden" name="planID" value="${PlanPresent.id}">
                                        <input type="hidden" name="weekIndex" value="${loop.index}">
                                        <input type="hidden" name="ActionEdit" value="deleteWeeklyMenu">
                                        <button type="submit" class="btn btn-warning button-option">
                                            Xóa
                                        </button>
                                    </form>
                                </span>
                            </h2>
                            <table id="mealPlanTable" class="table table-bordered">
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
                                                            <f:GetNameTag id="${z[status.index].foodID}" >
                                                            </f:GetNameTag>
                                                        </div>
                                                        <c:if test="${status.index + 6 < z.size()}">
                                                            <div>
                                                                <f:GetNameTag id="${z[status.index + 6].foodID}" >
                                                                </f:GetNameTag>
                                                            </div>
                                                        </c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div>
                                                            <f:GetNameTag id="${z[status.index].foodID}" >
                                                            </f:GetNameTag>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </c:forEach>
            </div> 
        </main>
        <c:set var="msg" value="${requestScope.msg}" />
        <c:if test="${msg!= null}">
            <script>
                window.alert('${msg}');
            </script>
        </c:if>
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>