//ManageMenu
$(document).ready(function () {
    $('#updatePriceModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var id = button.data('id');
        var name = button.data('name');
        var price = button.data('price');
        var origin = button.data('origin');

        var modal = $(this);
        modal.find('#modalFoodId').val(id);
        modal.find('#modalFoodName').val(name);
        modal.find('#modalFoodPrice').val(price);
        modal.find('#modalFoodOrigin').val(origin);
        
        $('#updateRecipeLink').attr('href', 'MainControllerAdmin?action=updateRecipe&foodId=' + id);
    });
});

function updateFood() {
    var foodId = $('#modalFoodId').val();
    var foodName = $('#modalFoodName').val();
    var price = $('#modalFoodPrice').val();
    var origin = $('#modalFoodOrigin').val();

    $.ajax({
        type: 'POST',
        url: 'updateFoodServlet',
        data: {
            foodId: foodId,
            foodName: foodName,
            price: price,
            origin: origin
        },
        success: function (response) {
            var formattedPrice = new Intl.NumberFormat().format(price) + ' VND';
            
            $('#name_' + foodId).text(foodName);
            $('#price_' + foodId).text(formattedPrice);
            $('#origin_' + foodId).text(origin);
            $('#updatePriceModal').modal('hide');

        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
}

function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('preview');
        output.src = reader.result;
        output.style.display = 'block';
    };
    reader.readAsDataURL(event.target.files[0]);
}

function addIngredient(ingredientId, measurement) {
    var quantity = document.getElementById('quantity-' + ingredientId).value;
    $.ajax({
        type: 'POST',
        url: 'addToRecipeServlet',
        data: {
            ingredientId: ingredientId,
            measurement: measurement,
            quantity: quantity
        },
        success: function (response) {
            alert('Thêm nguyên liệu thành công!');
        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
    return false;
}