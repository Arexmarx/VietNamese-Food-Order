<%-- 
    Document   : showPlan
    Created on : Jul 3, 2024, 12:48:50 PM
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
        <c:set var="listPlan" value="${sessionScope.ListPlanUser}"></c:set>
            <main>
                <div>
                    <h2>Danh Sách Kế Hoạch Của Bạn</h2>
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                            Tạo kế hoạch của bạn
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="dropdown-form" style="padding: 15px; min-width: 250px;">
                                    <form action="${pageContext.request.contextPath}/MainController?action=createPlan" method="post">
                                        <div class="form-group">
                                            <label>Tên Kế Hoạch:</label>
                                            <input type="text" class="form-control" name="namePlan" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="pwd">Số Tuần Dự Kiến:</label>
                                            <input type="number" class="form-control" name="numWeek" min="1" max="4"required>
                                        </div>
                                        <button type="submit" class="btn btn-success">Create</button>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <table class="list-plan table table-striped">
                        <thead>
                            <tr>
                                <th>Thứ Tự</th>
                                <th>Tên Kế Hoạch</th>
                                <th>Số Tuần</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${listPlan}" varStatus="status">
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${i.name}</td>
                                <td>${i.weeklyNumber}</td>
                                <td>
                                    <a class=" create btn btn-success" href="${pageContext.request.contextPath}/MainController?action=editPlan&planID=${i.id}&optionView=view">
                                        <i class="fa-solid fa-eye"></i></a>
                                    <a class=" create btn btn-success" href="${pageContext.request.contextPath}/MainController?action=editPlan&planID=${i.id}">
                                        <i class="fa-solid fa-wrench"></i></a>
                                    <a class=" create btn btn-success" href="${pageContext.request.contextPath}/MainController?action=deletePlan&planID=${i.id}">
                                        <i class="fa-solid fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </main>
        <%@include file="common/footer.html" %>
        <script src="<%= request.getContextPath()%>/assets/js/notifications.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
