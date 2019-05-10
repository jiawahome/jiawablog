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
    var categorys = sessionStorage.getItem("categorys");
    if (categorys) {
        $("#category-list").html(categorys);
        return;
    }

    $.ajax({
        type: "get",
        url: "/web/category/all",
        success: function (data) {
            $("#category-list").html(data);
            sessionStorage.setItem("categorys", data);
        }
    })
}

/**
 * 获取Url参数
 * @param paraName
 * @returns {*}
 * @constructor
 */
function getUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");
    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;
        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");
            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    } else {
        return "";
    }
}

$(function () {
    listCategory();
});