function checkPassword(password, form) {

    let regExp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;

    if(password.match(regExp)) {
        return true;
    }
    else {
        alert("Ваш пароль не соответствует требованиям безопасности");
        return false;
    }
}

function checkEmail(email, form) {

    let regExp = /^[\w-\.]+@[\w-]+\.[a-z]{2,4}$/i;

    if (email.match(regExp)) {
        return true;
    }
    else {
        alert("Вы ввели почту неверно");
        return false;
    }
}

function sameFields(field1, field2) {
    if (field1 === field2) {
        return true;
    }
    else {
        alert("Повторяющий пароль не совпадает с исходным")
        return false;
    }
}

function checkForm() {

    let password = document.getElementById("password").value;
    let submitPassword = document.getElementById("submit password").value;
    let email = document.getElementById("email").value;
    let form = document.getElementById("registration form");

    if (checkPassword(password, form) & checkEmail(email, form) & sameFields(password, submitPassword)) {
        form.submit();
    }
}
