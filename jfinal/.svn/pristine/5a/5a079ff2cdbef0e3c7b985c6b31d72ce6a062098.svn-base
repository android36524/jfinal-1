<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:16:41 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>主页</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="${CONTEXT_PATH}/static/hplus/favicon.ico">
    <link href="${CONTEXT_PATH}/static/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${CONTEXT_PATH}/static/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${CONTEXT_PATH}/static/hplus/css/animate.min.css" rel="stylesheet">
    <link href="${CONTEXT_PATH}/static/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${CONTEXT_PATH}/static/LayuiForLayIM_2.0.84/layui/css/layui.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span ><img style="height: 80px;width: 80px;" alt="image" class="img-circle" src="${CONTEXT_PATH}/upload/images/head/<shiro:principal property="userCode"/>.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <shiro:user>  
                               	<span class="block m-t-xs"><strong class="font-bold"><shiro:principal property="userCode"/> </strong></span>
                                <span class="text-muted text-xs block"><shiro:principal property="userName"/><b class="caret"></b></span>
								</shiro:user>   
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
<!--                                 <li><a class="J_menuItem" href="form_avatar.html">修改头像</a> -->
<!--                                 </li> -->
                                <li><a class="J_menuItem" href="${CONTEXT_PATH}/admin/system/user/userInfo">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="${CONTEXT_PATH}/admin/system/user/editPaw">修改密码</a>
                                </li>
<!--                                 <li><a class="J_menuItem" href="mailbox.html">信箱</a> -->
<!--                                 </li> -->
                                <li class="divider"></li>
                                <li><a href="login/doLogout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">H+
                        </div>
                    </li>
                    <li>
                        <a class="J_menuItem" href="main-index"><i class="fa fa-home"></i> <span class="nav-label">主页</span></a>
                    </li>
                    <c:forEach items="${list}" var="v">
                    	<shiro:hasPermission name="${v.funcCde}">  
	                    	<c:if test="${v.parentCde == null}">
			                    <li>
			                        <a href="#">
			                            <i class="fa fa-${v.funcCde}"></i>
			                            <span class="nav-label">${v.funcName}</span>
			                            <span class="fa arrow"></span>
			                        </a>
			                        <ul class="nav nav-second-level">
			                        	<c:forEach items="${list}" var="v2">
			                        		<shiro:hasPermission name="${v2.funcCde}">  
	                    					<c:if test="${v.funcCde == v2.parentCde}">
					                            <li>
					                            	<a class="J_menuItem" href="${CONTEXT_PATH}${v2.link}"> <span class="nav-label">${v2.funcName}</span></a>
					                            </li>
			                            	</c:if>
			                            	</shiro:hasPermission>
										</c:forEach>
			                        </ul>
			                    </li>
	                    	</c:if>
						</shiro:hasPermission>
					</c:forEach>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="http://www.zi-han.net/theme/hplus/search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="main-index">主页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="login/doLogout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="mainIndex" frameborder="0" data-id="main-index" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's blog</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <!--右侧边栏结束-->
        <!--mini聊天窗口开始-->
    </div>
    <script src="${CONTEXT_PATH}/static/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="${CONTEXT_PATH}/static/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${CONTEXT_PATH}/static/hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${CONTEXT_PATH}/static/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${CONTEXT_PATH}/static/hplus/js/plugins/layer/layer.min.js"></script>
    <script src="${CONTEXT_PATH}/static/hplus/js/hplus.min.js?v=4.1.0"></script>
    <script type="text/javascript" src="${CONTEXT_PATH}/static/hplus/js/contabs.min.js"></script>
    <script src="${CONTEXT_PATH}/static/hplus/js/plugins/pace/pace.min.js"></script>
    <script src="${CONTEXT_PATH}/static/LayuiForLayIM_2.0.84/layui/layui.js"></script>
    <script type="text/javascript">
      //* 
		layui.use('layim', function(layim) {
			var socket;
			//基础配置
			layim.config({
				//初始化接口
				init: {
					url: '${CONTEXT_PATH}/admin/getList',
					data: {}
				}
				//简约模式（不显示主面板）
// 				,brief: true
				//查看群员接口
				,
				members: {
					url: '',
					data: {}
				},
				uploadImage: {
					url: '' //（返回的数据格式见下文）
					,
					type: '' //默认post
				},
				uploadFile: {
					url: '' //（返回的数据格式见下文）
					,
					type: 'post' //默认post
				}
				//,skin: ['aaa.jpg'] //新增皮肤
				,
				isfriend:true //是否开启好友
				,
				isgroup: false //是否开启群组
				,
				chatLog: '${CONTEXT_PATH}/static/LayuiForLayIM_2.0.84/demo/chatlog.html' //聊天记录地址
				,
				find: '${CONTEXT_PATH}/static/LayuiForLayIM_2.0.84/demo/find.html'
				//,copyright: true //是否授权
			});

			//监听发送消息
			layim.on('sendMessage', function(data){
			    var To = data.to;
			    console.log(JSON.stringify(data));
				socket.send(JSON.stringify(data))
			});
			
			//layim建立就绪
			layim.on('ready', function(res) {
				//建立WebSocket通讯  ws://localhost:8080/jfinal/websocket.ws
				var url = location.hostname + ":" + location.port + '${CONTEXT_PATH}';
				url = "ws://" + url + "/ws/"+<shiro:principal property="id"/>;
				console.log('url===>'+url)
				socket = new WebSocket(url);
				//发送一个消息
				//socket.send('Hi Server, I am LayIM!');
				//连接成功时触发
				socket.onopen = function(res) {
					console.log('onopen===>'+res)
				};
				socket.onclose = function(res) {
// 					socket = new WebSocket("ws://" + url + "/websck");
					layer.alert("请刷新重新连接！")
					console.log('onclose===>'+res)
				};
				socket.onerror = function(res) {
					layer.msg("异常==》"+res)
// 					socket = new WebSocket("ws://" + url + "/websck");
					layer.alert("请刷新重新连接！")
					console.log('onerror===>'+res)
				};
				//监听收到的消息
				socket.onmessage = function(res) {
					//res为接受到的值，如 {"emit": "messageName", "data": {}}
					//emit即为发出的事件名，用于区分不同的消息
					console.log(res)
					if (res.type === 'message') {
	 					layim.getMessage(eval("("+res.data+")")); //res.data即你发送消息传递的数据（阅读：监听发送的消息）
					}
				};
				
			});

			//监听查看群员
			layim.on('members', function(data) {
				console.log(data);
			});

			//监听聊天窗口的切换
			layim.on('chatChange', function(data) {
				console.log(data);
			});

		});
		// */
    </script>
</body>

</html>
