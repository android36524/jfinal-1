<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>登录</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="${CONTEXT_PATH}/static/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${CONTEXT_PATH}/static/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${CONTEXT_PATH}/static/hplus/css/animate.min.css" rel="stylesheet">
    <link href="${CONTEXT_PATH}/static/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎使用 H+</h3>

            <form class="m-t" role="form" id="form1" action="${CONTEXT_PATH}/admin/login/doLogin">
                <div class="form-group">
                    <input type="text" class="form-control" name="userName" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="密码" required="">
                </div>
                <button type="button" onclick="login();" class="btn btn-primary block full-width m-b">登 录</button>

				${msg }
                <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>
    <script src="${CONTEXT_PATH}/static/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="${CONTEXT_PATH}/static/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${CONTEXT_PATH}/static/js/DM5.js"></script>
	<script src="${CONTEXT_PATH}/static/layer/layer.js"></script>
    <script>
    	function login(){
    		var pw = MD5($("input[name='password']").val());
			$("input[name='password']").val(pw);
	    	$("#form1").submit();
    	}
    	
    	<c:if test="${!empty msg}">
			layer.msg("${msg}"+"");
		</c:if>
    </script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
