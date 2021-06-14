
var string_1 =window.location.search.split("?")[1]
var string_2=string_1.split("&")[0]
var string_3=string_1.split("&")[1]
var title=decodeURI(string_3.split("=")[1])
var username=decodeURI(string_2.split("=")[1])



$("#“user-info”").text(username)
$("#input_title").attr("value",title)
console.log(title)

var string_backUrl="console.html?username="+username
$("#link_back").attr("href",string_backUrl)



$.ajax({
    url:"http://localhost:8080/queryFromTitle",
    type:"get",
    dataType:"json",
    data:{
        title:title
        /*username:$userId.val(),
        password:$pwd.val()*/
    },
    success:function(result){
        console.log(result)

        if (result === null) {
            alert("未找到相关图书");
        } else {

            for(var i=0;i<result.length;i++) {
                (function (i) {
                    var s_title="title_"+i;
                    var s_author="author_"+i;
                    var s_press="press_"+i;
                    var s_quantity="quantity_"+i;
                    var s_button_delete="delete_"+result[i].isbn
                    var s_button_edit="button_edit_"+result[i].isbn


                    let ap="<li class=\"list-group-item\" style=\"padding: 36px 40px 40px;\">\n" +
                        "<div class=\"card\" style=\"width: 8rem;border: none\">\n"+
                        "<img src=\"img/数据库系统概念.jpg\" class=\"card-img-top img-fluid\" alt=\"...\" >\n" +
                        "<div class=\"card-body\">\n" +
                        "<p id="+s_title+" class=\'card-title\'></p>\n" +
                        "<p id="+s_author+" class=\'author\' style=\'margin-top: 0px;margin-bottom: 0px;font-size: 8px\'></p>\n" +
                        "<p id="+s_press+" class=\'press\' style=\'margin-top: 2px;margin-bottom: 0px;font-size: 8px\'></p>\n" +
                        "<p id="+s_quantity+" class=\'quantity\' style=\'margin-top: 6px;margin-bottom: 16px;font-size: 8px;\'></p>\n" +
                        "<button id="+s_button_edit+" class=\'btn btn-outline-primary\' style=\"width: 80px;height: 32px\"><p style=\"font-size: 12px;font-weight:bold;position: center\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">编辑</p></button>\n" +
                        "<button id="+s_button_delete+" style=\'width:80px;border:none;margin-top:8px;background-color:white;font-size: 12px\'>下架</button>\n" +
                        "  </div>\n" +
                        " </div>\n" +
                        " </li>"


                    $("#book_list").append(ap)

                    $("#"+s_title).text(result[i].title + "(" + result[i].language + ")");
                    $("#"+s_author).text(result[i].author + "(著）");
                    $("#"+s_press).text(result[i].press);
                    $("#"+s_quantity).text(result[i].quantity + "本");
                    console.log(result[i].title)


                    $("#"+s_button_delete).click(function () {
                        var isbn=s_button_delete.split("_")[1]
                        console.log(s_button_delete)

                        $.ajax({
                            url:"http://localhost:8080/deleteBook",
                            type:"get",
                            dataType: "json",
                            data:{
                                isbn:isbn
                            },
                            success: function(result){
                                console.log(result)
                                if(result) {
                                    location.reload()
                                }
                                else{
                                    window.alert("删除失败")
                                }
                            }
                        });
                    })

                    $("#"+s_button_edit).click(function () {
                        var isbn=s_button_edit.split("_")[2]
                        $.ajax({
                            url:"http://localhost:8080/queryFromIsbn",
                            data:{
                                isbn:isbn
                            },
                            type:"get",
                            success:function (result) {

                                var obj=eval("("+result+")")




                                var $isbn=$("#inputisbn")
                                var $title=$("#inputtitle")
                                var $author=$("#inputauthor")
                                var $press=$("#inputpress")
                                var $language=$("#inputlanguage")
                                var $quantity=$("#inputquantity")
                                $isbn.attr("value",obj.isbn)
                                $title.attr("value",obj.title)
                                $author.attr("value",obj.author)
                                $press.attr("value",obj.press)
                                $language.attr("value",obj.language)
                                $quantity.attr("value",obj.quantity)

                                $("#button_submitForm").click(function () {

                                    $("#button_exit").click(function () {
                                        location.reload()
                                    })


                                    if ($isbn.val() === "") {
                                        $("#text_warning").text("请填写ISBN")
                                    } else if ($title.val() === "") {
                                        $("#text_warning").text("请填写图书书名和版本")
                                    } else if ($author.val() === "") {
                                        $("#text_warning").text("请填写图书作者")
                                    } else if ($press.val() === "") {
                                        $("#text_warning").text("请填写出版社")
                                    } else if ($language.val() === "") {
                                        $("#text_warning").text("请填写图书语言")
                                    } else if ($quantity.val() === "") {
                                        $("#text_warning").text("请填写图书数量")
                                    } else {
                                        var json = {
                                            "isbn": $isbn.val(),
                                            "title": $title.val(),
                                            "author": $author.val(),
                                            "press": $press.val(),
                                            "language": $language.val(),
                                            "quantity": parseInt($quantity.val())

                                        }
                                        var string = JSON.stringify(json)
                                        console.log(string)
                                        $.ajax({
                                            url: "http://localhost:8080/updateBook",
                                            data: "get",
                                            datatype: json,
                                            data: {
                                                param: string
                                            },
                                            success(result) {
                                                console.log(result)
                                                window.alert("修改成功")
                                                window.location.reload()


                                            }

                                        })
                                    }
                                })





                            }
                        });
                    })

                })(i);



            }

            var text="'"+$("#input_title").val()+"'"+"-找到"+result.length+"本相关图书"
            $("#text_result").text(text)

            $("#button_edit").click(function () {
                $.ajax({
                    url:192.
                })
            })

        }

    }
});


$("#submit_search").click(function () {
    var string_url="console-edit.html"+"?"+string_2+"&"+"title="+$("#input_title").val()
    window.location.href=string_url

    console.log(string_url)

})







