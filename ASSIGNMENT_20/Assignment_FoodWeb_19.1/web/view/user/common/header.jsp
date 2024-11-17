<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class=" navbar navbar-default">
    <div class="container-fluid row">
        <div class="task col-sm-4">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/MainController?action=firstTime" 
                       style="font-weight: bolder; font-size: 20px; padding-top: 2px;padding-bottom: 2px">                       
                        <img width="50px" height="50px" src="<%= request.getContextPath()%>/images/logo_no_background.png">
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/MainController?action=weeklyMenu">Thực đơn hàng tuần</a></li>
                <li><a href="${pageContext.request.contextPath}/MainController?action=myPlan">Kế hoạch của bạn</a></li>
            </ul>
        </div>


        <div class="search col-sm-4 navbar-left">
            <form id="searchForm" action="MainController" method="GET" class="input-group">
                <input type="search" class="form-control" placeholder="Tìm kiếm" aria-label="Search" aria-describedby="search-addon" 
                       name="txtSearch" value="${requestScope.searchQuery}"/>
                <input type="hidden" id="dataSearch" name="dataSearch" value=""/>
                <input type="hidden" name="action" value="searchName"/>
                <div class="input-group-btn">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" 
                                aria-haspopup="true" aria-expanded="false" style="height: 35px">
                            <i class="fas fa-search"></i>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li><a href="#" class="dropdown-item" onclick="submitForm(1)">Tìm món ăn</a></li>
                            <li><a href="#" class="dropdown-item" onclick="submitForm(2)">Tìm thực đơn hàng tuần</a></li>
                        </ul>
                    </div>
                </div>
            </form>
        </div>

        <script>
            function submitForm(value) {
                document.getElementById('dataSearch').value = value;
                document.getElementById('searchForm').submit();
            }
        </script>



        <c:set var="acc" value="${sessionScope.LoginObj}"/>
        <c:if test="${acc == null}">
            <ul class="col-sm-4 nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/MainController?action=login" id="cart-btn"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <li><a href="${pageContext.request.contextPath}/MainController?action=login"><i class="fa-solid fa-user"></i> Đăng nhập </a></li>
                <li><a href="${pageContext.request.contextPath}/MainController?action=register"><i class="fa-solid fa-right-to-bracket"></i> Đăng kí </a></li>
            </ul>
        </c:if>
        <c:if test="${acc != null}">
            <ul class="col-sm-4 nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/MainController?action=viewCart"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Chào,${acc.userName}
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/MainController?action=showInfor">Thông tin của bạn</a></li>
                        <li><a href="${pageContext.request.contextPath}/MainController?action=historyOrder">Lịch sử mua hàng</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/MainController?action=logout">Đăng xuất</a></li>
            </ul>
        </c:if>
    </div>
</nav>