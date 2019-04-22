function activeSidebar(id) {
    $("#" + id).siblings().removeClass("active");
    $("#" + id).addClass("active");
}

$(function () {
    var str = window.location.href;
    var index = str .lastIndexOf("\/");
    str  = str .substring(index + 1, str .length);

    activeSidebar(str + "-sidebar")
});