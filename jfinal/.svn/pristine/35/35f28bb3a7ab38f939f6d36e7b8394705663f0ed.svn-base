<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>UEditor demo</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="UEditor demo">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
window.UEDITOR_SERVER_URL = "${CONTEXT_PATH}/ueditor";
</script>
<!-- 配置文件 -->
<script type="text/javascript"
	src="${CONTEXT_PATH}/static/ueditor-1.4.3.3/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript"
	src="${CONTEXT_PATH}/static/ueditor-1.4.3.3/ueditor.all.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${CONTEXT_PATH}/static/ueditor-1.4.3.3/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
div{
    width:100%;
}
</style>
</head>

<body>
<!-- 加载编辑器的容器 -->
<script id="container" name="content" type="text/plain">
	初始化的内容
</script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
	var ue = UE.getEditor('container');
</script>
</body>
</html>
