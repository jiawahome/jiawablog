function activeSidebar(id) {
    $("#" + id).siblings().removeClass("active");
    $("#" + id).addClass("active");
}

function showConfirmModal(message) {
    if (message) {
        $("#confirm-message").text(message);
    }
    $('#confirm-modal').modal("show");
}

$(function () {
    var str = window.location.href;
    var index = str .lastIndexOf("\/");
    str  = str .substring(index + 1, str .length);

    activeSidebar(str + "-sidebar")
});