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

            var $title = $("#input_title")
            if ($title.val() === "") {
                alert("请输入要编辑的书名")
            }
            window.location.href = "console-edit.html" + "?username=" + username + "&searchBook=" + $title.val()
        })


        $("#button_submit_search").click(function () {

            $("#button_showall").css("display","block").text("显示全部记录").click(function () {
                window.location.reload()
            })
            console.log("click")
            $("#tbody").html("")
            var title = $("#input_search_record").val()
            if (title === "") {
                window.alert("请输入要查询的记录的书名")
            } else {
                $.ajax({
                    url:"http://localhost:8080/getLendingInfoByTitle",
                    type: "get",
                    datatype: "json",
                    data: {
                        title: title
                    },
                    success: function (result) {
                        console.log(result)
                        var json = JSON.parse(result)
                        console.log(json)
                        for (var i = 0; i < json.length; i++) {
                            (function (i) {
                                var s_button_return = "button_borrow_" + i;
                                var s_title = "title_" + i;
                                var s_isbn = "isbn_" + i;
                                var s_user = "user_" + i;
                                var s_date = "date_" + i;
                                var s_state = "state_" + i
                                let ap = "<tr>\n" +
                                    "<th scope=\"row\">" + (i + 1) + "</th>\n" +
                                    "<td id=" + s_title + "></td>\n" +
                                    "<td id=" + s_isbn + "></td>\n" +
                                    "<td id=" + s_user + "></td>\n" +
                                    "<td id=" + s_date + "></td>\n" +
                                    "<td id=" + s_state + " style=font-size:13px></td>\n" +
                                    "<td id=" + s_button_return + "><button class=\'btn btn-outline-primary\' style=\"padding:0px;font-size:12px;width: 50px;height:20px\">归还</button></td>\n" +
                                    "</tr>";

                                $("#tbody").append(ap)

                                $("#" + s_title).text(json[i].bookTitle)
                                $("#" + s_isbn).text(json[i].bookId)
                                $("#" + s_user).text(json[i].reader)
                                $("#" + s_date).text(json[i].lendDate)
                                if (json[i].state) {
                                    $("#" + s_state).text("未归还")

                                } else {
                                    $("#" + s_state).text("已归还")
                                    $("#" + s_button_return).remove()
                                }

                                $("#" + s_button_return).click(function () {
                                    var book_id = json[i].bookId
                                    console.log(s_button_return)
                                    console.log(book_id)
                                    $.ajax({
                                        url: "http://localhost:8080/returnBook",
                                        type: "get",
                                        datatype: "json",
                                        data: {
                                            bookId: book_id
                                        },
                                        success: function (result) {
                                            if (result) {
                                                window.alert("归还成功")
                                                window.location.reload()
                                            }
                                        }

                                    })
                                })


                            })(i);
                        }

                    }
                })
            }
        })
    }



    //动态添加借阅记录
    $.ajax( {
        url:"http://localhost:8080/getLendingInfo",
        type:"get",
        datatype:"json",
        success:function(result){
            console.log(result)
            console.log(result.length)
            var json=JSON.parse(result)
            console.log(json)
            for(var i=0;i<json.length;i++){
                (function (i) {
                    var s_button_return="button_borrow_"+i;
                    var s_title="title_"+i;
                    var s_isbn="isbn_"+i;
                    var s_user="user_"+i;
                    var s_date="date_"+i;
                    var s_state="state_"+i
                    let ap="<tr>\n" +
                        "<th scope=\"row\">"+(i+1)+"</th>\n" +
                        "<td id="+s_title+"></td>\n" +
                        "<td id="+s_isbn+"></td>\n" +
                        "<td id="+s_user+"></td>\n" +
                        "<td id="+s_date+"></td>\n" +
                        "<td id="+s_state+" style=font-size:13px></td>\n" +
                        "<td id="+s_button_return+ "><button class=\'btn btn-outline-primary\' style=\"padding:0px;font-size:12px;width: 50px;height:20px\">归还</button></td>\n" +
                        "</tr>";

                    $("#tbody").append(ap)

                    $("#"+s_title).text(json[i].bookTitle)
                    $("#"+s_isbn).text(json[i].bookId)
                    $("#"+s_user).text(json[i].reader)
                    $("#"+s_date).text(json[i].lendDate)
                    if(json[i].state) {
                        $("#" + s_state).text("未归还")

                    }else{
                        $("#" + s_state).text("已归还")
                        $("#"+s_button_return).remove()
                    }

                    $("#"+s_button_return).click(function () {
                        var book_id=json[i].bookId
                        console.log(s_button_return)
                        console.log(book_id)
                        $.ajax({
                            url:"http://localhost:8080/returnBook",
                            type:"get",
                            datatype: "json",
                            data:{
                                bookId:book_id
                            },
                            success:function (result) {
                                if(result){
                                    window.alert("归还成功")
                                    window.location.reload()
                                }
                            }

                        })
                    })




                })(i);
            }
        }
    })














}
)
