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