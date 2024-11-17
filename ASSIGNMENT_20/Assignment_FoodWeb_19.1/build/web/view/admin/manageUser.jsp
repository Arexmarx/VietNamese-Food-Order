<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <title>Quản lý Người dùng</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">
</head>
<body>
    <c:set var="msgNF" value="${requestScope.msg}"/>
    
    <f:dasboardTag></f:dasboardTag>
    <c:set var="userList" value="${requestScope.listUser}"/>
    <c:set var="acc" value="${requestScope.account}"/>
    <div class="container">
        <h1 class="text-center">Quản lý Người dùng</h1>
        <form class="form-horizontal row" action="MainControllerAdmin" method="post">
            <input type="hidden" name="action" value="manageUser">
            <div class="form-group col-sm-4">
                <label class="control-label col-sm-2" for="phone">SĐT:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="phone" name="phone" value="${requestScope.textPhone}">
                </div>
            </div>
            <div class="form-group col-sm-4">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email" value="${requestScope.textEmail}">
                </div>
            </div>
            <div class="form-group col-sm-2">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </div>
            </div>
        </form>
        <p style="font-weight: bold; color: red">${msgNF}</p>
        <c:if test="${acc == null}">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên</th>
                        <th>Email</th>
                        <th>Số Điện Thoại</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${userList}">
                        <tr id="row_user_${i.accid}">
                            <td>${i.accid}</td>
                            <td>${i.fullName}</td>
                            <td>${i.email}</td>
                            <td>${i.phone}</td>
                            <td>
                                <button id="button1_${i.accid}" class="btn btn-${i.isActive ? 'danger' : 'success'}" onclick="blockUser('${i.isActive ? 'block' : 'unBlock'}', '${i.accid}')">
                                    ${i.isActive ? 'Block' : 'Unblock'}
                                </button>
                                <button id="button2_${i.accid}" class="btn btn-${i.isActive ? 'success' : 'danger'}" style="display: none;" onclick="blockUser('${i.isActive ? 'unBlock' : 'block'}', '${i.accid}')">
                                    ${i.isActive ? 'Unblock' : 'Block'}
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${acc != null}">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên</th>
                        <th>Email</th>
                        <th>Số Điện Thoại</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <tr id="row_user_${acc.accid}">
                        <td>${acc.accid}</td>
                        <td>${acc.fullName}</td>
                        <td>${acc.email}</td>
                        <td>${acc.phone}</td>
                        <td>
                            <button id="button1_${acc.accid}" class="btn btn-${acc.isActive ? 'danger' : 'success'}" onclick="blockUser('${acc.isActive ? 'block' : 'unBlock'}', '${acc.accid}')">
                                ${acc.isActive ? 'Block' : 'Unblock'}
                            </button>
                            <button id="button2_${acc.accid}" class="btn btn-${acc.isActive ? 'success' : 'danger'}" style="display: none;" onclick="blockUser('${acc.isActive ? 'unBlock' : 'block'}', '${acc.accid}')">
                                ${acc.isActive ? 'Unblock' : 'Block'}
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/utilsAdmin.js"></script>
</body>
</html>
