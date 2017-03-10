<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>角色配置用户</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown"
							href="table_basic.html#"> <i class="fa fa-wrench"></i>
						</a>
						<a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="row">
		                    <div class="ibox-content">
		                        <form role="form" id="form1" class="form-inline" action="roleToUser">
		                        	<input type="hidden" name="roleId" value="${roleId}">
		                        	<input type="hidden" name="pageNumber" value="1">
		                            <div class="form-group">
		                                <label for="userCode" class="sr-only">用户账号</label>
		                                <input id="userCode" type="text" placeholder="请输入用户账号" name="user.userCode" value="${user.userCode }" class="form-control">
		                            </div>
		                            <div class="form-group">
		                                <label for="userName" class="sr-only">用户名</label>
		                                <input id="userName" type="text" placeholder="请输入用户名" name="user.userName" value="${user.userName}" class="form-control">
		                            </div>
									<button type="submit" class="btn btn-primary">搜索</button>
		                        </form>
		                    </div>
					</div>
					<div class="row">
						<div class="col-sm-4 m-b-xs">
							<div class="btn-group hidden-xs" id="exampleToolbar" role="group">
                                    <button type="button" onclick="roleToUser()" class="btn btn-outline btn-default">
                                       <i class="fa fa-exchange"> 配置所选用户</i>
                                    </button>
                             </div>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>账号</th>
									<th>用户名</th>
									<th>电话</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
									<tr>
										<td><input type="checkbox" class="i-checks"
											name="checksIds" value="${v.id}"></td>
										<td>${v.userCode}</td>
										<td>${v.userName}</td>
										<td>${v.phone}</td>
										<td>${v.state}</td>
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
function roleToUser(){
	var ids = checksIds();
	if(!ids){
		return;
	}
	location.href = "roleToUserUpdate?roleId=${roleId}&ids="+ids;
}
</script>
<%@ include file="/common/footer.jsp"%>
