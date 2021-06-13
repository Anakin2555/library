$(document).ready(function () {
    var string=window.location.search.split("=")

    if(string[0]==="?username") {
        var username = string[1];
        $("#“user-info”").text(username)


        $("#sign_out").click(function () {
            window.location.href = "main.html";
            console.log("main")
        })
        $("#convert_user").click(function () {
            window.location.href = "login.html";
            console.log("login")
        })

        console.log(1)




        $("#button_searchBook").click(function () {

            var $title=$("#input_title")
            if($title.val()===""){
                alert("请输入要编辑的书名")
            }
            window.location.href = "console-edit.html"+"?username="+username+"&searchBook="+$title.val()
        })
    }
}
)