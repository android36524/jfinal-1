<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>${act}角色</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="dropdown-toggle" data-toggle="dropdown"
						href="form_basic.html#"> <i class="fa fa-wrench"></i>
					</a>
					<ul class="dropdown-menu dropdown-role">
						<li><a href="form_basic.html#">选项1</a></li>
						<li><a href="form_basic.html#">选项2</a></li>
					</ul>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="ibox-content">
				<form class="form-horizontal m-t" id="form1" action='update' method="post" >
					<input type="hidden" name="role.id" value="${role.id}">
					<div class="form-group">
						<label class="col-sm-3 control-label">名字：</label>
						<div class="col-sm-8">
							<input id="role.roleName" name="role.roleName" value="${role.roleName}"  required="required" class="form-control"
								type="text"> <span class="help-block m-b-none"><i
								class="fa fa-info-circle"></i> 这里写点提示的内容</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">说明：</label>
						<div class="col-sm-8">
							<input id="role.remark" name="role.remark" value="${role.remark}" required="required" class="form-control"
								type="text" aria-required="true" aria-invalid="true"
								class="error">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">菜单类型：</label>
						<div class="col-sm-8">
							<div class="radio">
								<label><input type="radio" <c:if test="${role.state == 'USE'}">checked="checked"</c:if>  required="required"
									value="USE" id="radio1" name="role.state">启用</label>
							</div>
							<div class="radio">
								<label><input type="radio"  <c:if test="${role.state == 'STOP'}">checked="checked"</c:if> required="required"
									value="STOP" id="radio2"  name="role.state">停用</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-3">
							<button class="btn btn-primary" type="submit">提交</button>
							<button class="btn btn-white" type="button" onclick="backList();">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
	
</script>
<%@ include file="/common/footer.jsp"%>
