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
							分类
						</div>
					</div>
				</div>
				<div class="panel-body">
					<button type="button" id="addLeaf" class="btn btn-primary">增加</button>
					<button type="button" id="edit" class="btn btn-primary">修改</button>
					<button type="button" id="remove" class="btn btn-danger">删除</button>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-4">
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
<script>
$(document).ready(function() {
	var setting = {
		view: {
			selectedMulti: false
		},
		edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false
		},
		data: {
			keep: {
				parent:true,
				leaf:true
			},
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRename: zTreeOnRename,
			onRemove: onRemove
		}
	};

// 	var zNodes =[
// 		{ id:1, pId:0, name:"parent node 1", open:true},
// 		{ id:11, pId:1, name:"leaf node 1-1"},
// 		{ id:12, pId:1, name:"leaf node 1-2"},
// 		{ id:13, pId:1, name:"leaf node 1-3"},
// 		{ id:2, pId:0, name:"parent node 2", open:true},
// 		{ id:21, pId:2, name:"leaf node 2-1"},
// 		{ id:22, pId:2, name:"leaf node 2-2"},
// 		{ id:23, pId:2, name:"leaf node 2-3"},
// 		{ id:3, pId:0, name:"parent node 3", open:true },
// 		{ id:31, pId:3, name:"leaf node 3-1"},
// 		{ id:32, pId:3, name:"leaf node 3-2"},
// 		{ id:33, pId:3, name:"leaf node 3-3"}
// 	];
	var zNodes =  eval('(' + '${json}' + ')');
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		return confirm("Confirm delete node '" + treeNode.name + "' it?");
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function beforeRename(treeId, treeNode, newName) {
		if (newName.length == 0) {
			alert("Node name can not be empty.");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	function zTreeOnRename(event, treeId, treeNode) {
		$.ajax({
			url:"updateTree",
			data:{"id":treeNode.id,"name":treeNode.name},
			async: false,
			success:function(result){
				layer.msg(result.msg)
			}
		});	
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}
	
	var newCount = 1;
	function add(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		isParent = e.data.isParent,
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		treeNode.isParent = true
		treeNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, isParent:false, name:"new node" + (newCount++)});
		if (treeNode) {
			//提交数到后台
			
			$.ajax({
				url:"saveTree",
				data:{"pId":treeNode[0].pId,"name":treeNode[0].name},
				async: false,
				success:function(result){
// 					layer.msg(result.re.msg)
// 					if(result.re.code == 'success')
// 						b = true;
					treeNode[0].id = result.id
					zTree.editName(treeNode[0]);
				}
			});	
		} else {
			alert("Leaf node is locked and can not add child node.");
		}
	};
	function edit() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("Please select one node at first...");
			return;
		}
		
		zTree.editName(treeNode);
		
	};
	function remove(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("Please select one node at first...");
			return;
		}
		var callbackFlag = $("#callbackTrigger").attr("checked");
		zTree.removeNode(treeNode, callbackFlag);
	};
	function clearChildren(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0 || !nodes[0].isParent) {
			alert("Please select one parent node at first...");
			return;
		}
		zTree.removeChildNodes(treeNode);
	};
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("#addParent").bind("click", {isParent:true}, add);
		$("#addLeaf").bind("click", {isParent:true}, add);
		$("#edit").bind("click", edit);
		$("#remove").bind("click", remove);
		$("#clearChildren").bind("click", clearChildren);
	});
	//-->
});
</script>
<%@ include file="/common/footer.jsp"%>
