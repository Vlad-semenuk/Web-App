const login_regexp =  /^[a-zA-z]{1}[a-zA-Z1-9]{3,20}$/;
const email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
const pass_regexp = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{4,})/;

const loginErrorMsg = "*Login must have alphabet characters only and min 4 symbol!";
const emailErrorMsg = "*You have entered an invalid email address! Please enter valid email address, like: hello@gmail.com";
const passwordErrorMsg = " *Password should contains more then 4 symbol!";
const confirmPasswordsErrorMsg ="*Password confirm should not be empty / should be equals with Password" ;

$(document).ready(function() {
  $('#reg').submit(function(e) {
        e.preventDefault();
        var login = $('#login').val();
        var email = $('#email').val();
        var password = $('#password').val();
        var confirmPassword = $('#confirm-password').val();

         $(".error").remove();
         var resultLogin =  checkLogin(login);
         var resultEmail =  checkLogin(login);
         var resultPass  =  checkPass(password, confirmPassword);
         if(resultLogin && resultEmail && resultPass){
              $('#reg').unbind('submit').submit();
          }
  });
});

function checkLogin (login){
  if (login_regexp.test(login) === false) {
       $('#login').after('<span class="error" color: red;>*Login must have alphabet characters only!</span>');
        return false;
      } else{
        return true;
    }
}

function checkEmail (email){
   if (email_regexp.test(email) === false) {
       $('#email').after('<span class="error" color: red>You have entered an invalid email address! Please enter valid email address, like: hello@gmail.com</span>');
        return  false;
     } else{
      return true;
   }
}

function checkPass(password, confirmPassword){
   if (!pass_regexp.test(password) || password !== confirmPassword) {
         $('#password').after('<span class="error">*Password should contains more then 4 symbol and should have uppercase symbol</span>');
         $('#confirm-password').after('<span class="error">*Password confirm should not be empty / should be equals with Password</span>');
          return false;
        } else{
          return true;
     }
}