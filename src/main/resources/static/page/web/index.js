current = 1;
/**
 * 查询文章列表
 */
function list() {
    showLoadingModal();
    var cid = getUrlParam("cid");
    console.log("list");
    $.ajax({
        type: "get",
        data: {
            current: current,
            size: 10,
            categoryId: cid
        },
        url: "/web/article/list",
        success: function (data) {
            $("#article-list").html(data);

            renderPagination(page, list);
            setTimeout(function () {
                hideLoadingModal();
            }, 1000);
        }
    })
}

$(function () {

    console.log(1111)
    list();

});