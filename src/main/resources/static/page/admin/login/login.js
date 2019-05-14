function doLogin() {
    console.log(11123)
    $.ajax({
        type: "post",
        url: "/admin/doLogin",
        data: {
            loginName: $("#loginName").val(),
            password: hex_md5($("#password").val())
        },
        success: function (data) {
            console.log(data);
            if (data == 1) {
                window.location.href = "/admin/welcome";
            } else {
                alert("用户名或密码不对");
            }
        }
    })
}