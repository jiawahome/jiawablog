/**
 * 查询用户列表
 */
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

/**
 * 点击修改按钮，弹出用户表单
 */
function onUpdateClick(index) {
    var user = users[index];
    console.log(user);
    $("#id-input").val(user.id);
    $("#login-name-input").val(user.loginName);
    $("#password-input").val(user.password);
    $('#form-modal').modal("show");
}

/**
 * 点击表单保存按钮时的动作
 */
function onSaveClick() {
    var id = $("#id-input").val();
    var loginName = $("#login-name-input").val();
    var password = $("#password-input").val();

    $.ajax({
        type: "post",
        url: "/admin/user/update",
        data: {
            id: id,
            loginName: loginName,
            password: password
        },
        success: function (data) {
            console.log("save success");
            $('#form-modal').modal("hide");
            list();
        }
    })
}

$(function () {

    list();

});