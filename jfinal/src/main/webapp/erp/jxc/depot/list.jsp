<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>仓库列表</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
		                    <div class="ibox-content">
		                        <form role="form" id="form1" class="form-inline" action="list">
		                        	<input type="hidden" name="pageNumber" value="${shopsInfo.pageNumber}">
		                            <div class="form-group">店名:
		                                <input id="shopsInfo.name" type="text" placeholder="请输入店名" name="shopsInfo.name" value="${shopsInfo.name}" class="form-control">
		                            </div>
		                            <div class="form-group">联系人:
		                                <input id="shopsInfo.appellation" type="text" placeholder="请输入联系人" name="shopsInfo.appellation" value="${shopsInfo.appellation}" class="form-control">
		                            </div>
									<button type="submit" class="btn btn-primary">搜索</button>
		                        </form>
		                    </div>
					</div>
					<div class="row">
						<button class="btn btn-info " onclick="adds();" type="button"><i class="fa fa-plus"></i> 添加</button>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>名称</th>
									<th>地址</th>
									<th>创建时间</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
									<tr>
										<td><input type="radio" class="i-checks"
											name="checksIds" value="${v.id}"></td>
										<td>${v.name}</td>
										<td>${v.addr}</td>
										<td>${v.createTime}</td>
										<td>${v.remarks}</td>
										<td>
					                        <button class="btn btn-info " onclick="edits(${v.id})" type="button"><i class="fa fa-group"></i> 编辑</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div id="page"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function adds(){
	//自定页
	
	layer.open({
	  type: 1,
	  skin: 'layui-layer-demo', //样式类名
	  closeBtn: 1, //不显示关闭按钮
	  anim: 2,
	  shadeClose: true, //开启遮罩关闭
	  content: html(null)
	});
}
function edits(id){
	//自定页
	
	$.ajax({
		url:"edit",
		data:{"id":id},
		success:function(result){
			if(result.re.code == 'err')
				layer.alert(result.re.msg)
			else{
				var data = result.re.data;
				
				layer.open({
				  type: 1,
				  skin: 'layui-layer-demo', //样式类名
				  closeBtn: 1, //不显示关闭按钮
				  anim: 2,
				  shadeClose: true, //开启遮罩关闭
				  content: html(data)
				});
			}
		}
	});		
	
}

function html(data){
	var h = '<div class="ibox-content">';
		h += '<form class="form-horizontal m-t" id="form1" action="update" method="post" >'
		if(data != null){
			h +='<input type="hidden" name="depot.id" value="'+data.id+'">'
			h +='<input type="hidden" name="depot.version" value="'+(data.version)+'">'
		}
		h +='<div class="form-group">';
		h +='<label class="col-sm-3 control-label">名称：</label>';
		h +='<div class="col-sm-8">';
		h +='<input id="depot.name" name="depot.name" value="';
		if(data != null){
			h += data.name
		}
		h +='" required="required" class="form-control" type="text">';
		h +='</div>';
		h +='</div>';
		h +='<div class="form-group">';
		h +='<label class="col-sm-3 control-label">地址：</label>';
		h +='<div class="col-sm-8">';
		h +='<input id="depot.mobile" name="depot.addr" value="';
		if(data != null && data.addr != null){
			h +=data.addr
		}
		h +='"  class="form-control" type="text">';
		h +='</div>';
		h +='</div>';
		h +='<div class="form-group">';
		h +='<label class="col-sm-3 control-label">备注：</label>';
		h +='<div class="col-sm-8">';
		h +='<input id="depot.remarks" name="depot.remarks" value="'
		if(data != null && data.remarks != null){
			h +=data.remarks;
		}
		h +='" class="form-control" type="text">';
		h +='</div>';
		h +='</div>';
		h +='<div class="form-group">';
		h +='<div class="col-sm-8 col-sm-offset-3">';
		h +='<button class="btn btn-primary" type="submit">提交</button>';
		h +='</div>';
		h +='</div>';
		h +='</form>';
		h +='</div>';
		return h;
}

</script>
<%@ include file="/common/footer.jsp"%>
