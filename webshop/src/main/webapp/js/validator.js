const nameRegex = /^[a-zA-Z ]{2,16}$/;
const loginRegex =  /^[a-zA-z]{1}[a-zA-Z1-9]{3,16}$/;
const emailRegex = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
const passRegex = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{4,})/;

const loginErrorMsg = "*Login must have alphabet characters only and min 4 symbol!";
const firstNameErrorMsg = "*First name must have alphabet characters only!";
const lastNameErrorMsg = "*Last name must have alphabet characters only!";
const emailErrorMsg = "*You have entered an invalid email address! Please enter valid email address, like: hello@gmail.com";
const passwordErrorMsg = " *Password should contains more then 4 symbol and should have uppercase symbol!";
const confirmPasswordsErrorMsg ="*Password confirm should not be empty / should be equals with Password" ;

function formValidate() {

     var login = document.forms["register"]["login"];
     var firstName = document.forms["register"]["first-name"];
     var lastName = document.forms["register"]["last-name"];
     var email = document.forms["register"]["email"];
     var password = document.forms["register"]["password"];
     var confirmPassword = document.forms["register"]["confirm-password"];

      validate(login, loginRegex, loginErrorMsg, "login-error");
      validate(email, emailRegex, emailErrorMsg, "email-error");
      validate(firstName, nameRegex, firstNameErrorMsg,"first-name-error");
      validate(lastName, nameRegex, lastNameErrorMsg,"last-name-error");

      var massage =  document.getElementById("password-error");
      var massage2 =  document.getElementById("confirm-password-error");
      if(password.value !== confirmPassword.value || !passRegex.test(password.value)){
           password.style.border = "2px solid red";
           confirmPassword.style.border = "2px solid red";
           massage.innerHTML = passwordErrorMsg;
           massage2.innerHTML = confirmPasswordsErrorMsg;
           massage.style="color:red";
           massage2.style="color:red";
           event.preventDefault();
      } else{
          password.style.border = "2px solid green";
          confirmPassword.style.border = "2px solid green";
          massage.innerHTML = "&#10003";
          massage2.innerHTML = "&#10003";
          massage.style="color:green";
          massage2.style="color:green";
      }
}

function validate(field, regex, error, msgName) {
    var massage =  document.getElementById(msgName);
    if(!field.value.match(regex) || field.length == 0) {
     field.style.border = "2px solid red";
      massage.innerHTML = error;
      massage.style="color:red";
      event.preventDefault();
    } else{
      field.style.border = "2px solid green";
      massage.style="color:green";
      massage.innerHTML = "&#10003";
    }
}


