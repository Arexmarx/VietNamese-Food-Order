<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tagLIB.tld" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="v"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Sản phẩm</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/adminHomePageStyle.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/assetsAdmin/cssAdmin/createFoodFormStyle.css">
    </head>
    <body>
        <c:set var="listCategory" value="${requestScope.AllCategory}"/>
        <div class="content">
            <h1 style="text-align: center">Thêm Sản phẩm</h1>
            <form style="margin-left: 10%" class="form-horizontal col-sm-6" action="MainControllerAdmin?action=createFood" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product-name" class="col-sm-2 control-label">Tên sản phẩm:</label>
                    <div class="col-sm-10">
                        <input required="true" type="text" class="form-control" name="foodName" placeholder="Tên sản phẩm">
                    </div>
                </div>
                <div class="form-group">
                    <label for="category-ID" class="col-sm-2 control-label">ID danh mục:</label>
                    <div class="col-sm-10">
                        <input required="true" type="number" min="1" class="form-control" name="foodCategory" placeholder="ID danh mục">
                    </div>
                </div>
                <div class="form-group">
                    <label for="origin" class="col-sm-2 control-label">Xuất xứ:</label>
                    <div class="col-sm-10">
                        <input required="true" type="text" class="form-control" name="foodOrigin" placeholder="Xuất xứ">
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-2 control-label">Giá tiền:</label>
                    <div class="col-sm-10">
                        <input required="true" type="number" class="form-control" name="foodPrice" placeholder="Giá tiền">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">Mô tả:</label>
                    <div class="col-sm-10">
                        <textarea required="true" class="form-control" id="description" name="description" rows="4" placeholder="Mô tả sản phẩm"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="wayCooking" class="col-sm-2 control-label">Cách làm:</label>
                    <div class="col-sm-10">
                        <textarea required="true" class="form-control" id="wayCooking" name="wayCooking" rows="4" placeholder="Cách làm"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="file" class="col-sm-2 control-label">Chọn file:</label>
                    <div class="col-sm-10">
                        <input required="true" class="form-control" type="file" accept=".jpg, .png" id="file" name="file" onchange="previewImage(event)">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-2">
                        <img id="preview" src="#" alt="Image preview" style="display:none; width: 200px; height: auto;" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Bước tiếp theo</button>
                    </div>
                </div>
            </form>
            <div class="col-sm-4">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên danh mục</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="i" items="${listCategory}">
                            <tr>
                                <td>${i.catagoryId}</td>
                                <td>${i.catagoryName}</td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/assetsAdmin/jsAdmin/utilsAdmin.js"></script>
    </body>
</html>
