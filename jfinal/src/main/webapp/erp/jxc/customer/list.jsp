<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>客户列表</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
		                    <div class="ibox-content">
		                        <form role="form" id="form1" class="form-inline" action="list">
		                        	<input type="hidden" name="pageNumber" value="${customer.pageNumber}">
		                            <div class="form-group">名称:
		                                <input id="customer.name" type="text" placeholder="请输入店名" name="customer.name" value="${customer.name}" class="form-control">
		                            </div>
		                            <div class="form-group">联系电话:
		                                <input id="customer.mobile" type="text" placeholder="请输入联系电话" name="customer.mobile" value="${customer.mobile}" class="form-control">
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
									<th>联系方式</th>
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
										<td>${v.mobile}</td>
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
				  closeBtn: 0, //不显示关闭按钮
				  anim: 2,
				  shadeClose: true, //开启遮罩关闭
				  content: '<div class="ibox-content">'+
							'<form class="form-horizontal m-t" id="form1" action="update" method="post" >'+
								'<input type="hidden" name="customer.id" value="'+data.id+'">'+
								'<input type="hidden" name="customer.version" value="'+data.version+'">'+
								'<div class="form-group">'+
									'<label class="col-sm-3 control-label">客户名称：</label>'+
									'<div class="col-sm-8">'+
										'<input id="customer.name" name="customer.name" value="'+data.name+'" required="required" class="form-control" type="text">'+
									'</div>'+
								'</div>'+
								'<div class="form-group">'+
									'<label class="col-sm-3 control-label">联系电话：</label>'+
									'<div class="col-sm-8">'+
										'<input id="customer.mobile" name="customer.mobile" value="'+data.mobile+'"  class="form-control" type="text">'+
									'</div>'+
								'</div>'+
								'<div class="form-group">'+
									'<label class="col-sm-3 control-label">备注：</label>'+
									'<div class="col-sm-8">'+
										'<input id="customer.remarks" name="customer.remarks" value="'+data.remarks+'" class="form-control" type="text">'+
									'</div>'+
								'</div>'+
								'<div class="form-group">'+
									'<div class="col-sm-8 col-sm-offset-3">'+
										'<button class="btn btn-primary" type="submit">提交</button>'+
									'</div>'+
								'</div>'+
							'</form>'+
							'</div>'
				});
			}
		}
	});		
	
}

function adds(){
	//自定页
	layer.open({
	  type: 1,
	  skin: 'layui-layer-demo', //样式类名
	  closeBtn: 0, //不显示关闭按钮
	  anim: 2,
	  shadeClose: true, //开启遮罩关闭
	  content: '<div class="ibox-content">'+
				'<form class="form-horizontal m-t" id="form1" action="save" method="post" >'+
					'<div class="form-group">'+
						'<label class="col-sm-3 control-label">客户名称：</label>'+
						'<div class="col-sm-8">'+
							'<input id="customer.name" name="customer.name" required="required" class="form-control" type="text">'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label class="col-sm-3 control-label">联系电话：</label>'+
						'<div class="col-sm-8">'+
							'<input id="customer.mobile" name="customer.mobile" class="form-control" type="text">'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label class="col-sm-3 control-label">备注：</label>'+
						'<div class="col-sm-8">'+
							'<input id="customer.remarks" name="customer.remarks" class="form-control" type="text">'+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<div class="col-sm-8 col-sm-offset-3">'+
							'<button class="btn btn-primary" type="submit">提交</button>'+
						'</div>'+
					'</div>'+
				'</form>'+
				'</div>'
	});
}
</script>
<%@ include file="/common/footer.jsp"%>
