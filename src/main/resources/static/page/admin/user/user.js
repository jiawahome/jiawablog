/**
 * 点击修改按钮，弹出用户表单
 */
function onUpdateClick(index) {
    var user = users[index];
    console.log(user);
    $("#login-name-input").val(user.loginName);
    $("#password-input").val(user.password);
    $('#form-modal').modal("show");
}

$(function () {

    function list() {
        console.log("list");
        $.ajax({
            type: "get",
            url: "/admin/user/list",
            success: function (data) {
                $("#user-list").html(data);
            }
        })
    }

    list();

});