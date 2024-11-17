document.getElementById('cart-btn').addEventListener('click', myFunction);

function myFunction() {
  alert ('Must Be Login To Access The Cart');
}
    
function confirmAction() {
    var confirmed = window.confirm("Are you sure you want to delete this Food?");
    if (!confirmed) {
        var buttons = document.getElementsByName('btaction');
        buttons.forEach(function(button) {
            if (button.value === 'Remove') {
                button.value = 'Update';
            }
        });
        return false;
    } else {
        alert("Done !!!");
        return true;
    }
}

