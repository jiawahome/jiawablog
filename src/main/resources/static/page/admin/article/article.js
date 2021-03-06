current = 1;
/**
 * 查询文章列表
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
        url: "/admin/article/list",
        success: function (data) {
            $("#article-list").html(data);

            renderPagination(page, list);
            setTimeout(function () {
                hideLoadingModal();
            }, 1000);
        }
    })
}

/**
 * 点击修改按钮，弹出文章表单
 */
function onUpdateClick(index) {
    var article = articles[index];
    console.log(article);
    $("#id-input").val(article.id);
    $("#title-input").val(article.title);
    $("#category-id-select").val(article.categoryId);
    $("#summary-textarea").val(article.summary);
    $("#status-select").val(article.status);
    $('#form-modal').modal("show");
}

/**
 * 点击表单保存按钮时的动作
 */
function onSaveClick() {
    var id = $("#id-input").val();
    var title = $("#title-input").val();
    var categoryId = $("#category-id-select").val();
    var summary = $("#summary-textarea").val();
    var status = $("#status-select").val();

    $.ajax({
        type: "post",
        url: "/admin/article/save",
        data: {
            id: id,
            title: title,
            categoryId: categoryId,
            summary: summary,
            status: status
        },
        success: function (data) {
            if (data == "exist") {
                showAlertModal("文章名已存在");
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
    showConfirmModal("确认删除文章？", function () {
        $.ajax({
            type: "delete",
            url: "/admin/article/delete/" + id,
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
    $("#title-input").val(null);
    $("#category-id-select").val(null);
    $("#summary-textarea").val(null);
    $("#status-select").val(null);
    $('#form-modal').modal("show");
}

/**
 * 点击内容按钮，弹出内容表单
 */
function onContentClick(index) {
    var article = articles[index];
    console.log(article);
    $("#content-id-input").val(article.id);

    $('#content-summernote').summernote({
        height: 300
    });
    $('#content-summernote').summernote("code", "");
    $.ajax({
        type: "get",
        url: "/admin/article/content/find/" + article.id,
        success: function (data) {
            $('#content-summernote').summernote("code", data.content);
            $('#form-content-modal').modal("show");
        }
    })


}

/**
 * 点击表单保存按钮时的动作
 */
function onSaveContentClick() {
    var id = $("#content-id-input").val();
    var content = $('#content-summernote').summernote("code");

    $.ajax({
        type: "post",
        url: "/admin/article/content/save",
        data: {
            id: id,
            content: content
        },
        success: function (data) {
            console.log("save success");
            showAlertModal("保存成功")
        }
    })
}

$(function () {

    list();

});