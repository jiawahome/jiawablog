/**
 * 查询用户列表
 */
function list() {
    showLoadingModal();
    console.log("list");
    $.ajax({
        type: "get",
        data: {
            cur: 1,
            pageSize: 4
        },
        url: "/admin/user/list",
        success: function (data) {
            $("#user-list").html(data);

            renderPagination(page);
            setTimeout(function () {
                hideLoadingModal();
            }, 1000);
        }
    })
}

/**
 * 点击修改按钮，弹出用户表单
 */
function onUpdateClick(index) {
    var user = users[index];
    console.log(user);
    $("#login-name-input").attr("disabled", true);
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
        url: "/admin/user/save",
        data: {
            id: id,
            loginName: loginName,
            password: password
        },
        success: function (data) {
            if (data == "exist") {
                showAlertModal("用户名已存在");
            } else {
                console.log("save success");
                $('#form-modal').modal("hide");
                list();
            }
        }
    })
}

/**
 * 点击刷新按钮
 */
function onRefreshClick() {
    list();
}

/**
 * 点击删除按钮
 */
function onDeleteClick(id) {
    showConfirmModal("确认删除用户？", function () {
        $.ajax({
            type: "delete",
            url: "/admin/user/delete/" + id,
            success: function (data) {
                console.log("delete success");
                list();
            }
        })
    });

}

/**
 * 点击新增按钮
 */
function onAddClick() {
    $("#login-name-input").removeAttr("disabled");
    $("#id-input").val(null);
    $("#login-name-input").val(null);
    $("#password-input").val(null);
    $('#form-modal').modal("show");
}

$(function () {

    // $("#welcome-sidebar").removeClass("active");
    // $("#article-sidebar").removeClass("active");
    // $("#type-sidebar").removeClass("active");
    // $("#user-sidebar").addClass("active");
    // activeSidebar("user-sidebar");
    list();

});