/**
 * 点击修改按钮，弹出用户表单
 */
function onUpdateClick() {
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