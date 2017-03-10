<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>商家列表</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="ibox-content">
							<form role="form" id="form1" class="form-inline" action="list">
								<input type="hidden" name="pageNumber"
									value="${shopsInfo.pageNumber}">
								<div class="form-group">
									店名: <input id="shopsInfo.name" type="text" placeholder="请输入店名"
										name="shopsInfo.name" value="${shopsInfo.name}"
										class="form-control">
								</div>
								<div class="form-group">
									联系人: <input id="shopsInfo.appellation" type="text"
										placeholder="请输入联系人" name="shopsInfo.appellation"
										value="${shopsInfo.appellation}" class="form-control">
								</div>
								<div class="form-group">
									联系电话: <input id="shopsInfo.mobile" type="text"
										placeholder="请输入联系电话" name="shopsInfo.mobile"
										value="${shopsInfo.mobile}" class="form-control">
								</div>
								<button type="submit" class="btn btn-primary">搜索</button>
							</form>
						</div>
					</div>
					<div class="row">
						<button class="btn btn-info " onclick="sync();" type="button"><i class="fa fa-paste"></i> 同步o2o商家</button>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>店名</th>
									<th>联系人</th>
									<th>联系电话</th>
									<th>地址</th>
									<th>关联账号</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
									<tr>
										<td><input type="radio" class="i-checks"
											name="checksIds" value="${v.id}"></td>
										<td>${v.name}</td>
										<td>${v.appellation}</td>
										<td>${v.mobile}</td>
										<td>${v.house}</td>
										<td>
											账号：${v.get('userCode')}<br>
											用户名：${v.get('userName')}
										</td>
										<td>
											<c:if test="${empty v.get('shopsInfo')}">
						                        <button class="btn btn-info " onclick="authorization(${v.id},${v.mobile})" type="button"><i class="fa fa-group"></i> 授权转为本系统用户</button>
											</c:if>
											<c:if test="${!empty v.get('shopsInfo')}">
												<span style="color: green;">该店已转为本系统用户</span>
											</c:if>
<%-- 					                        <button class="btn btn-warning " onclick="del(${v.id})" type="button"><i class="fa fa-warning"></i> <span class="bold">警告</span></button> --%>
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
function sync(){
	location.href = "sync";
}
function authorization(id,mobile){
	layer.open({
		  type: 1,
		  skin: 'layui-layer-demo', //样式类名
		  closeBtn: 0, //不显示关闭按钮
		  anim: 2,
		  shadeClose: true, //开启遮罩关闭
		  content: '<div class="ibox-content">'+
						'<form class="form-horizontal m-t" id="form1" action="authorization" method="post" >'+
							'<input name="id" type="hidden" value="'+id+'" />'+
							'<div class="form-group">'+
								'<label class="col-sm-3 control-label">账号：</label>'+
								'<div class="col-sm-8">'+
									'<input name="accunt" value="'+mobile+'" required="required" class="form-control" type="text">'+
									'<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 默认手机号为账号</span>'+
								'</div>'+
							'</div>'+
							'<div class="form-group">'+
								'<div class="col-sm-8">'+
									'登录密码为：888888'+
								'</div>'+
							'</div>'+
							'<div class="form-group">'+
								'<div class="col-sm-8 col-sm-offset-3">'+
									'<button class="btn btn-primary" type="submit">确定</button>'+
								'</div>'+
							'</div>'+
						'</form>'+
					'</div>'
		});
	
}
</script>
<%@ include file="/common/footer.jsp"%>
