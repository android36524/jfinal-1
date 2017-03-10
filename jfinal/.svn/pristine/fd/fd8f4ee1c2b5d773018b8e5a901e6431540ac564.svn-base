<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>

<link rel="stylesheet" href="${CONTEXT_PATH}/static/zTree_v3-master/css/demo.css" type="text/css">
<link rel="stylesheet" href="${CONTEXT_PATH}/static/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-4">
							树
						</div>
					</div>
				</div>
				<div class="panel-body">
					<button type="button" id="add" class="btn btn-primary">新增</button>
					<button type="button" id="del" class="btn btn-danger">删除</button>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-4">
							<ul id="treeDemo" class="ztree" style="width: 400px;height: 600px;"></ul>
						</div>
						<div class="col-xs-4">
							<form class="form-horizontal m-t" id="form1" action='save' method="post" >
								<input type="hidden" name="function.version" value="0">
								<div class="form-group">
									<label class="col-sm-3 control-label">所属功能：</label>
									<div class="col-sm-8">
										<select id="select" class="form-control" name="function.parentCde">
											<option value=""></option>
											<c:forEach items="${menu}" var="v">
												<option value="${v.funcCde}" <c:if test="${function.parentCde == v.funcCde}">selected="selected"</c:if>>${v.funcName}--${v.funcCde}--${v.clevel}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">功能代码：</label>
									<div class="col-sm-8">
										<input id="function.funcCde" required="required" name="function.funcCde" value="${function.funcCde}" class="form-control" type="text"> 
											<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 必须唯一</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">功能名称：</label>
									<div class="col-sm-8">
										<input id="function.funcName" name="function.funcName" required="required"	value="${function.funcName}" class="form-control"
											type="text">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">排序：</label>
									<div class="col-sm-8">
										<input id="function.clevel" name="function.clevel" required="required"	value="${function.clevel}" class="form-control"
											type="number">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">点击链接：</label>
									<div class="col-sm-8">
										<input id="function.link" name="function.link" value="${function.link}" class="form-control"
											type="text">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">菜单等级：</label>
									<div class="col-sm-8">
										<input id="function.sort" name="function.sort" value="${function.sort}" required="required" class="form-control"
											type="number" value="1"><span class="help-block m-b-none"><i
											class="fa fa-info-circle"></i> 菜单级数，按钮默认1</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">菜单类型：</label>
									<div class="col-sm-8">
										<div class="radio">
                                        	<label><input type="radio" required="required" value="MENU" <c:if test="${function.menuType == 'MENU'}">checked="checked"</c:if> id="radio1" name="function.menuType">菜单</label>
	                                    </div>
	                                    <div class="radio">
	                                        <label><input type="radio"  required="required"	 value="BUTTON" id="radio2" <c:if test="${function.menuType == 'BUTTON'}">checked="checked"</c:if> name="function.menuType">按钮</label>
	                                    </div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">菜单状态：</label>
									<div class="col-sm-8">
										<div class="radio">
                                        	<label><input type="radio" required="required" value="USE" id="radio1" <c:if test="${function.state == 'USE'}">checked="checked"</c:if> name="function.state">使用</label>
	                                    </div>
	                                    <div class="radio">
	                                        <label><input type="radio"  required="required"	 value="STOP" id="radio2" <c:if test="${function.state == 'STOP'}">checked="checked"</c:if> name="function.state">停用</label>
	                                    </div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-8 col-sm-offset-3">
										<button class="btn btn-primary" type="submit">提交</button>
									</div>
								</div>
							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
	
	</div>
</div>
	<script type="text/javascript" src="${CONTEXT_PATH}/static/zTree_v3-master/js/jquery-1.4.4.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/static/zTree_v3-master/js/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/static/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/static/zTree_v3-master/js/jquery.ztree.exedit.js"></script>
<script>
$(document).ready(function() {
	var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback: {
			onClick: zTreeOnClick,
			beforeEditName: zTreeBeforeEditName,
			beforeRemove: zTreeBeforeRemove
		},
		edit : {
			enable: true,
			showRemoveBtn : true,
			showRenameBtn : true,
			removeTitle : "remove",
			renameTitle : "rename"
		}
	};
	// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
// 		var zNodes = [
// 		                 {"id":1, "pId":0, "name":"test1"},
// 		                 {"id":11, "pId":1, "name":"test11"},
// 		                 {"id":12, "pId":1, "name":"test12"},
// 		                 {"id":111, "pId":11, "name":"test111"}
// 		             ];
	
	var zNodes =  eval('(' + '${json}' + ')'); ;
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	
	//编辑点击事件
	function zTreeBeforeEditName(treeId, treeNode) {
		var id = treeNode.id;
		$.ajax({
			url:"edit",
			data:{"id":id},
			success:function(result){
				if(result.re.code == 'errer')
					layer.alert(result.re.msg)
				else{
					var data = result.re.data;
					
					$("input[name='function.funcCde']").val(data.funcCde);
					$("input[name='function.funcCde']").attr('readonly', true);
					$("input[name='function.version']").val(data.version);
					$("input[name='function.funcName']").val(data.funcName);
					$("input[name='function.clevel']").val(data.clevel);
					$("input[name='function.link']").val(data.link);
					$("input[name='function.sort']").val(data.sort);
					$('#select option[value='+data.parentCde+']').attr('selected',true);
					
					$("#form1").attr('action',"update");
				}
			}
		});		
		return false;
	}
	
	function zTreeBeforeRemove(treeId, treeNode) {
		var id = treeNode.id;
		var b = false;
		$.ajax({
			url:"del",
			data:{"id":id},
			async: false,
			success:function(result){
				layer.msg(result.re.msg)
				if(result.re.code == 'success')
					b = true;
			}
		});	
		return b;
	}
	
	//树的点击事件
	function zTreeOnClick(event, treeId, treeNode) {
//		    alert(treeNode.id + ", " + treeNode.name);
//			window.location.href = 'edit?id='+treeNode.id;
	};
});
</script>
<%@ include file="/common/footer.jsp"%>
