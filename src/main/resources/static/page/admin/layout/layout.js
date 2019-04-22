function activeSidebar(id) {
    $("#" + id).siblings().removeClass("active");
    $("#" + id).addClass("active");
}

/**
 * 显示确认框
 * @param message
 */
function showConfirmModal(message, func) {
    if (message) {
        $("#confirm-message").text(message);
    }
    $('#confirm-modal').modal("show");

    if (func){
        afterConfirm = func;
    }
}

/**
 * 点击确认
 */
function confirm() {
    $('#confirm-modal').modal("hide");
    afterConfirm();
}

function afterConfirm() {
    
}

/**
 * 显示提示框
 * @param message
 */
function showAlertModal(message) {
    if (message) {
        $("#alert-message").text(message);
    }
    $('#alert-modal').modal("show");
}

$(function () {
    var str = window.location.href;
    var index = str .lastIndexOf("\/");
    str  = str .substring(index + 1, str .length);

    activeSidebar(str + "-sidebar")
});