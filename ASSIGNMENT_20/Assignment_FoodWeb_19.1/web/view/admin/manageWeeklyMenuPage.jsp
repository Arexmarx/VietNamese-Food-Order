<%-- 
    Document   : manageMenu
    Created on : Jul 6, 2024, 7:09:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Weekly Menu</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">

    </head>
    <body>
        <f:dasboardTag></f:dasboardTag>
        <c:set var="msgSuccess" value="${requestScope.msgSuccess}"/>
        <c:set var="list" value="${requestScope.weeklyList}"/>
        <div class="" style="padding: 1% 1% 1% 15%">
            <div class="content">
                <h1 style="text-align: center">Quản lí thực đơn món ăn</h1>
                <form action="MainControllerAdmin?action=manageWeeklyMenu" class="form-horizontal" method="POST">
                    <div class="form-group col-sm-10">
                        <label for="product-name" class="col-sm-2 control-label">Tên thực đơn</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="findWeek" value="${weekName}" placeholder="Tên thực đơn">
                        </div>
                    </div>
                    <div class="form-group col-sm-1">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                        </div>
                    </div>    
                </form>
                <span class="col-sm-1" style="margin-left: 10px">
                    <a href="MainControllerAdmin?action=insertWeeklyMenu" class="btn btn-warning">Tạo thực đơn</a>
                </span>

                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Mã món ăn</th>
                            <th>Tên thực đơn hàng tuần</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="i" items="${list}">
                            <tr>
                                <td>${i.id}</td>
                                <td id="weekName_${i.id}">${i.name}</td>
                                <td id="startDate_${i.id}"><v:formatDate value="${i.startDate}" pattern="dd/MM/yyyy" /></td>
                                <td id="endDate_${i.id}"><v:formatDate value="${i.endDate}" pattern="dd/MM/yyyy" /></td>
                                <td style="text-align: center">
                                    <a href="MainControllerAdmin?action=getWeeklyMenuDetail&weeklyId=${i.id}" class="btn btn-info">Chi tiết</a>
                                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#updateDateModal" data-id="${i.id}" data-name="${i.name}" data-startDate="${i.startDate}" data-endDate="${i.endDate}">Cập Nhật</button>
                                </td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Update Date Modal -->
        <div id="updateDateModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cập Nhật Thực đơn hàng tuần</h4>
                    </div>
                    <div class="modal-body">
                        <form id="updateDateForm" method="POST">
                            <input type="hidden" name="weeklyId" id="modalWeeklyId">
                            <div class="form-group">
                                <label for="modalWeeklyName">Tên Thực Đơn</label>
                                <input type="text" class="form-control" id="modalWeeklyName" name="weekName" required>
                            </div>
                            <div class="form-group">
                                <label for="modalStartDate">Ngày bắt đầu</label>
                                <input type="date" class="form-control" id="modalStartDate" name="startDate" required>
                            </div>
                            <div class="form-group">
                                <label for="modalEndDate">Ngày kết thúc</label>
                                <input type="date" class="form-control" id="modalEndDate" name="endDate" required>
                            </div>                            
                        </form>
                        <button onclick="updateWeeklyMenu()" class="btn btn-primary">Cập Nhật</button>
                        <a id="updateFoodLink" onclick="updateWeeklyMenu()" href="#" class="btn btn-info">Cập Nhật món ăn</a>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#updateDateModal').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var id = button.data('id');
                    var name = button.data('name');
                    var startDate = button.data('startdate');
                    var endDate = button.data('enddate');

                    var modal = $(this);
                    modal.find('#modalWeeklyId').val(id);
                    modal.find('#modalWeeklyName').val(name);
                    modal.find('#modalStartDate').val(startDate);
                    modal.find('#modalEndDate').val(endDate);

                    $('#updateFoodLink').attr('href', 'MainControllerAdmin?action=manageFoodWeeklyMenu&option=formUpdateFood&weekId=' + id);
                });
            });

            function updateWeeklyMenu() {
                var weeklyId = $('#modalWeeklyId').val();
                var weekName = $('#modalWeeklyName').val();

                var startDate = $('#modalStartDate').val();
                var endDate = $('#modalEndDate').val();

                var data = {
                    weeklyId: weeklyId,
                    weekName: weekName
                };

                if (startDate) {
                    data.startDate = startDate;
                }

                if (endDate) {
                    data.endDate = endDate;
                }

                $.ajax({
                    type: 'POST',
                    url: 'updateWeeklyMenuServlet',
                    data: data,
                    success: function (response) {
                        if (startDate) {
                            var formattedStartDate = moment(startDate).format('DD/MM/YYYY');
                            $('#startDate_' + weeklyId).text(formattedStartDate);
                        }
                        if (endDate) {
                            var formattedEndDate = moment(endDate).format('DD/MM/YYYY');
                            $('#endDate_' + weeklyId).text(formattedEndDate);
                        }
                        $('#weekName_' + weeklyId).text(weekName);
                        $('#updateDateModal').modal('hide');
                    },
                    error: function (xhr, status, error) {
                        alert('Đã xảy ra lỗi: ' + error);
                    }
                });
            }
        </script>

        <c:if test="${msgSuccess != null && !msgSuccess.isEmpty()}">
            <script>
                window.alert('${msgSuccess}');
            </script>
        </c:if> 
        <c:if test="${msgUpdate != null && !msgUpdate.isEmpty()}">
            <script>
                window.alert('${msgUpdate}');
            </script>
        </c:if>    
    </body>
    </
