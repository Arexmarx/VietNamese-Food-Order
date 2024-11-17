/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 document.querySelectorAll('.show-password-toggle').forEach(toggle => {
            toggle.addEventListener('click', function() {
                const inputId = this.getAttribute('data-toggle');
                const input = document.getElementById(inputId);
                const isPassword = input.type === 'password';
                input.type = isPassword ? 'text' : 'password';
                this.classList.toggle('fa-eye');
                this.classList.toggle('fa-eye-slash');
            });
});




