<%-- 
    Document   : createWeeklyMenuForm
    Created on : Jul 15, 2024, 7:07:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/createFoodFormStyle.css">
    </head>
    <body>
        <div class="content">
            <h1 style="text-align: center">Thêm Thực Đơn Hàng Tuần</h1>
            <form class="form-horizontal" action="MainControllerAdmin?action=insertFoodToWeeklyMenu" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product-name" class="col-sm-2 control-label">Tên tuần lễ:</label>
                    <div class="col-sm-10">
                        <input required="true" type="text" class="form-control" name="weekLyName" placeholder="Tên tuần lễ">
                    </div>
                </div>
                <div class="form-group">
                    <label for="category-ID" class="col-sm-2 control-label">Ngày bắt đầu:</label>
                    <div class="col-sm-10">
                        <input required="true" type="date" class="form-control" name="startDate" placeholder="Ngày bắt đầu">
                    </div>
                </div>
                <div class="form-group">
                    <label for="origin" class="col-sm-2 control-label">Ngày kết thúc:</label>
                    <div class="col-sm-10">
                        <input required="true" type="date" class="form-control" name="endDate" placeholder="Ngày kết thúc">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Bước tiếp theo</button>
                    </div>
                </div>
            </form>
        </div>    
    </body>
</html>
