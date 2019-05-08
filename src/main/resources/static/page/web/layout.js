function showLoadingModal() {
    $('#loading-modal').modal("show");
}

function hideLoadingModal() {
    $('#loading-modal').modal("hide");
}

/**
 * 渲染分页组件
 */
function renderPagination(pageOption, callback) {
    if ($("#pagination").attr("data-pagination")) {
        return;
    }
    $("#pagination").pagination({
        maxSize: 10,
        page: pageOption.current,
        totals: pageOption.total,
        pageSize: pageOption.size,
        lastText: '>>',
        firstText: '<<',
        prevText: '<',
        nextText: '>',
        btnSize: 'lg'
    }).onChangePage(function (e) {
        current = e.page;
        callback();
    });
}

function listCategory() {
    $.ajax({
        type: "get",
        data: {
            current: current,
            size: 10
        },
        url: "/web/category/all",
        success: function (data) {
            $("#category-list").html(data);
        }
    })
}

$(function () {
    listCategory();
});