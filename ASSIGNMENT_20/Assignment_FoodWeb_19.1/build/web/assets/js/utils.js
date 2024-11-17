function addToCart(foodId) {
    $.ajax({
        type: 'POST',
        url: 'AddFoodsToCartServlet',
        data: {
            foodId: foodId
        },
        success: function (response) {
            alert(response);
        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
}

function cancleOrder(orderID, status) {
    var order = document.getElementById('order-' + orderID);
    $.ajax({
        type: 'POST',
        url: 'cancleOrderServlet',
        data: {
            orderID: orderID,
            status: status
        },
        success: function (response) {
            alert(response);
            order.style.display = 'none';
        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
}
