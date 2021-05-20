var x = document.getElementById("login");
var y = document.getElementById("register");
var z = document.getElementById("movement");

function loginForm(){
    x.style.left = "0px";
    y.style.left = "400px";
    z.style.left = "0";
}

function registerForm(){
    x.style.left = "-350px";
    y.style.left = "0px";
    z.style.left = "120px";
}

function count_check(obj){
    var theme = document.getElementsByName("themes");
    var checkingCount = 0;
    for(var i=0; i<theme.length; i++){
        if(theme[i].checked){checkingCount++;}
    }
    if(checkingCount>2){
        alert("2개까지만 체크 가능합니다!")
        obj.checked = false;
        return false;
    }
}