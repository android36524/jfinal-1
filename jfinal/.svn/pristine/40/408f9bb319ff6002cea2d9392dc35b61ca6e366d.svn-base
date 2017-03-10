<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="/WEB-INF/common/exception.jsp" %>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<!-- Mobile Devices Support @begin -->
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta name="format-detection" content="telephone=no, address=no"/>
<meta name="apple-itunes-app" content="app-id=1234567"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<!-- Mobile Devices Support @end -->

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<title><sitemesh:write property='title'></sitemesh:write></title>
<link rel="stylesheet" href="${CONTEXT_PATH }/static/iconfont/iconfont.css" />
<link rel="stylesheet" href="${CONTEXT_PATH }/static/weui/weui.min.css?v=1.1.0" />
<link rel="stylesheet" href="${CONTEXT_PATH }/static/jquery-drawer/css/drawer.min.css?v=3.2.1" />
<link rel="stylesheet" href="${CONTEXT_PATH}/static/jquery-labelauty/jquery-labelauty.css" />
<link rel="stylesheet" href="${CONTEXT_PATH}/static/bootstrap-datepicker/css/bootstrap-datepicker3.min.css?v=v1.7.0-dev" />

<link rel="stylesheet" href="${CONTEXT_PATH}/static/hplus/css/bootstrap.min.css?v=3.3.6" />
<link rel="stylesheet" href="${CONTEXT_PATH}/static/layer/mobile/need/layer.css?v=2.0" />

<script src="${CONTEXT_PATH}/static/jquery/jquery.min.js?v=2.2.4" charset="utf-8"></script>
<script src="${CONTEXT_PATH}/static/iscroll/iscroll.js?v=v5.2.0" charset="utf-8"></script>
<script src="${CONTEXT_PATH}/static/jquery-drawer/js/drawer.min.js?v=3.2.1" charset="utf-8"></script>
<script src="${CONTEXT_PATH}/static/jquery-labelauty/jquery-labelauty.js" charset="utf-8"></script>
<script src="${CONTEXT_PATH}/static/bootstrap-datepicker/js/bootstrap-datepicker.min.js?v=v1.7.0-dev"></script>
<script src="${CONTEXT_PATH}/static/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>

<script src="${CONTEXT_PATH}/static/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${CONTEXT_PATH}/static/layer/mobile/layer.js?v=2.x" charset="utf-8"></script>

<script src="${CONTEXT_PATH}/static/juicer/juicer.min.js?v=0.6.14" charset="utf-8"></script>
<script type="text/javascript">
window.alert = function(msg,t){
	layer.open({className:'layui-m-layer-msg',shade:false,content: msg||"hello world", time: t||2, anim:'scale'});
}
window.loading = function(msg){
	layer.open({type: 2});
}

juicer.set({
    'tag::interpolateOpen': '&{',
    'tag::interpolateClose': '}',
    'tag::noneencodeOpen': '&&{',
    'tag::noneencodeClose': '}'
});
jQuery.extend(
	  jQuery.expr[':'],
	  {
	    /// check that a field's value property has a particular value
	    'field-value': function (el, indx, args) {
	      var a, v = $(el).val();
	      if ( (a = args[3]) ) {
	        switch ( a.charAt(0) ) {
	          /// begins with
	          case '^':
	            return v.substring(0,a.length-1) == a.substring(1,a.length);
	          break;
	          /// ends with
	          case '$':
	            return v.substr(v.length-a.length-1,v.length) ==
	              a.substring(1,a.length);
	          break;
	          /// contains
	          case '*': return v.indexOf(a.substring(1,a.length)) != -1; break;
	          /// equals
	          case '=': return v == a.substring(1,a.length); break;
	          /// not equals
	          case '!': return v != a.substring(1,a.length); break;
	          /// equals
	          default: return v == a; break;
	        }
	      }
	      else {
	        return !!v;
	      }
	    }
	  }
	);
</script>
<sitemesh:write property='head'></sitemesh:write>
</head>
<body>
<sitemesh:write property='body'></sitemesh:write>
</body>
</html>
