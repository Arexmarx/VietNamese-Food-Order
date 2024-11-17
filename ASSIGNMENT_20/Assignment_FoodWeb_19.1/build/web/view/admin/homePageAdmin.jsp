<%-- 
    Document   : homePageAdmin
    Created on : Jul 6, 2024, 5:15:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminContentStyle.css">
    </head>
    <body>
        <f:dasboardTag></f:dasboardTag>
        <c:set var="acc" value="${sessionScope.LoginObj}"/>
        <div class="container">
            <div class="row">
                <div class="text-center admin">
                    <i class="fa-solid fa-user-tie fa-10x"></i>
                    <h1>${acc.userName}</h1>      
                </div>
                <div class="container">
                    <div class="chart-container">
                        <div class="chart-title">Biểu Đồ Mua Hàng</div>
                        <canvas id="purchaseChart"></canvas>
                    </div>
                    <div class="chart-container">
                        <div class="chart-title">Tổng Tiền Trong Tháng</div>
                        <canvas id="totalPriceChart"></canvas>
                    </div>
                </div>


            </div>    
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            $(document).ready(function () {
                // Biểu đồ Mua Hàng
                $.ajax({
                    url: 'manageChartOrderServlet',
                    method: 'GET',
                    success: function (data) {
                        var orderData = data.split(',').map(Number);

                        var ctx = document.getElementById('purchaseChart').getContext('2d');
                        var purchaseChart = new Chart(ctx, {
                            type: 'bar',
                            data: {
                                labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                                datasets: [{
                                        label: 'Mua Hàng',
                                        data: orderData,
                                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                        borderColor: 'rgba(54, 162, 235, 1)',
                                        borderWidth: 1
                                    }]
                            },
                            options: {
                                scales: {
                                    yAxes: [{
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                }
                            }
                        });
                    }
                });

                // Biểu đồ Tổng Tiền
                $.ajax({
                    url: 'managaTotalPriceInMonthServlet',
                    method: 'GET',
                    success: function (data) {
                        var totalPriceData = data.split(',').map(Number);

                        var ctx2 = document.getElementById('totalPriceChart').getContext('2d');
                        var totalPriceChart = new Chart(ctx2, {
                            type: 'bar',
                            data: {
                                labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                                datasets: [{
                                        label: 'Tổng Tiền',
                                        data: totalPriceData,
                                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                                        borderColor: 'rgba(255, 99, 132, 1)',
                                        borderWidth: 1
                                    }]
                            },
                            options: {
                                scales: {
                                    yAxes: [{
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                }
                            }
                        });
                    }
                });
            });

        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>



