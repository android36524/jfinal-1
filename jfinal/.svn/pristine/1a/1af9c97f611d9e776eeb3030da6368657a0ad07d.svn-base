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
							角色权限配置
						</div>
					</div>
				</div>
				<div class="panel-body">
					<button type="button" onclick="authorize();" class="btn btn-primary">保存</button>
					<button type="button" onclick="backList()" class="btn btn-danger">返回</button>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-4">
							当前角色：${role.roleName}
							<ul id="treeDemo" class="ztree" style="width: 400px;height: 600px;"></ul>
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
<script type="text/javascript">
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
				check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y" : "ps", "N" : "ps" }
				}
			};
			// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
// 			setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
			var zNodes = ${json};
			
			zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
		});
		function authorize(){
			var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true),
            ids = "";
            for(var i=0;i<nodes.length;){
            	ids += nodes[i].id;
            	if(++i != nodes.length){
            		ids += ",";
            	}
//	            	alert(nodes[i].id); //获取选中节点的值
            }
            var roleId = ${role.id};
            location.href = 'authorizeUpate?roleId='+roleId+'&ids='+ids;
		}
	</script>
<%@ include file="/common/footer.jsp"%>
