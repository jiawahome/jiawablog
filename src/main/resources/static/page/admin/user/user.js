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