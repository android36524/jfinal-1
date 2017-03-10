<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	
<script src="${CONTEXT_PATH}/static/layer/layer.js"></script>
<script>
$(document).ready(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	})
	<c:if test="${!empty msg}">
		layer.msg("${msg}"+"");
	</c:if>
	
	<c:if test="${!empty errMsg}">
		layer.alert("${errMsg}"+"");
	</c:if>
	
	<c:if test="${!empty re}">
		<c:if test="${re.code == 'err'}">
			layer.msg("${re.msg}"+"");
		</c:if>
	</c:if>
});
function add(){
	location.href = "edit"
}
function edit(id){
	location.href = "edit?id="+id;
}
function backList(){
	location.href = "list"
}
function del(){
	var ids = '';
	$("input[name='checksIds']:checked").each(function(){
		ids += $(this).val() + ',';
	});
	
	location.href = "del?ids="+ids;
}
function checksIds(){
	var ids = '';
	$("input[name='checksIds']:checked").each(function(){
		ids += $(this).val() + ',';
	});
	if(ids == ''){
		layer.msg("必需选择一个或以上");
		return false;
	}
	return ids;
}
function getIds() {
	var ids = '';
	$("input[name='checksIds']:checked").each(function(){
		ids += $(this).val() + ',';
	});
	return ids;
}
</script>
<c:if test="${!empty page}">
	<script src="${CONTEXT_PATH}/static/laypage-v1.3/laypage/laypage.js"></script>
	<script>
// //好像很实用的样子，后端的同学再也不用写分页逻辑了。
	laypage({
	    cont: 'page',
	    pages: ${page.totalPage}, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
	    curr: function(){ //通过url获取当前页，也可以同上（pages）方式获取
// 	        var page = location.search.match(/pageNumber=(\d+)/);
	        return ${page.pageNumber};
	    }(),
	    jump: function(e, first){ //触发分页后的回调
	        if(!first){ //一定要加此判断，否则初始时会无限刷新
// 	            location.href = '?pageNumber='+e.curr;
	        	$("input[name='pageNumber']").val(e.curr)
	            $("#form1").submit();
	        }
	    }
	});
</script>
</c:if>

</body>
</html>