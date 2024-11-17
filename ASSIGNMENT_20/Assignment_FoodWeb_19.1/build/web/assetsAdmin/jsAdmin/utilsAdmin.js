//Manage Order
function updateStatusOrder(orderID, status) {
    $.ajax({
        type: 'POST',
        url: 'updateStatusOrderServlet',
        data: {
            orderID: orderID,
            status: status
        },
        success: function (response) {
            alert(response);
        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
}
//Manage User
function blockUser(action, accid) {
    var btn1 = document.getElementById('button1_' + accid);
    var btn2 = document.getElementById('button2_' + accid);
    var btn3 = document.getElementById('button3_' + accid);

    $.ajax({
        type: 'POST',
        url: 'manageUserServlet',
        data: {
            active: action,
            id: accid
        },
        success: function (response) {
            if (btn1.style.display === 'none') {
                btn1.style.display = 'inline';
                btn2.style.display = 'none';
                if (btn3) {
                    btn3.style.display = action === 'block' ? 'none' : 'inline';
                }
            } else {
                btn1.style.display = 'none';
                btn2.style.display = 'inline';
                if (btn3) {
                    btn3.style.display = action === 'block' ? 'none' : 'inline';
                }
            }
        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
}

function plusAdmin(action, accid) {
    var row = document.getElementById('row_user_' + accid);
    $.ajax({
        type: 'POST',
        url: 'manageUserServlet',
        data: {
            id: accid,
            active: action
        },
        success: function (response) {
            row.style.display = 'none';
        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
}
