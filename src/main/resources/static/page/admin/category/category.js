current = 1;
/**
 * 查询分类列表
 */
function list() {
    showLoadingModal();
    console.log("list");
    $.ajax({
        type: "get",
        data: {
            current: current,
            size: 10
        },
        url: "/admin/category/list",
        success: function (data) {
            $("#category-list").html(data);

            renderPagination(page, list);
            setTimeout(function () {
                hideLoadingModal();
            }, 1000);
        }
    })
}

/**
 * 点击修改按钮，弹出分类表单
 */
function onUpdateClick(index) {
    var category = categorys[index];
    console.log(category);
    $("#id-input").val(category.id);
    $("#name-input").val(category.name);
    $('#form-modal').modal("show");
}

/**
 * 点击表单保存按钮时的动作
 */
function onSaveClick() {
    var id = $("#id-input").val();
    var name = $("#name-input").val();

    $.ajax({
        type: "post",
        url: "/admin/category/save",
        data: {
            id: id,
            name: name
        },
        success: function (data) {
            if (data == "exist") {
                showAlertModal("分类名已存在");
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
    showConfirmModal("确认删除分类？", function () {
        $.ajax({
            type: "delete",
            url: "/admin/category/delete/" + id,
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
    $("#id-input").val(null);
    $("#name-input").val(null);
    $('#form-modal').modal("show");
}

$(function () {

    // $("#welcome-sidebar").removeClass("active");
    // $("#article-sidebar").removeClass("active");
    // $("#type-sidebar").removeClass("active");
    // $("#category-sidebar").addClass("active");
    // activeSidebar("category-sidebar");
    list();

});