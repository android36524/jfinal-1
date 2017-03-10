<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>${act}用户</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="dropdown-toggle" data-toggle="dropdown"
						href="form_basic.html#"> <i class="fa fa-wrench"></i>
					</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="form_basic.html#">选项1</a></li>
						<li><a href="form_basic.html#">选项2</a></li>
					</ul>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="ibox-content">
				<form class="form-horizontal m-t" id="form1" action='<c:if test="${empty user}">save</c:if><c:if test="${!empty user.id}">update</c:if>' method="post" >
					<input type="hidden" name="user.id" value="${user.id}"> 
					<input type="hidden" name="user.version" value="${user.version}">
					<div class="form-group">
						<label class="col-sm-3 control-label">账号：</label>
						<div class="col-sm-8">
							<input id="user.userCode" name="user.userCode" value="${user.userCode}" class="form-control"
								type="text"> <span class="help-block m-b-none"><i
								class="fa fa-info-circle"></i> 这里写点提示的内容</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">用户名：</label>
						<div class="col-sm-8">
							<input id="user.userName" name="user.userName" value="${user.userName}" class="form-control"
								type="text" aria-required="true" aria-invalid="true"
								class="error">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label">电话：</label>
						<div class="col-sm-8">
							<input id="user.phone" name="user.phone" value="${user.phone}" class="form-control" type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">状态：</label>
						<div class="col-sm-8">
							<div class="radio">
								<label><input type="radio" <c:if test="${user.state == 'USE' || empty user.state }">checked="checked"</c:if>  required="required"
									value="USE" id="radio1" name="user.state">启用</label>
							</div>
							<div class="radio">
								<label><input type="radio"  <c:if test="${user.state == 'STOP'}">checked="checked"</c:if> required="required"
									value="STOP" id="radio2"  name="user.state">停用</label>
							</div>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-sm-3 control-label">角色：</label>
						<div class="col-sm-8" style="height: 200px">
							<select class="form-control" multiple="multiple" style="height: 200px" name="roleIds">
								<c:forEach items="${roleList}" var="v">
	                            	<option value="${v.id}" 
	                            		<c:forEach items="${roleUserList}" var="rv">
	                            			<c:if test="${rv.roleId == v.id }"> selected="selected" </c:if> 
	                            		</c:forEach>
	                            		>${v.roleName}</option>
								</c:forEach>
                            </select>
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
