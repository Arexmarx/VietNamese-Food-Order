setTimeout(function () {
    var errElements = document.getElementById('alert');
    errElements.style.display = 'none';
}, 2000);
function setAvailable(ingreId, setAvai) {
    var btn1 = document.getElementById('button1_' + ingreId);
    var btn2 = document.getElementById('button2_' + ingreId);

    $.ajax({
        type: 'POST',
        url: 'manageIngredientAllServlet',
        data: {
            option: 'setAvailable',
            ingreId: ingreId,
            setAvai: setAvai

        },
        success: function (response) {
            if (btn1.style.display === 'none') {
                btn1.style.display = 'inline';
                btn2.style.display = 'none';

            } else {
                btn1.style.display = 'none';
                btn2.style.display = 'inline';

            }
        },
        error: function (xhr, status, error) {
            alert('Đã xảy ra lỗi: ' + error);
        }
    });
}


