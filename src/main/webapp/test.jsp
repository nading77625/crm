<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/8/27
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
         // window.onload = function () {
         //    alert("asdasd")
         //    document.getElementById("loginUser").focus()
         // }当页面加载完毕账号栏自动获取焦点


         // window.onload = function(){
         //     document.getElementById("buttonSub").onclick = function () {
         //        alert("wuhu")
         //     }
         // }//点击按钮编辑想要的效果

         $(function(){
           $("#date").blur(function () {
                //alert($("#date").val())
               var date = $("#date").val()
               //alert(date.substr(0,4))
               //20201010
               var newDate = date.substr(0,4) + "-" + date.substr(4,2) + "-" + date.substr(6,2)
               $("#date").val(newDate)
           })
           $("#loginUser").focus()//当页面加载完毕账号栏自动获取焦点

           $("#buttonSub").click(function () {
                login()//点击登录按钮显示效果
           })
             //为当前登录窗口绑定敲击键盘的事件
           $(window).keydown(function (event) {
                //event表示我们敲的是哪个键

               if(event.keyCode == 13){
                   login()
               }
           })

         })
        function login() {
            // alert("登录验证")
            //首先验证账号密码不为空,将文本中的左右空格去掉，使用$.trim(文本)
            var user = $.trim($("#loginUser").val())
            var psw = $.trim($("#loginPassword").val())
            if(user == "" || psw == ""){
                $("#exMsg").html("账号或者密码不能为空")
                // 为空的话，终止该方法
                return false;
            }
            // 去后台验证登录的相关操作
            $.ajax({
                url:"one",
                data:{
                    "loginUser" : user,
                    "loginPassword" : psw
                },
                type:"post",
                dataType:"json",
                success:function (data) {
                    /*
                        data
                            {“success”:true/false,"msg":"cuwo"}
                     */
                    if(data.success){
                        //跳转到欢迎页
                        alert("登录成功啦")
                        //window.location.href = "workbench/index.html"
                    }
                    //如果登录失败
                    else{
                        $("#exMsg").html(data.msg)
                    }
                }

            })
        }
    </script>
</head>
<body>
    <form action="one">
        账号：<input type="text" id="loginUser">
        密码：<input type="text" id="loginPassword">
        <input type="button" id="buttonSub" value="login">
    </form>
    <span id="exMsg" style="color: red"></span>
    <br>日期：<input type="text" id="date">

</body>
</html>
