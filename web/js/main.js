$.ajax({
    url:"http://localhost:8080/getBooks",
    type:"get",
    dataType:"json",
    data:{
        /*username:$userId.val(),
        password:$pwd.val()*/
    },
    success:function(result){
        console.log(result)

        if (result === false) {
            alert("图书加载失败");
        } else {

            for(var i=0;i<result.length;i++) {
                var s_title="title_"+i;
                var s_author="author_"+i;
                var s_press="press_"+i;
                var s_quantity="quantity_"+i;


                let ap="<li class=\"list-group-item\" style=\"padding: 36px 40px 40px;\">\n" +
                    "<div class=\"card\" style=\"width: 8rem;border: none\">\n"+
                    "<img src=\"img/数据库系统概念.jpg\" class=\"card-img-top img-fluid\" alt=\"...\" >\n" +
                    "<div class=\"card-body\">\n" +
                    "<p id="+s_title+" class=\'card-title\'></p>\n" +
                    "<p id="+s_author+" class=\'author\' style=\'margin-top: 0px;margin-bottom: 0px;font-size: 8px\'></p>\n" +
                    "<p id="+s_press+" class=\'press\' style=\'margin-top: 2px;margin-bottom: 0px;font-size: 8px\'></p>\n" +
                    "<p id="+s_quantity+" class=\'quantity\' style=\'margin-top: 6px;margin-bottom: 16px;font-size: 8px;\'></p>\n" +
                    "<button class=\"btn btn-outline-primary\" style=\"width: 80px;height: 32px\"><p style=\"font-size: 12px;font-weight:bold;position: center\">Borrow</p></button>\n" +
                    "  </div>\n" +
                    " </div>\n" +
                    " </li>"


                $("#book_list").append(ap)



                $("#"+s_title).text(result[i].title + "(" + result[i].language + ")");
                $("#"+s_author).text(result[i].author + "(著）");
                $("#"+s_press).text(result[i].press);
                $("#"+s_quantity).text(result[i].quantity + "本");

                console.log(result[i].title)
            }

        }

    }
});


$(document).ready(function () {
    var string=window.location.search.split("=");
    if(string[0]==="?username") {
        var username = string[1];
        $("#“user-info”").text(username).addClass("dropdown-toggle");
        document.getElementById("login_button").setAttribute("data-bs-toggle","dropdown");

        $("#sign_out").click(function () {
            window.location.href="main.html";
        })
        $("#convert_user").click(function () {
            window.location.href="login.html";
        })

        console.log(1)
    }





})
