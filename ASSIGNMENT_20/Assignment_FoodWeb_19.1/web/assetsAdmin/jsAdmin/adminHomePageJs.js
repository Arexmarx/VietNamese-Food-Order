document.addEventListener('DOMContentLoaded', function() {
    const navLinks = document.querySelectorAll('.sidebar ul li a');
    const sections = document.querySelectorAll('.content section');

    navLinks.forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            const targetSection = this.getAttribute('data-section');

            sections.forEach(section => {
                if (section.id === targetSection) {
                    section.style.display = 'block';
                } else {
                    section.style.display = 'none';
                }
            });
        });
    });

    const blockButtons = document.querySelectorAll('.block-button');

    blockButtons.forEach(button => {
        button.addEventListener('click', function() {
            const userId = this.getAttribute('data-user-id');
            // Gửi yêu cầu để block người dùng
            blockUser(userId);
        });
    });

    function blockUser(userId) {
        // Xử lý logic để block người dùng ở đây
        alert('Người dùng ' + userId + ' đã bị block');
        // Bạn có thể thực hiện yêu cầu AJAX tới server để cập nhật trạng thái người dùng
    }
});
