function checkValue(select,type){
    //num:only num could be put
    //word:nun,word,underline and not whitespace
    //eSpetial:except . , & | ~ / \ ' " ; * $ { } [ ] ( ) = whitespace
    var dest=document.querySelector(select);
    var exp = "";
    switch(type){
        case "num":
            exp=/\D/g;
            break;
        case "word":
            exp=/[\W\s]/g;
            break;
        case "eSpetial":
            exp=/[., &^|\/\\'";*$~\[\]{}()=]/g;
            break;
        default:
            alert("the parameter is wrong");
            return;
    }
    dest.value = dest.value.replace(exp,'');
}

function inputFocus(obj){
    var label = obj.parentNode.firstElementChild;
    label.style.left='90%';
}
function inputBlur(obj){
    var label = obj.parentNode.firstElementChild;
    if(obj.value==""){
        label.style.left='0';
    }
}
function showToolTip(selector,action,time,title) {
    var tip = $(selector);
    tip.tooltip("destroy");
    if(title!=null){
        tip.tooltip({title:title});
    }
    tip.tooltip(action);
    setTimeout(function () {
        tip.tooltip("destroy");
    },time);
}